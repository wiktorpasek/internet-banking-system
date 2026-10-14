package com.bank.bankingsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record TransactionResponse(
        Long id,
        Long senderID,
        Long receiverID,
        BigDecimal amount,
        String title,
        LocalDateTime transactionDate
) { }
