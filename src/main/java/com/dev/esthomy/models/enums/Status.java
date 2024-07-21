package com.dev.esthomy.models.enums;

import lombok.Getter;

@Getter
public enum Status {
    CANCELLED(2),
    ACCEPTED(1),
    DONE(3),
    IN_PROGRESS(0);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public static Status valueOf(int value) {
        for (Status status : Status.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }
}
