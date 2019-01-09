package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.UserAccountConverter;
import com.burachenko.munichhotel.converter.impl.UserConverter;
import com.burachenko.munichhotel.dto.UserAccountDto;
import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.UserAccountEntity;
import com.burachenko.munichhotel.entity.UserEntity;
import com.burachenko.munichhotel.enumeration.UserBlocking;
import com.burachenko.munichhotel.repository.UserAccountRepository;
import com.burachenko.munichhotel.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    private final UserAccountRepository userAccountRepository;
    private final UserAccountConverter userAccountConverter;

    public List<UserDto> getUserList(final String filterString){
        if (!filterString.isEmpty()){
            List<UserDto> list = new ArrayList<>();
            for (UserEntity userEntity : userRepository.findUserByEmailOrTelNum(filterString)) {
                UserDto convertToDto = userConverter.convertToDto(userEntity);
                list.add(convertToDto);
            }
            return list;
        }
        return getUserList();
    }

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

    public boolean deleteUser(final UserDto userDto){
        userRepository.delete(userConverter.convertToEntity(userDto));
        return userRepository.exists(Example.of(userConverter.convertToEntity(userDto)));
    }

    public UserDto updateUser(final UserDto userDto, final long id){
        final Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()){
            userDto.setId(id);
            return userConverter.convertToDto(userRepository.save(userConverter.convertToEntity(userDto)));
        }
        return null;
    }

    public UserAccountDto updateUserAccount(final UserAccountDto userAccountDto, final long id){
        final Optional<UserAccountEntity> userAccountEntity = userAccountRepository.findById(id);
        if (userAccountEntity.isPresent()){
            userAccountDto.setId(id);
            return userAccountConverter.convertToDto(userAccountRepository.save(userAccountConverter.convertToEntity(userAccountDto)));
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
        if (!userByEmail.isPresent() && !userByTelNum.isPresent()){
            final UserEntity justCreatedUser = userRepository.save(userConverter.convertToEntity(userDto));
            return userConverter.convertToDto(justCreatedUser);
        }
        return null;
    }

    public UserDto changeBlockUser(final long userId, final UserBlocking userBlocking){
        final UserDto userDto = getUser(userId);
        if (userDto != null){
            userDto.setBlocking(userBlocking.ordinal());
            return updateUser(userDto, userId);
        }
        return null;
    }

}
