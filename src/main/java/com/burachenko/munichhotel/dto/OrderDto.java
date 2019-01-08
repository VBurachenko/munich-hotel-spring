package com.burachenko.munichhotel.dto;

import com.burachenko.munichhotel.enumeration.BookingStatus;
import com.burachenko.munichhotel.enumeration.InvoiceStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OrderDto extends IdentifiableDto{

    private Long bookingId;

    private Long userId;

    private Double totalPayment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate checkIn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate checkOut;

    private BookingStatus bookingStatus;

    private InvoiceStatus invoiceStatus;

}
