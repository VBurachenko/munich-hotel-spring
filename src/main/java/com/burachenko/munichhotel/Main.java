package com.burachenko.munichhotel;

import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(final String[] args) {
        System.out.println(getDateFirstDayOfWeek(30, 2018));
    }

    public static Date getDateFirstDayOfWeek(final int weekNumber, final int year){
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }
}
