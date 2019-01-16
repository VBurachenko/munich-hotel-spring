package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.AbstractConverter;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.dto.SearchUnitDto;
import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.entity.RoomEntity;
import com.burachenko.munichhotel.enumeration.BookingStatus;
import com.burachenko.munichhotel.repository.BookingRepository;
import com.burachenko.munichhotel.service.util.IdCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookingService extends AbstractService<BookingDto, BookingEntity, BookingRepository>{

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private InvoiceService invoiceService;

    public BookingService(final BookingRepository repository, final AbstractConverter<BookingEntity, BookingDto> converter) {
        super(repository, converter);
    }

    @Override
    protected boolean beforeSave(final BookingDto dto) {
        return false;
    }

    @Override
    public List<BookingDto> findByFilterParameter(final String filterParameter) {
        try {
            final long id = Long.valueOf(filterParameter);
            return getConverter().convertToDto(getRepository().findAllByBookingIdOrUserIdOrInvoiceId(id));
        } catch (NumberFormatException e){
            return Collections.emptyList();
        }
    }

    @Override
    public BookingDto save(final BookingDto bookingDto) {
        int startIndex = 1;
        long bookingId = IdCreator.createLongIdByTodayDate(startIndex);
        while (findById(bookingId) != null) {
            bookingId = IdCreator.createLongIdByTodayDate(++startIndex);
        }
        bookingDto.setId(bookingId);
        final BookingEntity bookingEntity = getRepository().save(
            getConverter().convertToEntity(bookingDto));
        if (bookingEntity != null) {
            return getConverter().convertToDto(bookingEntity);
        }
        return null;
    }

    public BookingDto prepareBookingToOrder(final SearchUnitDto searchUnit, final long userId) {
        final BookingDto preparedBooking = new BookingDto();
        preparedBooking.setCheckIn(searchUnit.getCheckIn());
        preparedBooking.setCheckOut(searchUnit.getCheckOut());
        preparedBooking.setUser(userService.findById(userId));
        return preparedBooking;
    }

    public BookingDto changeBookingStatus(final long id, final BookingStatus status) {
        final BookingDto bookingDto = findById(id);
        if (bookingDto != null) {
            bookingDto.setStatus(status);
            return save(bookingDto);
        }
        return null;
    }

    public BookingDto getBookingByInvoiceId(final long invoiceId) {
        final Optional<BookingEntity> bookingEntity = getRepository().getBookingByInvoiceId(invoiceId);
        return bookingEntity.map(getConverter()::convertToDto).orElse(null);
    }

    public List<BookingDto> getBookingListByUserId(final long userId) {
        return getConverter().convertToDto(getRepository().getBookingListByUserId(userId));
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
        return getRepository().findAllByCheckInBeforeAndCheckOutAfter(after.plusDays(1L), before.minusDays(1L));
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
