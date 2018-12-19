package com.burachenko.munichhotel.dbo;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room implements EntityDbo {

    private static final long serialVersionUID = -3208736046194083051L;

    @Id
    @Column(name="room_id", nullable = false, unique = true, updatable = false)
    private Integer roomId;

    @Column(name="berth_count", nullable = false, columnDefinition = "TINYINT", precision = 1)
    private Integer berthCount = 1;

    @Column(name="comfort_level", nullable = false, columnDefinition = "TINYINT", precision = 1)
    private Integer comfortLevel = 3;

    @Column(name="price_per_night", nullable = false)
    private Double pricePerNight;

    @Column(name="picture_link", nullable = false)
    private String pictureLink;

    @Column(name="available", nullable = false, precision = 1, columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isAvailable = false;

    @ManyToMany
    @JoinTable(name = "booking_room",
            joinColumns = @JoinColumn(name="room_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id"))
    private Set<Booking> bookings = new HashSet<>();

    public Room() {
    }

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

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomId, room.roomId) &&
                Objects.equals(berthCount, room.berthCount) &&
                Objects.equals(comfortLevel, room.comfortLevel) &&
                Objects.equals(pricePerNight, room.pricePerNight) &&
                Objects.equals(pictureLink, room.pictureLink) &&
                Objects.equals(isAvailable, room.isAvailable) &&
                Objects.equals(bookings, room.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, berthCount, comfortLevel, pricePerNight, pictureLink, isAvailable, bookings);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", berthCount=" + berthCount +
                ", comfortLevel=" + comfortLevel +
                ", pricePerNight=" + pricePerNight +
                ", pictureLink='" + pictureLink + '\'' +
                ", isAvailable=" + isAvailable +
                ", bookings=" + bookings +
                '}';
    }
}
