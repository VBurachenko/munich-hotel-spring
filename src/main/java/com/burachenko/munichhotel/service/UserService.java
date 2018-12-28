package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.UserConverter;
import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.UserEntity;
import com.burachenko.munichhotel.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public List<UserDto> getUsersList(){
        final List<UserDto> list = new ArrayList<>();
        for (final UserEntity user : userRepository.findAll()) {
            final UserDto userDto = userConverter.convertToDto(user);
            list.add(userDto);
        }
        return list;
    }

    public UserDto getUser(final long id){
        final Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()){
            return userConverter.convertToDto(userEntity.get());
        }
        return null;
    }

    public UserDto createUser(final UserDto userDto){
        final UserEntity justCreatedUser = userRepository.save(userConverter.convertToEntity(userDto));
        return userConverter.convertToDto(justCreatedUser);
    }

    public boolean deleteUser(final long id){
        userRepository.deleteById(id);
        final Optional <UserEntity> user = userRepository.findById(id);
        return !user.isPresent();
    }

    public UserDto updateUser(final UserDto userDto, final long id){
        final Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userDto.setId(id);
            return userConverter.convertToDto(userRepository.save(userConverter.convertToEntity(userDto)));
        }
        return null;
    }

    public UserDto findUserByEmail(final String email){
        final Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userConverter.convertToDto(userEntity.get());
    }

    public UserDto registerNewCustomer(final UserDto userDto){
        final Optional <UserEntity> userByEmail = userRepository.findByEmail(userDto.getEmail());
        final Optional <UserEntity> userByTelNum = userRepository.findByTelNum(userDto.getTelNum());
        if (userByEmail.isPresent() || userByTelNum.isPresent()){
            return null;
        }
        return userConverter.convertToDto(userRepository.save(userConverter.convertToEntity(userDto)));
    }
}
