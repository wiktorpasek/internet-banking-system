    package com.bank.bankingsystem.service;

    import com.bank.bankingsystem.dto.TransactionResponse;
    import com.bank.bankingsystem.entity.Account;
    import com.bank.bankingsystem.entity.Transaction;
    import com.bank.bankingsystem.repository.AccountRepository;
    import com.bank.bankingsystem.repository.TransactionRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.math.BigDecimal;
    import java.util.List;

    @Service
    public class TransactionService {

        private final TransactionRepository transactionRepository;
        private final AccountRepository accountRepository;

        public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
            this.transactionRepository = transactionRepository;
            this.accountRepository = accountRepository;
        }

        @Transactional
            public TransactionResponse makeTransfer(Long senderAccountId, Long receiverAccountId, BigDecimal amount, String title) {
            Account sender = accountRepository.findById(senderAccountId)
                    .orElseThrow(() -> new IllegalArgumentException("Konto nadawcy nie istnieje"));
            Account receiver = accountRepository.findById(receiverAccountId)
                    .orElseThrow(() -> new IllegalArgumentException("Konto odbiorcy nie istnieje"));

            if (sender.getBalance().compareTo(amount) < 0) {
                throw new IllegalArgumentException("Niewystarczające środki na koncie nadawcy.");
            }

            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));

            accountRepository.save(sender);
            accountRepository.save(receiver);

            Transaction transaction = new Transaction();
            transaction.setSenderAccount(sender);
            transaction.setReceiverAccount(receiver);
            transaction.setAmount(amount);
            transaction.setTitle(title);

            Transaction savedTransaction = transactionRepository.save(transaction);
            return mapToResponse(savedTransaction);
        }
        public List<TransactionResponse> getAccountHistory(Long AccountId) {
            accountRepository.findById(AccountId)
                    .orElseThrow(() -> new IllegalArgumentException("Konto nie istnieje"));
            return transactionRepository.findBySenderAccountIdOrReceiverAccountId(AccountId,
                    AccountId).stream().map(this::mapToResponse).toList();
        }
        private TransactionResponse mapToResponse(Transaction transaction) {

            return new TransactionResponse(transaction.getId(),
                    transaction.getSenderAccount().getId(),transaction.getReceiverAccount().getId(),
                    transaction.getAmount(),transaction.getTitle(),transaction.getTransactionDate()
            );
        }
    }
