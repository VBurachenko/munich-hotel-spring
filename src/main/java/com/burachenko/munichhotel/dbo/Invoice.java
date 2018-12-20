package com.burachenko.munichhotel.dbo;

import com.burachenko.munichhotel.enumeration.InvoiceStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;
import java.time.LocalDate;
import java.util.Objects;

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
    @Column(name="invoice_id", nullable = false, unique = true)
    private Long invoiceId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name="appointment", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getAppointment() {
        return appointment;
    }

    public void setAppointment(LocalDate appointment) {
        this.appointment = appointment;
    }

    public Integer getNightsCount() {
        return nightsCount;
    }

    public void setNightsCount(Integer nightsCount) {
        this.nightsCount = nightsCount;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public Boolean getPayed() {
        return isPayed;
    }

    public void setPayed(Boolean payed) {
        isPayed = payed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceId, invoice.invoiceId) &&
                Objects.equals(user, invoice.user) &&
                Objects.equals(appointment, invoice.appointment) &&
                Objects.equals(nightsCount, invoice.nightsCount) &&
                Objects.equals(totalPayment, invoice.totalPayment) &&
                status == invoice.status &&
                Objects.equals(isPayed, invoice.isPayed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, user, appointment, nightsCount, totalPayment, status, isPayed);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", user=" + user +
                ", appointment=" + appointment +
                ", nightsCount=" + nightsCount +
                ", totalPayment=" + totalPayment +
                ", status=" + status +
                ", isPayed=" + isPayed +
                '}';
    }
}
