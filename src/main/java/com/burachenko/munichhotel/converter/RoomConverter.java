package com.burachenko.munichhotel.converter;

import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.entity.RoomEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomConverter extends AbstractConverter<RoomEntity, RoomDto> {

    @Override
    public RoomDto convertToDto(final RoomEntity room) {
        final RoomDto roomDto = new RoomDto();
        removeRoomListFromBookingDto(roomDto);
        BeanUtils.copyProperties(room, roomDto, "bookingList");
        return roomDto;
    }

    @Override
    public RoomEntity convertToEntity(final RoomDto roomDto) {
        final RoomEntity room = new RoomEntity();
        removeRoomSetFromBookingDbo(room);
        BeanUtils.copyProperties(roomDto, room, "bookingList");
        return room;
    }

    private void removeRoomListFromBookingDto(final RoomDto roomDto){
        final List<BookingDto> dtoBookingList = roomDto.getBookingList();
        if (dtoBookingList != null){
            for (final BookingDto bookingDto : dtoBookingList) {
                bookingDto.setRoomList(null);
            }
        }
    }

    private void removeRoomSetFromBookingDbo(final RoomEntity roomEntity){
        final List<BookingEntity> bookingList = roomEntity.getBookingList();
        if (bookingList != null){
            for (final BookingEntity bookingEntity : bookingList) {
                bookingEntity.setRoomList(null);
            }
        }
    }
}
