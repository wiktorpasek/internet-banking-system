package com.bank.bankingsystem.service;

import com.bank.bankingsystem.entity.Account;
import com.bank.bankingsystem.entity.Transaction;
import com.bank.bankingsystem.repository.AccountRepository;
import com.bank.bankingsystem.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import static org.mockito.Mockito.*;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import com.bank.bankingsystem.dto.TransactionResponse;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest
{
@Mock
private TransactionRepository transactionRepository;

@Mock
private AccountRepository accountRepository;

@InjectMocks
private TransactionService transactionService;

@Test
    void shouldThrowExceptionWhenSenderHasInsufficientFunds() {
    //Given
    Account sender = new Account();
    sender.setId(1L);
    sender.setBalance(new BigDecimal("50.00"));

    Account receiver = new Account();
    receiver.setId(2L);
    receiver.setBalance(new BigDecimal("100.00"));

    when(accountRepository.findById(1L)).thenReturn(Optional.of(sender));
    when(accountRepository.findById(2L)).thenReturn(Optional.of(receiver));

    //When Then
    assertThrows(IllegalArgumentException.class, ()
            -> {
        transactionService.makeTransfer(1L,
                2L, new BigDecimal("200.00"), "Przelew Testowy");
    });

    verify(transactionRepository, never()).save(any());
}
    @Test
    void shouldTransferMoneySuccessfully(){

        //Given
        Account sender = new Account();
        sender.setId(1L);
        sender.setBalance(new BigDecimal("500.00"));

        Account receiver = new Account();
        receiver.setId(2L);
        receiver.setBalance(new BigDecimal("100.00"));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(receiver));

        Transaction savedTransaction = new Transaction();
        savedTransaction.setId(1L);
        savedTransaction.setAmount(new BigDecimal("200.00"));
        savedTransaction.setSenderAccount(sender);
        savedTransaction.setReceiverAccount(receiver);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);

        //When
        transactionService.makeTransfer(1L,2L,new BigDecimal ("200.00"),
                "Przelew Testowy poprawany");

        //Then
        assertEquals(new BigDecimal("300.00"),sender.getBalance());
        assertEquals(new BigDecimal("300.00"),receiver.getBalance());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }
    @Test
    void shouldReturnAccountHistorySuccessfully() {
        //Given
        Account sender = new Account();
        sender.setId(1L);

        Account receiver = new Account();
        receiver.setId(2L);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(sender));

        Transaction savedTransaction = new Transaction();
        savedTransaction.setId(10L);
        savedTransaction.setAmount(new BigDecimal("150.00"));
        savedTransaction.setTitle("Zakupy");
        savedTransaction.setSenderAccount(sender);
        savedTransaction.setReceiverAccount(receiver);

        when(transactionRepository.findBySenderAccountIdOrReceiverAccountId(1L,1L))
                .thenReturn(List.of(savedTransaction));

        //When
        List<TransactionResponse> history = transactionService.getAccountHistory(1L);
        //Then
        assertEquals(1,history.size());
        assertEquals(savedTransaction.getTitle(),history.get(0).title());
    }
}