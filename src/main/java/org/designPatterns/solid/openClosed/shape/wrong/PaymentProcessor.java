package org.designPatterns.solid.openClosed.shape.wrong;

public class PaymentProcessor {
    public void pay(String type) {
        if (type.equals("CreditCard")) {
            // Credit card logic
        } else if (type.equals("UPI")) {
            // UPI logic
        } else if (type.equals("PayPal")) {
            // PayPal logic
        }
    }

}
