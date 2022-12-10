package ChainOfResposibility;

import lombok.Setter;

public abstract class Handler {
    private final int quantity;
    @Setter
    private Handler next;

    public Handler(int quantity) {
        this.quantity = quantity;
    }

    public void process(int amount) {
        if (amount % quantity > 0 && next == null) {
            throw new IllegalArgumentException();
        } else if (next != null) {
            next.process(amount % quantity);
            System.out.println(amount / quantity + " * " + quantity);
        } else {
            System.out.println(amount / quantity + " * " + quantity);
        }
    }
}
