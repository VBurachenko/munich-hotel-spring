package com.burachenko.munichhotel.entity;

import com.burachenko.munichhotel.enumeration.BookingStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "booking")
public class BookingEntity extends AbstractEntity {

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name="check_in", nullable = false)
    private LocalDate checkIn;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name="check_out", nullable = false)
    private LocalDate checkOut;

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

    @Column(name="status", nullable = false, length = 10)
    @Enumerated(value = EnumType.STRING)
    private BookingStatus status;

    @ManyToMany(fetch = FetchType.EAGER,
                cascade = {CascadeType.PERSIST, CascadeType.MERGE},
                mappedBy = "bookingList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<RoomEntity> roomList;
}
