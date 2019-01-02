package com.burachenko.munichhotel.controller;

import com.burachenko.munichhotel.dto.OrderDto;
import com.burachenko.munichhotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private final BookingService bookingService;

    public BookingController(final BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/orders")
    public List<OrderDto> findOrders(
            @RequestParam(name = "fromDate") final String fromDate,
            @RequestParam(name = "toDate") final String toDate,
            @RequestParam(name = "bookingStatus") final String bookingStatus) {
        return bookingService.findOrders(fromDate, toDate, bookingStatus);
    }

    @GetMapping("/moneyEarned")
    public Double moneyAmount(
            @RequestParam(name = "fromDate") final String fromDate,
            @RequestParam(name = "toDate") final String toDate) {
        return bookingService.getMoneyAmount(fromDate, toDate);
    }
}
