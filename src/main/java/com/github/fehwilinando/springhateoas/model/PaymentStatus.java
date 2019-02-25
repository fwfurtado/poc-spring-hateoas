package com.github.fehwilinando.springhateoas.model;

import java.util.Arrays;

public enum PaymentStatus {
    CREATED,
    CONFIRMED,
    CANCELLED,
    FINISHED;

    public boolean in(PaymentStatus... others) {
        return Arrays.asList(others).contains(this);
    }

}
