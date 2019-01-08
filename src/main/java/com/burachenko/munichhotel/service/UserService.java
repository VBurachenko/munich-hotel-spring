package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.UserAccountConverter;
import com.burachenko.munichhotel.converter.impl.UserConverter;
import com.burachenko.munichhotel.dto.UserAccountDto;
import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.UserAccountEntity;
import com.burachenko.munichhotel.entity.UserEntity;
import com.burachenko.munichhotel.repository.UserAccountRepository;
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

    private final UserAccountRepository userAccountRepository;
    private final UserAccountConverter userAccountConverter;

    public List<UserDto> getUserList(){
        final List<UserDto> dtoUserList = new ArrayList<>();
        for (final UserEntity user : userRepository.findAll()) {
            final UserDto userDto = userConverter.convertToDto(user);
            dtoUserList.add(userDto);
        }
        return dtoUserList;
    }

    public UserAccountDto getUserAccount(final long id){
        final Optional<UserAccountEntity> userAccount = userAccountRepository.findById(id);
        return userAccount.map(userAccountConverter::convertToDto).orElse(null);
    }

    public UserDto getUser(final long id){
        final Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(userConverter::convertToDto).orElse(null);
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
        final Optional<UserEntity> userEntity = userRepository.findUserByEmail(email);
        return userEntity.map(userConverter::convertToDto).orElse(null);

    }

    public UserDto registerNewUser(final UserDto userDto){
        final Optional <UserEntity> userByEmail = userRepository.findUserByEmail(userDto.getEmail());
        final Optional <UserEntity> userByTelNum = userRepository.findByTelNum(userDto.getTelNum());
        if (userByEmail.isPresent() || userByTelNum.isPresent()){
            return null;
        }
        return createUser(userDto);
    }

    public UserDto createUser(final UserDto userDto){
        final UserEntity justCreatedUser = userRepository.save(userConverter.convertToEntity(userDto));
        return userConverter.convertToDto(justCreatedUser);
    }

    public UserDto changeBlockUser(final long userId, final int blockIndex){
        final UserDto userDto = getUser(userId);
        if (userDto != null){
            userDto.setBlocking(blockIndex);
            return updateUser(userDto, userId);
        }
        return null;
    }
}
