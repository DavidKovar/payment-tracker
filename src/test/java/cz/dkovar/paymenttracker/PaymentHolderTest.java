package cz.dkovar.paymenttracker;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @author dkovar
 */
public class PaymentHolderTest {

    @Test
    public void testAddPayment() throws Exception {
        PaymentHolder paymentHolder = new PaymentHolder(new PatternLineParser(), new ArrayList<>());
        paymentHolder.addPayment("USD 100");
        paymentHolder.addPayment("CZK 200");
        paymentHolder.addPayment("CZK 0");
        assertEquals(2, paymentHolder.getPayments().size());
    }

    @Test
    public void testAddPayments() throws Exception {
        PaymentHolder paymentHolder = new PaymentHolder(new PatternLineParser(), new ArrayList<>());
        Collection<Payment> collection = Arrays.asList(
                new Payment("USD", BigDecimal.valueOf(100)),
                new Payment("USD", BigDecimal.valueOf(200)),
                new Payment("USD", BigDecimal.ZERO));
        paymentHolder.addPayments(collection);
        assertEquals(2, paymentHolder.getPayments().size());
    }
}