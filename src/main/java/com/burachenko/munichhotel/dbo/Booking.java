package com.burachenko.munichhotel.dbo;

import com.burachenko.munichhotel.enumeration.BookingStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "booking")
public class Booking implements EntityDbo {

    @Id
    @Column(name="booking_id", unique = true, nullable = false)
    private Long bookingId;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name="check_in", nullable = false)
    private LocalDate checkIn;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name="check_out", nullable = false)
    private LocalDate checkOut;

    @Column(name="adult_count", nullable = false)
    private Integer adultCount;

    @Column(name="child_count", nullable = false)
    private Integer childCount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Invoice invoice;

    @Column(name="status", nullable = false, length = 10)
    @Enumerated(value = EnumType.STRING)
    private BookingStatus status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
                mappedBy = "bookingSet")
    private Set<Room> roomSet = new HashSet<>();
}
