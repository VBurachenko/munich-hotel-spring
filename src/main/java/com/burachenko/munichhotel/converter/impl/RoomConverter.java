package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.EntityDtoConverter;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.entity.RoomEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoomConverter implements EntityDtoConverter<RoomEntity, RoomDto> {

    @Override
    public RoomDto convertToDto(final RoomEntity room) {
        final RoomDto roomDto = new RoomDto();
        removeRoomSetFromBookingDto(roomDto);
        BeanUtils.copyProperties(room, roomDto, "bookingSet");
        return roomDto;
    }

    @Override
    public RoomEntity convertToEntity(final RoomDto roomDto) {
        final RoomEntity room = new RoomEntity();
        removeRoomSetFromBookingDbo(room);
        BeanUtils.copyProperties(roomDto, room, "bookingSet");
        return room;
    }

    private void removeRoomSetFromBookingDto(final RoomDto dto){
        final Set<BookingDto> dtoBookingSet = dto.getBookingSet();
        if (dtoBookingSet != null){
            for (final BookingDto bookingDto : dtoBookingSet) {
                bookingDto.setRoomSet(null);
            }
        }
    }

    private void removeRoomSetFromBookingDbo(final RoomEntity dbo){
        final Set<BookingEntity> dboBookingSet = dbo.getBookingSet();
        if (dboBookingSet != null){
            for (final BookingEntity booking : dboBookingSet) {
                booking.setRoomSet(null);
            }
        }
    }
}
