package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.BookingConverter;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
