package com.burachenko.munichhotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingHistoryDto implements Serializable {

    private UserAccountDto userAccount;

    private List<BookingDto> bookingList = new ArrayList<>();
}
