package info.dmerej.coffeeshop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record Payment(UUID orderId, BigDecimal amount, CreditCard creditCard, LocalDate paid) {
}
