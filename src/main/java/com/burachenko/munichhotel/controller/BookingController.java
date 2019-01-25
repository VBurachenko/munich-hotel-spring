package com.burachenko.munichhotel.controller;

import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/test")
    public String test(@RequestParam("before") String before, @RequestParam("after") String after) {
        //List<BookingEntity> current = bookingService.findAllByCheckInBeforeAndCheckOutAfter(LocalDate.parse(before), LocalDate.parse(after));
        //return "Size: " + current.size() + ", number: " + current.get(0).getCheckIn().toString() + ", " + (current.size() > 1 ? current.get(1).getCheckIn().toString() : " null");
        Map<Long, Double> currentPayments = bookingService.getPayDataForPeriodByRooms(LocalDate.parse(before), LocalDate.parse(after));
        StringBuilder result=new StringBuilder();
        result.append("Result: ");
        for (Long id: currentPayments.keySet()){
            result.append("id: " + id + ", summ: " + currentPayments.get(id) + ", ");
        }
        result.delete(result.length()-2, result.length()-1);
        return result.toString();
    }

    @GetMapping("/testcomfort")
    public String testByComfort(@RequestParam("before") String before, @RequestParam("after") String after) {
        //List<BookingEntity> current = bookingService.findAllByCheckInBeforeAndCheckOutAfter(LocalDate.parse(before), LocalDate.parse(after));
        //return "Size: " + current.size() + ", number: " + current.get(0).getCheckIn().toString() + ", " + (current.size() > 1 ? current.get(1).getCheckIn().toString() : " null");
        Map<Integer, Double> currentPayments = bookingService.getPayDataForPeriodByComfort(LocalDate.parse(before), LocalDate.parse(after));
        StringBuilder result=new StringBuilder();
        result.append("Result: ");
        for (Integer id: currentPayments.keySet()){
            result.append("comfort level: " + id + ", summ: " + currentPayments.get(id) + ", ");
        }
        result.delete(result.length()-2, result.length()-1);
        return result.toString();
    }

    @GetMapping("/history")
    public void showHistory(){

    }

    @GetMapping("/get/{id}")
    public BookingDto getBooking(@PathVariable final long id){
        return bookingService.findById(id);
    }
}