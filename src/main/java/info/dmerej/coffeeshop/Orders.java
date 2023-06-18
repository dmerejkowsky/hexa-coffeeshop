package info.dmerej.coffeeshop;

import java.util.UUID;

public interface Orders {
    Order findOrderById(UUID orderId) throws OrderNotFound;

    Order save(Order order);

    void deleteById(UUID orderId);
}
