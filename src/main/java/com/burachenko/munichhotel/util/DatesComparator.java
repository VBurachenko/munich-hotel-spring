package com.burachenko.munichhotel.util;

import java.time.LocalDate;
import java.util.Comparator;

public class DatesComparator implements Comparator<LocalDate> {

    @Override
    public int compare(final LocalDate checkIn, final LocalDate checkOut) {
        if (checkOut.isAfter(checkIn)){
            return 1;
        } else if (checkOut.isBefore(checkIn)){
            return -1;
        }
        return 0;
    }


}
