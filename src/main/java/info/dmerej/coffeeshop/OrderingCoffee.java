package info.dmerej.coffeeshop;

import java.util.UUID;

public interface OrderingCoffee {
    Order placeOrder(Order order);

    Order updateOrder(UUID orderId, Order order);

    void cancelOrder(UUID orderId);

    Payment payOrder(UUID orderId, CreditCard creditCard);

    Receipt readReceipt(UUID orderId);

    Order takeOrder(UUID orderId);
}
