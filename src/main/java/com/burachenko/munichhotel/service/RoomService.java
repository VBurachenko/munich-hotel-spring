package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.RoomConverter;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.entity.RoomEntity;
import com.burachenko.munichhotel.enumeration.RoomAvailabilityStatus;
import com.burachenko.munichhotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service("roomService")
public class RoomService extends AbstractService<RoomDto, RoomEntity, RoomRepository>{

    public RoomService(final RoomRepository roomRepository, final RoomConverter roomConverter){
        super(roomRepository, roomConverter);
    }

    @Override
    protected boolean beforeSave(final RoomDto roomDto) {
        return true;
    }

    @Override
    public List<RoomDto> findByFilterParameter(final String filterParameter) {
        try {
            final long id = Long.valueOf(filterParameter);
            return getConverter().convertToDto(getRepository().findAllById(id));
        } catch (NumberFormatException e){
            return Collections.emptyList();
        }
    }

    public List<RoomDto> getFreeRoomsList(final LocalDate checkIn, final LocalDate checkOut){
        return Collections.emptyList();
    }

    public RoomDto changeRoomAvailability(final long id, final RoomAvailabilityStatus isAvailable){
        final RoomDto roomDto = findById(id);
        if (roomDto != null){
            roomDto.setIsAvailable(isAvailable);
        }
        return save(roomDto);
    }

}
