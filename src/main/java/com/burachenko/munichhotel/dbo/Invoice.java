package com.burachenko.munichhotel.dbo;

import com.burachenko.munichhotel.enumeration.InvoiceStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "invoice")
public class Invoice implements EntityDbo {

    @Id
    @Column(name="invoice_id", nullable = false, unique = true)
    private Long invoiceId;

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
