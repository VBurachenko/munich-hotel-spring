package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.BookingConverter;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.joda.time.Days;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingConverter bookingConverter;

    public List<BookingEntity> getBookingsList(){
        return bookingRepository.findAll();
    }

    public BookingDto createBooking(final BookingEntity booking){
        return bookingConverter.convertToDto(bookingRepository.save(booking));
    }

    private long getDaysNumber(BookingEntity entity, LocalDate before, LocalDate after){
        LocalDate startNumber=entity.getCheckIn().isBefore(before)?before:entity.getCheckIn();
        LocalDate endNumber = entity.getCheckOut().isAfter(after)?after:entity.getCheckOut();
        long days = startNumber.until(endNumber, ChronoUnit.DAYS);
        return days;
    }

    public List<BookingEntity> findAllByCheckInBeforeAndCheckOutAfter(LocalDate before, LocalDate after){
        List<BookingEntity> entities = bookingRepository.findAllByCheckInBeforeAndCheckOutAfter(after, before);
        for (BookingEntity entity: entities){
            long numberOfDays = getDaysNumber(entity, before, after);
            System.out.println(numberOfDays);
        }
        return bookingRepository.findAllByCheckInBeforeAndCheckOutAfter(after, before);
    }
}
