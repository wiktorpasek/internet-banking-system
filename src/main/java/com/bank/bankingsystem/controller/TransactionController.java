package com.bank.bankingsystem.controller;

import com.bank.bankingsystem.dto.TransferRequest;
import com.bank.bankingsystem.entity.Transaction;
import com.bank.bankingsystem.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")

public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction makeTransaction(@Valid @RequestBody TransferRequest request) {
        return transactionService.makeTransfer(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount(),
                request.getTitle()

        );
    }
}
