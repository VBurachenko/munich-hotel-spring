package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.BookingConverter;
import com.burachenko.munichhotel.converter.impl.InvoiceConverter;
import com.burachenko.munichhotel.converter.impl.RoomConverter;
import com.burachenko.munichhotel.converter.impl.UserConverter;
import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.UserEntity;
import com.burachenko.munichhotel.repository.UserRepository;
import com.burachenko.munichhotel.tool.MockData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private final UserConverter userConverter = new UserConverter(new BookingConverter(new InvoiceConverter(), new RoomConverter()));

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUsersList() {
    }

    @Test
    public void getUser() {

    }

    @Test
    public void createUser() {
        final UserEntity userEntity = MockData.userEntity();
        doReturn(userEntity).when(userRepository).save(any(UserEntity.class));
        userService.createUser(new UserDto());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void updateUser() {
    }
}