package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.UserConverter;
import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.UserEntity;
import com.burachenko.munichhotel.repository.UserRepository;
import com.burachenko.munichhotel.tool.MockData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    private final UserConverter userConverter = new UserConverter();

    @InjectMocks
    private UserService userService;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUsersList() {
        final List<UserEntity> findAllResult = new ArrayList<>();
        final UserEntity userEntity = MockData.userEntity();
        findAllResult.add(userEntity);
        findAllResult.add(userEntity);
        doReturn(findAllResult).when(userRepository).findAll();

        final List<UserDto> userList = userService.getUserList();

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
        final UserEntity userEntity = new UserEntity();
        userEntity.setName("name");
        userEntity.setSurname("surname");

        doReturn(userEntity).when(userRepository).save(any(UserEntity.class));

        userService.registerNewUser(new UserDto());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void deleteUser() {
        doNothing().when(userRepository).deleteById(any(Long.class));
        userService.deleteUser(10L);
        Mockito.verify(userRepository, times(1)).deleteById(any(Long.class));
    }

    @Test
    public void updateUser() {

        final UserDto userDto = new UserDto();

        final long userId = 10L;
        userDto.setId(userId);
        userDto.setEmail("email");
        userDto.setTelNum("num");

        final UserEntity retrievedUser = new UserEntity();

        when(userRepository.findById(userId)).thenReturn(Optional.of(retrievedUser));
        when(userRepository.save(retrievedUser)).thenReturn(retrievedUser);

        final UserDto updatedUser = userService.updateUser(userDto);

        Assert.assertEquals("email", retrievedUser.getEmail());
    }


    @Test
    public void findUserByEmail() {
        final UserEntity userEntity = new UserEntity();
        Mockito.when(userRepository.findUserByEmail("email")).thenReturn(Optional.of(userEntity));
        final UserDto userDto = userService.findUserByEmail("email");
        assertEquals(userDto.getEmail(), userEntity.getEmail());
        assertEquals(userDto.getTelNum(), userEntity.getTelNum());
    }

    @Test
    public void registerNewCustomer() {
    }

    @Test
    public void changeBlockUser() {
    }
}