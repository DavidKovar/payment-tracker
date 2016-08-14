package cz.dkovar.paymenttracker.reader;

import cz.dkovar.paymenttracker.PaymentHolder;

import static java.util.Objects.requireNonNull;

/**
 * @author dkovar
 */
public abstract class PaymentReader implements Runnable {

    public PaymentHolder payments;

    public PaymentReader(PaymentHolder payments) {
        this.payments = requireNonNull(payments, "The parameter payments is required!");
    }

    public PaymentHolder getPaymentHolder() {
        return payments;
    }

}
