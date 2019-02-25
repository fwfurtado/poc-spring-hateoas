package com.github.fehwilinando.springhateoas.model;

import org.springframework.hateoas.Identifiable;

import java.math.BigDecimal;
import java.util.UUID;

import static com.github.fehwilinando.springhateoas.model.PaymentStatus.CANCELLED;
import static com.github.fehwilinando.springhateoas.model.PaymentStatus.CONFIRMED;
import static com.github.fehwilinando.springhateoas.model.PaymentStatus.CREATED;

public class Payment implements Identifiable<UUID> {
    private UUID id;
    private BigDecimal value;
    private PaymentStatus status;

    public Payment(BigDecimal value) {
        this.value = value;
        this.status = CREATED;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void confirm() {
        if (status.equals(CANCELLED)) {
            throw new IllegalStateException("Cancelled payment cannot be confirmed");
        }

        this.status = CONFIRMED;
    }

    public void cancelled() {
        if (status.in(CANCELLED)) {
            throw new IllegalStateException("Payment already in cancelled status");
        }

        this.status = CANCELLED;
    }
}
