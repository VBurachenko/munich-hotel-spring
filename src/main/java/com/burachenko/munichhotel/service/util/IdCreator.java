package com.burachenko.munichhotel.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdCreator {

    private static final String SHORT_DATE_PATTERN = "ddMMyyyy";

    public static long createLongIdByTodayDate(final int startIndex){
        final SimpleDateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_PATTERN);
        final String todayDate = dateFormat.format(new Date());
        return convertToLong(startIndex, todayDate);
    }

    private static long convertToLong(final int startIndex, final String todayDate){
        return Long.valueOf(startIndex + todayDate);
    }

}
