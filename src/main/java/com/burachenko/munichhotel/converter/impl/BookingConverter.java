package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.DtoDboConverter;
import com.burachenko.munichhotel.dbo.Booking;
import com.burachenko.munichhotel.dto.BookingDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BookingConverter implements DtoDboConverter<Booking, BookingDto> {

    @Override
    public BookingDto convertToDto(Booking booking) {
        final BookingDto bookingDto = new BookingDto();
        BeanUtils.copyProperties(booking, bookingDto);
        return bookingDto;
    }

    @Override
    public Booking convertToDbo(BookingDto bookingDto) {
        final Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDto, booking);
        return booking;
    }
}
