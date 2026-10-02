    package com.bank.bankingsystem.entity;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.fasterxml.jackson.annotation.JsonProperty;
    import jakarta.persistence.*;
    import org.hibernate.annotations.CreationTimestamp;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;

    @Entity
    @Table(name = "accounts")
    public class Account {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="user_id",nullable = false)
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private User user;

        @Column(name="account_number",nullable = false,unique = true,length = 26)
        private String accountNumber;

        @Column(nullable = false,precision = 19,scale = 2)
        private BigDecimal balance;

        @Column(nullable = false,length = 3)
        private String currency;

        @Column(nullable = false,length = 10)
        private LocalDateTime createdAt = LocalDateTime.now();

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public User getUser() {
            return user;
        }
        public void setUser(User user) {
            this.user = user;
        }
        public String getAccountNumber() {
            return accountNumber;
        }
        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }
        public BigDecimal getBalance() {
            return balance;
        }
        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }
        public String getCurrency() {
            return currency;
        }
        public void setCurrency(String currency) {
            this.currency = currency;
        }
        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }
    }
