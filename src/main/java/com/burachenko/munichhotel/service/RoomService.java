package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.RoomConverter;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.entity.RoomEntity;
import com.burachenko.munichhotel.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomConverter roomConverter;

    public List<RoomEntity> getRoomsList(){
        return roomRepository.findAll();
    }

    public RoomDto createRoom(final RoomEntity room){
        return roomConverter.convertToDto(roomRepository.save(room));
    }
}
