package com.burachenko.munichhotel.dto;

import com.burachenko.munichhotel.enumeration.BookingStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BookingDto extends IdentifiableDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate checkIn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate checkOut;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserAccountDto userAccount;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private InvoiceDto invoice;

    private BookingStatus status = BookingStatus.REGISTERED;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<RoomDto> roomList;
}
