package Rios.tech.Noctra.util;

import java.math.BigDecimal;

public enum SubscriptionType {
    BASIC(1, new BigDecimal("4.99")),
    STANDARD(1, new BigDecimal("8.99")),
    PREMIUM(1, new BigDecimal("13.99"));

    private final int durationMonths;
    private final BigDecimal price;

    SubscriptionType(int durationMonths, BigDecimal price) {
        this.durationMonths = durationMonths;
        this.price = price;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public BigDecimal getPrice() {
        return price;
    }
}