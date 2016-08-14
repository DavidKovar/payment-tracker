package cz.dkovar.paymenttracker;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

import static java.util.Objects.requireNonNull;

/**
 * Container class for holding payments.
 * @author dkovar
 */
public class PaymentHolder {

    private final Collection<Payment> payments;
    private final LineParser lineParser;

    public PaymentHolder(LineParser lineParser, Collection<Payment> payments) {
        this.payments = requireNonNull(payments, "The parameter payments is required!");
        this.lineParser = requireNonNull(lineParser, "The parameter lineParser is required!");
    }

    /**
     * Returns line parser.
     * @return line parser
     */
    public final LineParser getLineParser() {
        return lineParser;
    }

    /**
     * Returns unmodifialbe collection of payments.
     * @return unmodifialbe collection of payments
     */
    public final Collection<Payment> getPayments() {
        return Collections.unmodifiableCollection(payments);
    }

    /**
     * Adds payment from line
     * @param paymentLineEntry
     * @throws PaymentFormatException if {@code line} is not valid representation of a payment record.
     */
    public final void addPayment(String paymentLineEntry) throws PaymentFormatException {
        requireNonNull(paymentLineEntry, "The parameter paymentLineEntry is required!");
        Payment payment = lineParser.parse(paymentLineEntry);
        if(!BigDecimal.ZERO.equals(payment.getAmount())) {
            this.payments.add(lineParser.parse(paymentLineEntry));
        }
    }

    /**
     * Adds payments
     * @param payments
     */
    public final void addPayments(Collection<Payment> payments){
        requireNonNull(payments, "The parameter payments is required!");
        payments.stream().filter(payment -> !BigDecimal.ZERO.equals(payment.getAmount())).forEach(this.payments::add);
    }

}
