package com.bank.bankingsystem.dto;

import java.time.LocalDateTime;

public record UserResponse (
        Long id,
        String firstName,
        String lastName,
        String pesel,
        String email,
        LocalDateTime createdAt
) {}
