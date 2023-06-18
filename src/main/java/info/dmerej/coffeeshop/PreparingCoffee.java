package info.dmerej.coffeeshop;

import java.util.UUID;

public interface PreparingCoffee {
    Order startPreparingOrder(UUID orderId);

    Order finishPreparingOrder(UUID orderId);
}

