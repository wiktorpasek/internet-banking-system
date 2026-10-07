package com.bank.bankingsystem.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

public class TransferRequest {
    private Long senderAccountId;
    private Long receiverAccountId;

    @NotNull(message = "Kwota przelewu jest wymagana")
    @Positive(message = "Kwota przelewu musi być większa od 0")
    private BigDecimal amount;

    @NotBlank(message = "Tytuł przelewu nie może być pusty")
    private String title;

    public long getSenderAccountId() {
        return senderAccountId;
    }

    public long getReceiverAccountId() {
        return receiverAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTitle() {
        return title;
    }

    public void setSenderAccountId(long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public void setReceiverAccountId(long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
