package com.burachenko.munichhotel.dto;

import com.burachenko.munichhotel.enumeration.InvoiceStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class InvoiceDto implements EntityDto{

    private Long invoiceId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate appointment = LocalDate.now();

    private Integer nightsCount;

    private Double totalPayment;

    private InvoiceStatus status = InvoiceStatus.INVOICED;

    private Boolean isPayed = false;
}
