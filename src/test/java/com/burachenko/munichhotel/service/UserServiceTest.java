package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.UserConverter;
import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.UserEntity;
import com.burachenko.munichhotel.repository.UserRepository;
import com.burachenko.munichhotel.tool.MockData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserConverter userConverter;

    @InjectMocks
    private UserService userService;

    @Test
    public void getUsersList() {
        final List<UserEntity> findAllResult = new ArrayList<>();
        final UserEntity userEntity = MockData.userEntity();
        findAllResult.add(userEntity);
        findAllResult.add(userEntity);
        doReturn(findAllResult).when(userRepository).findAll();

        final List<UserDto> userList = userService.getUsersList();

        verify(userRepository, times(1)).findAll();
        assertEquals(findAllResult.size(), userList.size());
        for (final UserDto dto : userList) {
            assertEquals(userEntity.getEmail(), dto.getEmail());
            assertEquals(userEntity.getTelNum(), dto.getTelNum());
        }
    }

    @Test
    public void getUser() {
        final UserEntity userEntity = new UserEntity();
        Mockito.when(userRepository.findById(10L)).thenReturn(Optional.of(userEntity));
        final UserDto userDto = userService.getUser(10L);
        assertEquals(userDto.getEmail(), userEntity.getEmail());
        assertEquals(userDto.getTelNum(), userEntity.getTelNum());
    }

    @Test
    public void createUser() {
        userService.createUser(new UserDto());
        Mockito.verify(userRepository).save(Mockito.any());
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void updateUser() {
    }
}