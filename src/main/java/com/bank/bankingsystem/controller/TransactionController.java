package com.bank.bankingsystem.controller;

import com.bank.bankingsystem.dto.TransferRequest;
import com.bank.bankingsystem.entity.Transaction;
import com.bank.bankingsystem.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")

public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction makeTransaction(@RequestBody TransferRequest request) {
        return transactionService.makeTransfer(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount(),
                request.getTitle()
        );
    }
}
