package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.UserConverter;
import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.User;
import com.burachenko.munichhotel.exception.ServiceException;
import com.burachenko.munichhotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public List<UserDto> getUsersList(){
        final List<UserDto> list = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            UserDto userDto = userConverter.convertToDto(user);
            list.add(userDto);
        }
        return list;
    }

    public UserDto getUser(final long id){
        final Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            throw new ServiceException("user with id " + id + " is absent");
        }
        return userConverter.convertToDto(user.get());
    }

    public boolean createUser(final UserDto userDto){
        final User createdUser = userRepository.save(userConverter.convertToDbo(userDto));
        return createdUser != null;
    }

    public boolean deleteUser(final long id){
        userRepository.deleteById(id);
        final Optional <User> user = userRepository.findById(id);
        return !user.isPresent();
    }

    public boolean updateUser(final UserDto userDto, final long id){
        final Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userDto.setUserId(id);
            return userRepository.save(userConverter.convertToDbo(userDto)) != null;
        }
        return false;
    }




}
