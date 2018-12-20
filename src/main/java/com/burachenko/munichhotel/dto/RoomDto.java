package com.burachenko.munichhotel.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RoomDto implements EntityDto{

    private Integer roomId;

    private Integer berthCount;

    private Integer comfortLevel;

    private Double pricePerNight;

    private String pictureLink;

    private Boolean isAvailable;

    private Set<BookingDto> bookings = new HashSet<>();

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getBerthCount() {
        return berthCount;
    }

    public void setBerthCount(Integer berthCount) {
        this.berthCount = berthCount;
    }

    public Integer getComfortLevel() {
        return comfortLevel;
    }

    public void setComfortLevel(Integer comfortLevel) {
        this.comfortLevel = comfortLevel;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Set<BookingDto> getBookings() {
        return bookings;
    }

    public void setBookings(Set<BookingDto> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomDto roomDto = (RoomDto) o;
        return Objects.equals(roomId, roomDto.roomId) &&
                Objects.equals(berthCount, roomDto.berthCount) &&
                Objects.equals(comfortLevel, roomDto.comfortLevel) &&
                Objects.equals(pricePerNight, roomDto.pricePerNight) &&
                Objects.equals(pictureLink, roomDto.pictureLink) &&
                Objects.equals(isAvailable, roomDto.isAvailable) &&
                Objects.equals(bookings, roomDto.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, berthCount, comfortLevel, pricePerNight, pictureLink, isAvailable, bookings);
    }
}
