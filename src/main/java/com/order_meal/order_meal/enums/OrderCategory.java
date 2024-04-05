package com.order_meal.order_meal.enums;

public enum OrderCategory {
    ON(2),
    ONGOING(1),
    END(0);

    private final int value;

    OrderCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
