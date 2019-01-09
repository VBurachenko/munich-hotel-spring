package com.burachenko.munichhotel.controller;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public List<UserDto> getAllUsers(){
        return userService.getUserList();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable final long id){
        return userService.getUser(id);
    }

    @PostMapping("/create")
    public UserDto createUser(@RequestBody final UserDto userDto){
        return userService.registerNewUser(userDto);
    }

    @PutMapping("/update/{id}")
    public UserDto updateUser(@RequestBody final UserDto userDto, @PathVariable final long id){
        return userService.updateUser(userDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable final long id){
        final String responsePart = "UserEntity with id " + id;
        if (userService.deleteUser(id)){
            return responsePart + " was deleted.";
        }
        return responsePart + " is absent.";
    }

    @GetMapping("/email/{email:[^@]+@[^@]+\\.[^@]+}")
    public UserDto getUserByEmail(@PathVariable final String email){
        return userService.findUserByEmail(email);
    }
}
