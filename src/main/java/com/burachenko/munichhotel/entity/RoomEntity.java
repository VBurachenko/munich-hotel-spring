package com.burachenko.munichhotel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "room")
public class RoomEntity extends IdentifiableEntity {

    @Column(name="berth_count", nullable = false, columnDefinition = "TINYINT")
    private Integer berthCount;

    @Column(name="comfort_level", nullable = false, columnDefinition = "TINYINT")
    private Integer comfortLevel;

    @Column(name="price_per_night", nullable = false)
    private Double pricePerNight;

    @Column(name="picture_link", nullable = false)
    private String pictureLink;

    @Column(name="available", nullable = false, precision = 1, columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isAvailable;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "booking_room",
            joinColumns = @JoinColumn(name="room_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<BookingEntity> bookingList;
}
