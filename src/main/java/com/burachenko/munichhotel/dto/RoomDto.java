package com.burachenko.munichhotel.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class RoomDto implements EntityDto{

    private Integer roomId;

    private Integer berthCount = 1;

    private Integer comfortLevel = 3;

    private Double pricePerNight;

    private String pictureLink;

    private Boolean isAvailable = false;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<BookingDto> bookingSet = new HashSet<>();
}
