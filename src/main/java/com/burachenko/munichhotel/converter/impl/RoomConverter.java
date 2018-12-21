package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.DtoDboConverter;
import com.burachenko.munichhotel.dbo.Booking;
import com.burachenko.munichhotel.dbo.Room;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.RoomDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoomConverter implements DtoDboConverter<Room, RoomDto> {

    private final BookingConverter bookingConverter;

    @Autowired
    public RoomConverter(BookingConverter bookingConverter) {
        this.bookingConverter = bookingConverter;
    }

    @Override
    public RoomDto convertToDto(Room room) {
        final RoomDto roomDto = new RoomDto();
        BeanUtils.copyProperties(room, roomDto, "bookingSet");
        setBookingSetToDto(room, roomDto);
        return roomDto;
    }

    @Override
    public Room convertToDbo(RoomDto roomDto) {
        final Room room = new Room();
        BeanUtils.copyProperties(roomDto, room, "bookingSet");
        setBookingSetToDbo(roomDto, room);
        return room;
    }

    private void setBookingSetToDbo(final RoomDto dto, final Room dbo){
        final Set<BookingDto> dtoBookingSet = dto.getBookingSet();
        if (dtoBookingSet != null){
            for (BookingDto bookingDto : dtoBookingSet) {
                bookingDto.setRoomSet(null);
            }
        }
        final Set<Booking> bookingSet = bookingConverter.convertToDbo(dtoBookingSet);
        dbo.getBookingSet().addAll(bookingSet);
    }

    private void setBookingSetToDto(final Room dbo, final RoomDto dto){
        final Set<Booking> dboBookingSet = dbo.getBookingSet();
        if (dboBookingSet != null){
            for (Booking booking : dboBookingSet) {
                booking.setRoomSet(null);
            }
        }
        final Set<BookingDto> bookingSet = bookingConverter.convertToDto(dboBookingSet);
        dto.getBookingSet().addAll(bookingSet);
    }
}
