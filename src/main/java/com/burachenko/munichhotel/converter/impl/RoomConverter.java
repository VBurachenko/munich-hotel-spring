package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.DtoDboConverter;
import com.burachenko.munichhotel.dbo.Room;
import com.burachenko.munichhotel.dto.RoomDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoomConverter implements DtoDboConverter<Room, RoomDto> {

    @Override
    public RoomDto convertToDto(Room room) {
        final RoomDto roomDto = new RoomDto();
        BeanUtils.copyProperties(room, roomDto);
        return roomDto;
    }

    @Override
    public Room convertToDbo(RoomDto roomDto) {
        final Room room = new Room();
        BeanUtils.copyProperties(roomDto, room);
        return room;
    }
}
