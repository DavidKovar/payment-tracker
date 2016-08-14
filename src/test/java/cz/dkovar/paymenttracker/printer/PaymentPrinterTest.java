package cz.dkovar.paymenttracker.printer;

import cz.dkovar.paymenttracker.PatternLineParser;
import cz.dkovar.paymenttracker.Payment;
import cz.dkovar.paymenttracker.PaymentHolder;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * @author dkovar
 */
public class PaymentPrinterTest {

    @Test
    public void testGetOutput() throws Exception {
        Collection<Payment> payments = new ArrayList<>();

        payments.add(new Payment("USD", BigDecimal.ZERO));
        payments.add(new Payment("USD", BigDecimal.valueOf(1000)));
        payments.add(new Payment("USD", BigDecimal.valueOf(1500)));
        payments.add(new Payment("CZK", BigDecimal.valueOf(0.2)));

        PaymentHolder paymentHolder = new PaymentHolder(new PatternLineParser(), payments);
        assertEquals("CZK 0.2\nUSD 2500\n", new PaymentPrinter(paymentHolder).getOutput());
    }
}