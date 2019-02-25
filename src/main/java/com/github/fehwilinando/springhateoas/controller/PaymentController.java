package com.github.fehwilinando.springhateoas.controller;

import com.github.fehwilinando.springhateoas.model.Payment;
import com.github.fehwilinando.springhateoas.repository.PaymentRepository;
import com.github.fehwilinando.springhateoas.view.PaymentView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class PaymentController {

    private PaymentRepository repository;

    public PaymentController(PaymentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<PaymentView> list() {
        return repository.findAll().stream().map(PaymentView::new).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentView> show(@PathVariable UUID id) {
        return repository.findById(id).map(PaymentView::new).map(ok()::body).orElseGet(notFound()::build);
    }


    @PutMapping("{id}/confirm")
    public ResponseEntity<?> confirm(@PathVariable UUID id) {
        return updateStatusOfPaymentTo(id, Payment::confirm);
    }


    @DeleteMapping("{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable UUID id) {
        return updateStatusOfPaymentTo(id, Payment::cancelled);
    }

    private ResponseEntity<?> updateStatusOfPaymentTo(UUID id, Consumer<Payment> ifExists) {
        Optional<Payment> optionalPayment = repository.findById(id);

        optionalPayment.ifPresent(ifExists);

        return optionalPayment.map(payment -> noContent().build()).orElseGet(notFound()::build);
    }
}
