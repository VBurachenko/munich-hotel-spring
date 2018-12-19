package com.burachenko.munichhotel.dbo;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice implements EntityDbo {

    private static final long serialVersionUID = 1892861816293848970L;

    @Id
    @Column(name="invoice_id", nullable = false, updatable = false, unique = true)
    private Long invoiceId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name="appointment", nullable = false)
    private LocalDate appointment = LocalDate.now();

    @Column(name="nights_count", nullable = false)
    private Integer nightsCount;

    @Column(name="total_payment", nullable = false, precision = 10, scale = 2)
    private Double totalPayment;

    @Column(name="status", nullable = false, length = 15)
    @Enumerated(value = EnumType.STRING)
    private InvoiceStatus status = InvoiceStatus.INVOICED;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name="payed", nullable = false, precision = 1, columnDefinition = "TINYINT")
    private Boolean isPayed = false;
}
