package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.DtoDboConverter;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.entity.Booking;
import com.burachenko.munichhotel.entity.Room;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoomConverter implements DtoDboConverter<Room, RoomDto> {

    @Override
    public RoomDto convertToDto(Room room) {
        final RoomDto roomDto = new RoomDto();
        removeRoomSetFromBookingDto(roomDto);
        BeanUtils.copyProperties(room, roomDto, "bookingSet");
        return roomDto;
    }

    @Override
    public Room convertToDbo(RoomDto roomDto) {
        final Room room = new Room();
        removeRoomSetFromBookingDbo(room);
        BeanUtils.copyProperties(roomDto, room, "bookingSet");
        return room;
    }

    private void removeRoomSetFromBookingDto(final RoomDto dto){
        final Set<BookingDto> dtoBookingSet = dto.getBookingSet();
        if (dtoBookingSet != null){
            for (BookingDto bookingDto : dtoBookingSet) {
                bookingDto.setRoomSet(null);
            }
        }
    }

    private void removeRoomSetFromBookingDbo(final Room dbo){
        final Set<Booking> dboBookingSet = dbo.getBookingSet();
        if (dboBookingSet != null){
            for (Booking booking : dboBookingSet) {
                booking.setRoomSet(null);
            }
        }
    }
}
