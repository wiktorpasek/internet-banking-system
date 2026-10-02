package com.bank.bankingsystem.controller;

import com.bank.bankingsystem.entity.User;
import com.bank.bankingsystem.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.createUser(user);
    }
}
