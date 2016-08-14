package cz.dkovar.paymenttracker;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

/**
 * Class representing a payment.
 * @author dkovar
 */
public class Payment {

    private String currency;
    private BigDecimal amount;

    public Payment(String currency, BigDecimal amount) {
        this.currency = requireNonNull(currency, "The parameter currency is required!");
        this.amount = requireNonNull(amount, "The parameter amount is required!");
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return  currency + " " + amount;
    }

}
