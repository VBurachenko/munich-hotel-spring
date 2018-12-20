package com.burachenko.munichhotel.dbo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "booking")
public class Booking implements EntityDbo {

    private static final long serialVersionUID = -3208736046194083051L;

    @Id
    @Column(name="booking_id", unique = true, nullable = false)
    private Long bookingId;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name="check_in", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate checkIn;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name="check_out", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate checkOut;

    @Column(name="adult_count", nullable = false, precision = 2)
    private Integer adultCount;

    @Column(name="child_count", nullable = false, precision = 2)
    private Integer childCount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Invoice invoice;

    @Column(name="status", nullable = false, length = 10)
    @Enumerated(value = EnumType.STRING)
    private BookingStatus status = BookingStatus.REGISTERED;

    @ManyToMany(mappedBy = "bookings")
    private Set<Room> rooms = new HashSet<>();

    public Booking() {
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId) &&
                Objects.equals(checkIn, booking.checkIn) &&
                Objects.equals(checkOut, booking.checkOut) &&
                Objects.equals(adultCount, booking.adultCount) &&
                Objects.equals(childCount, booking.childCount) &&
                Objects.equals(user, booking.user) &&
                Objects.equals(invoice, booking.invoice) &&
                status == booking.status &&
                Objects.equals(rooms, booking.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, checkIn, checkOut, adultCount, childCount, user, invoice, status, rooms);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", adultCount=" + adultCount +
                ", childCount=" + childCount +
                ", user=" + user +
                ", invoice=" + invoice +
                ", status=" + status +
                ", rooms=" + rooms +
                '}';
    }
}
