package com.burachenko.munichhotel.dto;

import com.burachenko.munichhotel.enumeration.InvoiceStatus;

import java.time.LocalDate;
import java.util.Objects;

public class InvoiceDto implements EntityDto{

    private Long invoiceId;

    private UserDto user;

    private LocalDate appointment;

    private Integer nightsCount;

    private Double totalPayment;

    private InvoiceStatus status;

    private Boolean isPayed;

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
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
        InvoiceDto that = (InvoiceDto) o;
        return Objects.equals(invoiceId, that.invoiceId) &&
                Objects.equals(user, that.user) &&
                Objects.equals(appointment, that.appointment) &&
                Objects.equals(nightsCount, that.nightsCount) &&
                Objects.equals(totalPayment, that.totalPayment) &&
                status == that.status &&
                Objects.equals(isPayed, that.isPayed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, user, appointment, nightsCount, totalPayment, status, isPayed);
    }
}
