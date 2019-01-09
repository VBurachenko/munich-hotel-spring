package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.RoomConverter;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.entity.RoomEntity;
import com.burachenko.munichhotel.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomConverter roomConverter;

    public List<RoomDto> getAllRoomList(){
        return roomRepository.findAll().stream().map(roomConverter::convertToDto).collect(Collectors.toList());
    }

    public List<RoomDto> getFreeRoomsList(){
        return new ArrayList<>();
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

    @Transactional
    public RoomDto updateRoom(final RoomDto roomDto, final long id){
        final Optional<RoomEntity> roomEntity = roomRepository.findById(id);
        if (roomEntity.isPresent()){
            roomDto.setId(id);
        }
        return roomConverter.convertToDto(roomRepository.save(roomConverter.convertToEntity(roomDto)));
    }

    public RoomDto changeRoomAvailability(final long id, final boolean availabilityValue){
        final RoomDto roomDto = getRoom(id);
        if (roomDto != null){
            roomDto.setIsAvailable(availabilityValue);
        }
        return updateRoom(roomDto, id);
    }

}
