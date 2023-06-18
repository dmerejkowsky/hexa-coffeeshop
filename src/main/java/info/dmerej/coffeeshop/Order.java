package info.dmerej.coffeeshop;

import java.util.List;
import java.util.UUID;

public class Order {
    private final Location location;
    private final List<LineItem> items;
    private final UUID id;
    private final Status status;

    public Order(Location location, List<LineItem> items) {
        this.location = location;
        this.items = items;
        this.id = UUID.randomUUID();
        this.status = Status.PAYMENT_EXPECTED;
    }

    private Order(Location location, List<LineItem> items, UUID id, Status status) {
        this.location = location;
        this.items = items;
        this.id = id;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public Order markPaid() {
        if (status != Status.PAYMENT_EXPECTED) {
            throw new IllegalStateException(
                String.format("Expected status to be PAYMENT_EXPECTED, got %s", status)
            );
        }
        return new Order(this.location, this.items, this.id, Status.PAID);
    }

    public Order markBeingPrepared() {
        return new Order(this.location, this.items, this.id, Status.PREPARING);
    }

    public UUID getId() {
        return this.id;
    }

    public List<LineItem> getItems() {
        return items;
    }
}

