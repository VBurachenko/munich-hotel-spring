package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.RoomConverter;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.entity.RoomEntity;
import com.burachenko.munichhotel.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomConverter roomConverter;

    public List<RoomEntity> getRoomsList(){
        return roomRepository.findAll();
    }

    public RoomDto createRoom(final RoomDto roomDto){
        final RoomEntity roomEntity = roomRepository.save(roomConverter.convertToEntity(roomDto));
        if (roomEntity != null){
            roomConverter.convertToDto(roomEntity);
        }
        return null;
    }

    public RoomDto getRoom(final Long id){
        final Optional<RoomEntity> roomEntity = roomRepository.findById(id);
        return roomEntity.map(roomConverter::convertToDto).orElse(null);
    }

}
