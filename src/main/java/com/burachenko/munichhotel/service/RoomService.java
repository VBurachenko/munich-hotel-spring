package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.dbo.Room;
import com.burachenko.munichhotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRoomsList(){
        return roomRepository.findAll();
    }

    public void createRoom(final Room room){
        roomRepository.save(room);
    }
}
