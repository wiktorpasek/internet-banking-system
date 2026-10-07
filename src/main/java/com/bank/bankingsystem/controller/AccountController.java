package com.bank.bankingsystem.controller;

import com.bank.bankingsystem.entity.Account;
import com.bank.bankingsystem.entity.Transaction;
import com.bank.bankingsystem.service.AccountService;
import com.bank.bankingsystem.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountController(AccountService accountService , TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Account> getAccounts(){
        return accountService.getAllAccounts();
    }

    @PostMapping
    public Account addAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> getAccountHistory(@PathVariable("id") Long accountId) {
        return transactionService.getAccountHistory(accountId);
    }
}
