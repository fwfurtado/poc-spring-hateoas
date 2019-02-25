package com.github.fehwilinando.springhateoas.view;

import com.github.fehwilinando.springhateoas.controller.PaymentController;
import com.github.fehwilinando.springhateoas.model.Payment;
import com.github.fehwilinando.springhateoas.model.PaymentStatus;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PaymentView extends ResourceSupport {

    private final Payment payment;

    public PaymentView(Payment payment) {
        this.payment = payment;

        UUID id = payment.getId();

        add(linkTo(PaymentController.class).slash(payment).withSelfRel());

        switch (payment.getStatus()) {
            case CREATED:
                add(linkTo(methodOn(PaymentController.class).confirm(id)).withRel("confirm"));
            case CONFIRMED:
                add(linkTo(methodOn(PaymentController.class).cancel(id)).withRel("cancel"));

        }
    }


    public BigDecimal getValue() {
        return payment.getValue();
    }

    public PaymentStatus getStatus() {
        return payment.getStatus();
    }

    @Override
    public Link getId() {
        return linkTo(PaymentController.class)
                    .slash(payment)
                        .withSelfRel();
    }
}
