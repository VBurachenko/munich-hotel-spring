package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.dbo.Booking;
import com.burachenko.munichhotel.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(final BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getBookingsList(){
        return bookingRepository.findAll();
    }

    public void createBooking(final Booking booking){
        bookingRepository.save(booking);
    }
}
