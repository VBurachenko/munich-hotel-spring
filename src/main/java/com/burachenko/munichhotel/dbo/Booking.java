package com.burachenko.munichhotel.dbo;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

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
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "booking")
public class Booking implements EntityDbo {

    private static final long serialVersionUID = -3208736046194083051L;

    @Id
    @Column(name="booking_id", unique = true, nullable = false, updatable = false)
    private Long bookingId;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name="check_in", nullable = false)
    private LocalDate checkIn;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name="check_out", nullable = false)
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

}
