package cz.dkovar.paymenttracker.printer;

import cz.dkovar.paymenttracker.Payment;
import cz.dkovar.paymenttracker.PaymentHolder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.*;

/**
 * Prints formatted payment output.
 * @author dkovar
 */
public class PaymentPrinter {

    private final PaymentHolder holder;

    public PaymentPrinter(PaymentHolder paymentHolder) {
        this.holder = requireNonNull(paymentHolder, "The parameter paymentHolder is required!");
    }

    /**
     * Returns string representation of payments.
     * @return string representation of payments
     */
    public String getOutput() {
        Map<String, List<BigDecimal>> groupedByCurrency = groupByCurrency();

        StringBuilder sb = new StringBuilder();
        groupedByCurrency.keySet().stream()
                .sorted()
                .forEach(currency -> formatCurrencyGroup(sb, currency, groupedByCurrency));

        return sb.toString();
    }

    /**
     * Groups payments by currency.
     * @return payments by currency
     */
    private Map<String, List<BigDecimal>> groupByCurrency() {
        return holder.getPayments().stream()
                .filter(payment -> !payment.getAmount().equals(BigDecimal.ZERO))
                .collect(groupingBy(Payment::getCurrency, mapping(Payment::getAmount, toList())));
    }

    /**
     * Sums given amounts.
     * @param amounts list of amounts
     * @return sums of amounts
     */
    private static BigDecimal sum(List<BigDecimal> amounts) {
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal amount : amounts) {
            total = total.add(amount);
        }
        return total;
    }

    /**
     * Formats currency group.
     */
    private static void formatCurrencyGroup(StringBuilder sb, String currency, Map<String, List<BigDecimal>> groupedByCurrency) {
        BigDecimal total = sum(groupedByCurrency.get(currency));
        if (!total.equals(BigDecimal.ZERO)) {
            formatRow(sb, currency, total);
        }
    }

    /**
     * Formats row.
     */
    private static void formatRow(StringBuilder sb, String currency, BigDecimal total) {
        sb.append(currency)
        .append(" ")
        .append(total)
        .append("\n");
    }

}
