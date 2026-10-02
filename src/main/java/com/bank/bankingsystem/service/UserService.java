package com.bank.bankingsystem.service;

import com.bank.bankingsystem.entity.User;
import com.bank.bankingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User createUser(User user){
        return userRepository.save(user);
    }
}