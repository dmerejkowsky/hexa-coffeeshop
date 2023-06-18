package info.dmerej.coffeeshop;

import java.util.UUID;

public interface Payments {
    Payment findPaymentByOrderId(UUID orderId);

    Payment save(Payment payment);
}
