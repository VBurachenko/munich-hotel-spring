package com.burachenko.munichhotel.service.util;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.Date;

public class DatesCalculator {

    public static int nightsCountCalculate(final java.time.LocalDate checkIn, final java.time.LocalDate checkOut){
        return nightsCountCalculate(java.sql.Date.valueOf(checkIn), java.sql.Date.valueOf(checkOut));
    }

    private static int nightsCountCalculate(final Date checkIn, final Date checkOut){
        return nightsCountCalculate(new LocalDate(checkIn), new LocalDate(checkOut));
    }

    private static int nightsCountCalculate(final LocalDate checkIn, final LocalDate checkOut){
        return Days.daysBetween(checkIn, checkOut).getDays();
    }
}
