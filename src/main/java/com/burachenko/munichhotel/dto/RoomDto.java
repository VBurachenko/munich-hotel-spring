package com.burachenko.munichhotel.dto;

import com.burachenko.munichhotel.enumeration.RoomAvailabilityStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RoomDto extends AbstractDto {

    private Integer berthCount = 1;

    private Integer comfortLevel = 3;

    private Double pricePerNight;

    private String pictureLink;

    private RoomAvailabilityStatus isAvailable = RoomAvailabilityStatus.DISABLED;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<BookingDto> bookingList;
}
