package com.bank.bankingsystem.controller;

import com.bank.bankingsystem.entity.Account;
import com.bank.bankingsystem.service.AccountService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAccounts(){
        return accountService.getAllAccounts();
    }

    @PostMapping
    public Account addAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }
}
