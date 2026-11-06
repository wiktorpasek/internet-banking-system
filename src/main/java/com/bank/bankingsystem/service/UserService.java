package com.bank.bankingsystem.service;

import com.bank.bankingsystem.dto.CreateUserRequest;
import com.bank.bankingsystem.dto.UserResponse;
import com.bank.bankingsystem.entity.User;
import com.bank.bankingsystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(user -> new UserResponse(user.getId(),user.getFirstName()
        ,user.getLastName(),user.getPesel(),user.getEmail(),user.getCreatedAt())).toList();
    }

    public UserResponse createUser(CreateUserRequest request) {
        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPesel(request.pesel());
        user.setPassword(passwordEncoder.encode(request.password()));
        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(),savedUser.getFirstName(),savedUser.getLastName(),savedUser.getPesel(),
        savedUser.getEmail(),savedUser.getCreatedAt());
    }
}