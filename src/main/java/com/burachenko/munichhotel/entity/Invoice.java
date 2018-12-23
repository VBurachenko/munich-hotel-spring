package com.burachenko.munichhotel.entity;

import com.burachenko.munichhotel.enumeration.InvoiceStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "invoice")
public class Invoice extends IdentifiableEntity {

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name="appointment", nullable = false)
    private LocalDate appointment = LocalDate.now();

    @Column(name="nights_count", nullable = false)
    private Integer nightsCount;

    @Column(name="total_payment", nullable = false, precision = 10, scale = 2)
    private Double totalPayment;

    @Column(name="status", nullable = false, length = 15)
    @Enumerated(value = EnumType.STRING)
    private InvoiceStatus status;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name="payed", nullable = false, precision = 1, columnDefinition = "TINYINT")
    private Boolean isPayed;

}
