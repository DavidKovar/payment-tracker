package cz.dkovar.paymenttracker;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;

/**
 * @author dkovar
 */
public class PatternLineParserTest {

    private PatternLineParser patternLineParser = new PatternLineParser();

    @Test
    public void testParseSuccessfulParsing() throws Exception {
        Payment payment = patternLineParser.parse("USD 1000");
        assertEquals("USD", payment.getCurrency());
        assertEquals(BigDecimal.valueOf(1000), payment.getAmount());
    }

    @Test(expected = PaymentFormatException.class)
    public void testThrowsExceptionWhenIncorrectFormat() throws Exception {
        patternLineParser.parse("1000 USD");
        fail("Expected PaymentFormatException!");
    }

}