package com.burachenko.munichhotel.controller;

import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/test")
    public String test(@RequestParam("before") String before, @RequestParam("after") String after) {
        List<BookingEntity> current = bookingService.findAllByCheckInBeforeAndCheckOutAfter(LocalDate.parse(before), LocalDate.parse(after));
        return "Size: " + current.size() + ", number: " + current.get(0).getCheckIn().toString() + ", " + (current.size() > 1 ? current.get(1).getCheckIn().toString() : " null");
    }
}
