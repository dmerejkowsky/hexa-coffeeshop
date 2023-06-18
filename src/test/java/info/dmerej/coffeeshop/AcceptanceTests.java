package info.dmerej.coffeeshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTests {
    private Orders orders;
    private Payments payments;
    private OrderingCoffee customer;
    private PreparingCoffee barista;

    @BeforeEach
    void setup() {
        orders = new InMemoryOrders();
        payments = new InMemoryPayments();
        customer = new CoffeeShop(orders, payments);
        barista = new CoffeeMachine(orders);
    }

    @Test
    void customer_can_pay_the_order() {
        var order = anOrder();
        var existingOrder = orders.save(order);
        var creditCard = aCreditCard();

        var payment = customer.payOrder(existingOrder.getId(), creditCard);

        assertThat(payment.orderId()).isEqualTo(existingOrder.getId());
        assertThat(payment.creditCard()).isEqualTo(creditCard);
        assertThat(payment.amount()).isEqualTo(BigDecimal.valueOf(10));
    }

    @Test
    void barista_can_start_preparing_paid_order() {
        var paidOrder = aPaidOrder();
        var existingOrder = orders.save(paidOrder);

        var orderInPreparation = barista.startPreparingOrder(existingOrder.getId());

        assertThat(orderInPreparation.getStatus()).isEqualTo(Status.PREPARING);
    }

    private CreditCard aCreditCard() {
        return new CreditCard(
            "Alice", "01234", Month.APRIL, Year.now()
        );
    }

    private Order anOrder() {
        var smallCoffee = new LineItem(Drink.Coffee, Milk.WithMilk, Size.Small, 1);
        return new Order(Location.InStore, List.of(smallCoffee));
    }

    private Order aPaidOrder() {
        var order = anOrder();
        return order.markPaid();
    }
}
