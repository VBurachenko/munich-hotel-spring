package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.dbo.User;
import com.burachenko.munichhotel.repository.UserRepository;
import com.burachenko.munichhotel.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsersList(){
        return userRepository.findAll();
    }

    public User getUser(final long id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            throw new ServiceException("user with id " + id + " is absent");
        }
        return user.get();
    }

    public boolean createUser(final User user){
        User createdUser = userRepository.save(user);
        return createdUser != null;
    }

    public boolean deleteUser(final long id){
        userRepository.deleteById(id);
        Optional <User> user = userRepository.findById(id);
        return !user.isPresent();
    }

    public boolean updateUser(User user, final long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            user.setUserId(id);
            return userRepository.save(user) != null;
        }
        return false;
    }




}
