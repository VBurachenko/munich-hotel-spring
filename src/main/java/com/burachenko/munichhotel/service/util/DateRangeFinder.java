package com.burachenko.munichhotel.service.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateRangeFinder {
    public static LocalDate[] getDateRange(String type, int num){
        LocalDate[] localDates = new LocalDate[2];
        Date[] dates = new Date[2];

        final Calendar calendar = Calendar.getInstance();
        final int year = Calendar.getInstance().get(Calendar.YEAR);

        if (type.equals("week")) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.WEEK_OF_YEAR, num);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            calendar.add(Calendar.MONTH, 1);
            dates[0] = calendar.getTime();
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            dates[1] = calendar.getTime();
        } else if (type.equals("month")) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, num);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            dates[0] = calendar.getTime();
            calendar.add(Calendar.MONTH, 1);
            dates[1] = calendar.getTime();
        } else {
            if (type.equals("year")) {
                calendar.set(Calendar.YEAR, num);
            } else {
                calendar.set(Calendar.YEAR, year);
            }
            calendar.set(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            dates[0] = calendar.getTime();
            calendar.add(Calendar.YEAR, 1);
            dates[1] = calendar.getTime();
        }

        localDates[0] = dates[0].toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusMonths(1);
        localDates[1] = dates[1].toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusMonths(1);

        return localDates;
    }
}
