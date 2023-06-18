package info.dmerej.coffeeshop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CoffeeShop implements OrderingCoffee {
    private final Orders orders;
    private final Payments payments;

    public CoffeeShop(Orders orders, Payments payments) {
        this.orders = orders;
        this.payments = payments;
    }

    @Override
    public Order placeOrder(Order order) {
        return orders.save(order);
    }

    @Override
    public Order updateOrder(UUID orderId, Order order) {
        orders.deleteById(orderId);
        return orders.save(order);
    }

    @Override
    public void cancelOrder(UUID orderId) {
        orders.deleteById(orderId);
    }

    @Override
    public Payment payOrder(UUID orderId, CreditCard creditCard) {
        var order = orders.findOrderById(orderId);

        orders.save(order.markPaid());

        var items = order.getItems();

        var amount = items.stream().map(i -> getPrice(i)).reduce(BigDecimal.ZERO, BigDecimal::add);

        return payments.save(new Payment(orderId, amount, creditCard, LocalDate.now()));
    }

    @Override
    public Receipt readReceipt(UUID orderId) {
        var payment = payments.findPaymentByOrderId(orderId);
        return new Receipt(payment.amount(), payment.paid());
    }

    @Override
    public Order takeOrder(UUID orderId) {
        return null;
    }

    private BigDecimal getPrice(LineItem item) {
        var price = switch (item.drink()) {
            case Coffee -> BigDecimal.valueOf(10);
            case Tea -> BigDecimal.valueOf(5);
        };
        return price;
    }
}

