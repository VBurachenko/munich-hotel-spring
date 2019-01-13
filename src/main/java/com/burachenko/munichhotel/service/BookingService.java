package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.BookingConverter;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.dto.SearchUnitDto;
import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.entity.RoomEntity;
import com.burachenko.munichhotel.enumeration.BookingStatus;
import com.burachenko.munichhotel.repository.BookingRepository;
import com.burachenko.munichhotel.service.util.IdCreator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingConverter bookingConverter;

    private final UserService userService;
    private final RoomService roomService;

    public List<BookingDto> getBookingsList() {
        return bookingRepository.findAll().stream().map(bookingConverter::convertToDto).collect(Collectors.toList());
    }

    public BookingDto createBooking(final BookingDto bookingDto) {
        int startIndex = 1;
        long bookingId = IdCreator.createLongIdByTodayDate(startIndex);
        while (getBooking(bookingId) != null) {
            bookingId = IdCreator.createLongIdByTodayDate(++startIndex);
        }
        bookingDto.setId(bookingId);
        final BookingEntity bookingEntity = bookingRepository.save(
            bookingConverter.convertToEntity(bookingDto));
        if (bookingEntity != null) {
            return bookingConverter.convertToDto(bookingEntity);
        }
        return null;
    }

    public BookingDto prepareBookingToOrder(final SearchUnitDto searchUnit, final long userId) {
        final BookingDto preparedBooking = new BookingDto();
        preparedBooking.setCheckIn(searchUnit.getCheckIn());
        preparedBooking.setCheckOut(searchUnit.getCheckOut());
//        preparedBooking.setUserAccount(userService.getUserAccount(userId));
        return preparedBooking;
    }

    public BookingDto getBooking(final long id) {
        final Optional<BookingEntity> bookingEntity = bookingRepository.findById(id);
        return bookingEntity.map(bookingConverter::convertToDto).orElse(null);
    }

    public BookingDto updateBooking(final BookingDto bookingDto, final long id) {
        final Optional<BookingEntity> bookingEntity = bookingRepository.findById(id);
        if (bookingEntity.isPresent()) {
            bookingDto.setId(id);
            return bookingConverter.convertToDto(bookingEntity.get());
        }
        return null;
    }

    public BookingDto changeBookingStatus(final long id, final BookingStatus status) {
        final BookingDto bookingDto = getBooking(id);
        if (bookingDto != null) {
            bookingDto.setStatus(status);
            return updateBooking(bookingDto, id);
        }
        return null;
    }

    public boolean deleteBooking(final long id) {
        final Optional<BookingEntity> bookingEntity = bookingRepository.findById(id);
        if (bookingEntity.isPresent()) {
            bookingRepository.deleteById(id);
        }
        return !bookingRepository.findById(id).isPresent();
    }

    public BookingDto getBookingByInvoiceId(final long invoiceId) {
        final Optional<BookingEntity> bookingEntity = bookingRepository.getBookingByInvoiceId(invoiceId);
        return bookingEntity.map(bookingConverter::convertToDto).orElse(null);
    }

    public List<BookingDto> getBookingListByUserId(final long userId) {
        return bookingConverter.convertToDto(bookingRepository.getBookingListByUserAccountId(userId));
    }

    public BookingDto addRoomsToBooking(final BookingDto bookingDto, final long ... selectedRoomIds){
        for (final long roomId : selectedRoomIds){
           final RoomDto roomDto = roomService.findById(roomId);
           if (roomDto == null){
               return null;
           }
        }
        return bookingDto;
    }

    public boolean removeRoomsFromBooking(final BookingDto bookingDto, final long ... selectedRoomIds){
        final List<RoomDto> preSavedRooms = bookingDto.getRoomList();
        for (final long roomId : selectedRoomIds){
            final RoomDto roomDto = roomService.findById(roomId);
            if (roomDto == null){
                bookingDto.setRoomList(preSavedRooms);
                return false;
            }
            bookingDto.getRoomList().remove(roomDto);
        }
        return true;
    }

    private long getDaysNumber(BookingEntity entity, LocalDate before, LocalDate after) {
        LocalDate startNumber = entity.getCheckIn().isBefore(before) ? before : entity.getCheckIn();
        LocalDate endNumber = entity.getCheckOut().isAfter(after) ? after : entity.getCheckOut();
        long days = startNumber.until(endNumber, ChronoUnit.DAYS);
        return days;
    }

    private long getPeriodLength(BookingEntity entity) {
        long days = entity.getCheckIn().until(entity.getCheckOut(), ChronoUnit.DAYS);
        return days;
    }

    public List<BookingEntity> findAllByCheckInBeforeAndCheckOutAfter(LocalDate before, LocalDate after) {
        System.out.println(before + " " + after);
        return bookingRepository.findAllByCheckInBeforeAndCheckOutAfter(after.plusDays(1L), before.minusDays(1L));
    }

    public Map<Long, Double> getPayDataForPeriodByRooms(LocalDate before, LocalDate after) {
        List<BookingEntity> entities = findAllByCheckInBeforeAndCheckOutAfter(before, after);
        Map<Long, Double> roomsPays = new HashMap<>();
        for (BookingEntity entity : entities) {
            if (!entity.getRoomList().isEmpty()) {
                double coefficient = (double) getDaysNumber(entity, before, after) /
                                     (getPeriodLength(entity) * entity.getRoomList().size());
                for (RoomEntity room : entity.getRoomList()) {
                    if (!roomsPays.containsKey(room.getId())) {
                        roomsPays.put(room.getId(), 0.0);
                    }
                    double summ = roomsPays.get(room.getId());
                    summ += entity.getInvoice().getTotalPayment() * coefficient;
                    roomsPays.put(room.getId(), summ);

                }
            }
        }
        long days = before.until(after, ChronoUnit.DAYS);
        if (days != 0) {
            for (Long key : roomsPays.keySet()) {
                double summ = roomsPays.get(key);
                roomsPays.put(key, summ / days);
            }
        }
        return roomsPays;
    }

    public Map<Integer, Double> getPayDataForPeriodByComfort(LocalDate before, LocalDate after) {
        List<BookingEntity> entities = findAllByCheckInBeforeAndCheckOutAfter(before, after);
        Map<Integer, Double> roomsPays = new HashMap<>();
        for (BookingEntity entity : entities) {
            if (!entity.getRoomList().isEmpty()) {
                double coefficient = (double) getDaysNumber(entity, before, after) /
                                     (getPeriodLength(entity) * entity.getRoomList().size());
                for (RoomEntity room : entity.getRoomList()) {
                    if (!roomsPays.containsKey(room.getComfortLevel())) {
                        roomsPays.put(room.getComfortLevel(), 0.0);
                    }
                    double summ = roomsPays.get(room.getComfortLevel());
                    summ += entity.getInvoice().getTotalPayment() * coefficient;
                    roomsPays.put(room.getComfortLevel(), summ);

                }
            }
        }
        long days = before.until(after, ChronoUnit.DAYS);
        if (days != 0) {
            for (Integer key : roomsPays.keySet()) {
                double summ = roomsPays.get(key);
                roomsPays.put(key, summ / days);
            }
        }
        return roomsPays;
    }

}
