package com.dev.esthomy.models.enums;

import lombok.Getter;

@Getter
public enum AestheticType {
    HAIR_TRANSPLANT(0),
    NOSE_AESTHETIC(1),
    BREAST_AESTHETIC(2),
    DENTAL_AESTHETIC(3),
    EAR_AESTHETIC(4),
    OTHER(5);

    private final int value;

    AestheticType(int value) {
        this.value = value;
    }

    public static AestheticType fromValue(final int value) {
        for (AestheticType type : AestheticType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

}
