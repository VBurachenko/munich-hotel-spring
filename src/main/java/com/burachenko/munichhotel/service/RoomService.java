package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.RoomConverter;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.entity.RoomEntity;
import com.burachenko.munichhotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
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
        return null;
    }

    public List<RoomDto> getFreeRoomsList(final LocalDate checkIn, final LocalDate checkOut){
        return new ArrayList<>();
    }



    public RoomDto changeRoomAvailability(final long id, final boolean availabilityValue){
        final RoomDto roomDto = findById(id);
        if (roomDto != null){
            roomDto.setIsAvailable(availabilityValue);
        }
        return save(roomDto);
    }

}
