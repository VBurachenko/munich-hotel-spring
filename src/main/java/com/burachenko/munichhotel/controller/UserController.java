package com.burachenko.munichhotel.controller;

import com.burachenko.munichhotel.dbo.User;
import com.burachenko.munichhotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public List<User> getAllUsers(){
        return userService.getUsersList();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable final long id){
        return userService.getUser(id);
    }

    @PostMapping("/create")
    public String createUser(@RequestBody final User user){
        if (userService.createUser(user)){
            return "User was created.";
        }
        return "Fail of user creation.";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@RequestBody final User user, @PathVariable final long id){
        if (userService.updateUser(user, id)){
            return "User with id " + id + " was updated.";
        }
        return "Fail of user update.";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable final long id){
        String responsePart = "User with id " + id;
        if (userService.deleteUser(id)){
            return responsePart + " was deleted.";
        }
        return responsePart + " is absent.";
    }
}
