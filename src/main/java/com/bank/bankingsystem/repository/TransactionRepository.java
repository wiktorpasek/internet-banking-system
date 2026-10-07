package com.bank.bankingsystem.repository;

import com.bank.bankingsystem.entity.Transaction;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySenderAccountIdOrReceiverAccountId(Long senderId, Long receiverId);
}