package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.BookingConverter;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.OrderDto;
import com.burachenko.munichhotel.dto.SearchUnitDto;
import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.entity.InvoiceEntity;
import com.burachenko.munichhotel.enumeration.BookingStatus;
import com.burachenko.munichhotel.repository.BookingRepository;
import com.burachenko.munichhotel.repository.InvoiceRepository;
import com.burachenko.munichhotel.service.util.IdCreator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingConverter bookingConverter;

    private final InvoiceRepository invoiceRepository;

    private final UserService userService;

    public List<BookingEntity> getBookingsList() {
        return bookingRepository.findAll();
    }

    public BookingDto createBooking(final BookingDto bookingDto) {
        int startIndex = 1;
        long bookingId = IdCreator.createLongIdByTodayDate(1);
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
        preparedBooking.setAdultCount(searchUnit.getAdultCount());
        preparedBooking.setChildCount(searchUnit.getChildCount());
        preparedBooking.setUser(userService.getUser(userId));
        return preparedBooking;
    }

    public BookingDto getBooking(final long id) {
        final Optional<BookingEntity> bookingEntity = bookingRepository.findById(id);
        if (bookingEntity.isPresent()) {
            return bookingConverter.convertToDto(bookingEntity.get());
        }
        return null;
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

    BookingDto setInvoiceToBooking(final BookingDto bookingDto) {
        return updateBooking(bookingDto, bookingDto.getId());
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
        if (bookingEntity.isPresent()) {
            return bookingConverter.convertToDto(bookingEntity.get());
        }
        return null;
    }

    public Set<BookingDto> getBookingListByUserId(final long userId) {
        return bookingConverter.convertToDto(bookingRepository.getBookingSetByUserId(userId));
    }

    public List<OrderDto> findOrders(final String fromDate, final String toDate, final String status) {
        final LocalDate from = LocalDate.parse(fromDate).plusDays(1);
        final LocalDate to = LocalDate.parse(toDate).plusDays(1);

        BookingStatus bookingStatus;
        try {
            bookingStatus = BookingStatus.valueOf(status);
        } catch (Exception e) {
            return null;
        }

        List<BookingEntity> bookingEntities = bookingRepository.getByCheckInBetweenAndStatus(from, to, bookingStatus);
        List<InvoiceEntity> invoiceEntities;
        List<Long> invoiceIds = new ArrayList<>();
        List<OrderDto> orderDtos = new ArrayList<>();

        for (BookingEntity bookingEntity : bookingEntities) {
            invoiceIds.add(bookingEntity.getInvoice().getId());
        }

        invoiceEntities = invoiceRepository.findByIdIn(invoiceIds);

        for (int i = 0; i < bookingEntities.size(); i++) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId((long) i);
            orderDto.setInvoiceStatus(invoiceEntities.get(i).getStatus());
            orderDto.setTotalPayment(invoiceEntities.get(i).getTotalPayment());
            orderDto.setUserId(bookingEntities.get(i).getUser().getId());
            orderDto.setBookingId(bookingEntities.get(i).getId());
            orderDto.setBookingStatus(bookingEntities.get(i).getStatus());
            orderDto.setCheckIn(bookingEntities.get(i).getCheckIn());
            orderDto.setCheckOut(bookingEntities.get(i).getCheckOut());
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    public Double getMoneyAmount(final String fromDate, final String toDate) {
        Double sum = 0D;
        final LocalDate from = LocalDate.parse(fromDate).plusDays(1);
        final LocalDate to = LocalDate.parse(toDate).plusDays(1);

        List<BookingEntity> bookingEntities = bookingRepository.getByCheckInBetween(from, to);

        for (BookingEntity bookingEntity : bookingEntities) {
            List<InvoiceEntity> invoiceEntity = invoiceRepository.findByIdAndIsPayed(bookingEntity.getInvoice().getId(), true);
            if (!invoiceEntity.isEmpty()) {
                sum += invoiceEntity.get(0).getTotalPayment();
            }
        }

        return sum;
    }
}
