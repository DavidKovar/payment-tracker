package cz.dkovar.paymenttracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses line with payment using regular expression.
 * Exapmle line: USD 100
 * @author dkovar
 */
public class PatternLineParser implements LineParser {

    private static final Pattern INPUT_PATTERN = Pattern.compile("[A-Z]{3} \\d*.?\\d*");

    /**
     * Parses line with payment using regular expression.
     * @param paymentLine line with payment input
     * @return line with payment using regular expression
     */
    @Override
    public Payment parse(String paymentLine) {
        Matcher matcher = INPUT_PATTERN.matcher(paymentLine);
        if (!matcher.matches()) {
            throw new PaymentFormatException("The line ".concat(paymentLine).concat(" is invalid payment format."));
        }

        String[] payment = paymentLine.split(" ");
        String currency = payment[0];
        BigDecimal amount = new BigDecimal(payment[1]);
        amount.setScale(2, RoundingMode.HALF_UP);

        return new Payment(currency, amount);
    }

}
