package com.github.fehwilinando.springhateoas.repository;

import com.github.fehwilinando.springhateoas.model.Payment;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentRepository {
    private static Map<UUID, Payment> payments = new HashMap<>();

    static {
        insert(new Payment(new BigDecimal("39.9")));
        insert(new Payment(new BigDecimal("19.9")));
        insert(new Payment(new BigDecimal("20.0")));

    }

    private static void insert(Payment payment) {
        UUID id = payment.getId();
        payments.put(id, payment);
    }

    public List<Payment> findAll() {
        return new ArrayList<>(payments.values());
    }

    public Optional<Payment> findById(UUID id) {
        return Optional.ofNullable(payments.get(id));
    }
}
