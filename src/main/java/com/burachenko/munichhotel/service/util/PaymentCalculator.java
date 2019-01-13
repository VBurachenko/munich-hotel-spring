package com.burachenko.munichhotel.service.util;

import com.burachenko.munichhotel.dto.RoomDto;

import java.util.List;

public class PaymentCalculator {

    public static double calculateTotalPayment(final int nightsCount, final List<RoomDto> roomsInBooking){

        double commonDailyCost = 0.0;

        for (final RoomDto currentRoom : roomsInBooking){
            commonDailyCost += currentRoom.getPricePerNight();
        }
        return commonDailyCost * nightsCount;
    }
}
