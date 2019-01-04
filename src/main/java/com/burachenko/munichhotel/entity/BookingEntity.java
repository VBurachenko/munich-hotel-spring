package com.burachenko.munichhotel.entity;

import com.burachenko.munichhotel.enumeration.BookingStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "booking")
public class BookingEntity extends IdentifiableEntity {

    @Id
    @Column(name="id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long id;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    @Column(name = "adult_count", nullable = false)
    private Integer adultCount;

    @Column(name = "child_count", nullable = false)
    private Integer childCount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserEntity user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private InvoiceEntity invoice;

    @Column(name = "status", nullable = false, length = 10)
    @Enumerated(value = EnumType.STRING)
    private BookingStatus status;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "bookingSet")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<RoomEntity> roomSet = new HashSet<>();
}
