package com.bank.bankingsystem.service;

import com.bank.bankingsystem.entity.Account;
import com.bank.bankingsystem.repository.AccountRepository;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service

public class AccountService {
    private final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
    public Account createAccount(Account account){
        return accountRepository.save(account);
    }
}

