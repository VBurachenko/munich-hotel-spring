package com.burachenko.munichhotel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "room")
public class Room extends IdentifiableEntity {

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
    private Set<Booking> bookingSet= new HashSet<>();
}
