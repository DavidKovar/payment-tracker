package cz.dkovar.paymenttracker;

/**
 * @author dkovar
 */
public class PaymentFormatException extends IllegalArgumentException {
    public PaymentFormatException(String message) {
        super(message);
    }
}
