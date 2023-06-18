package info.dmerej.coffeeshop;

import java.util.List;
import java.util.UUID;

public class Order {
    private final Location location;
    private final List<LineItem> items;
    private final UUID id = UUID.randomUUID();
    private final Status status = Status.PAYMENT_EXPECTED;

    public Order(Location location, List<LineItem> items) {
        this.location = location;
        this.items = items;
    }

}

