package cz.dkovar.paymenttracker.reader;

import cz.dkovar.paymenttracker.PatternLineParser;
import cz.dkovar.paymenttracker.Payment;
import cz.dkovar.paymenttracker.PaymentHolder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @author dkovar
 */
public class PaymentFileReaderTest {

    private PaymentHolder paymentsHolder = new PaymentHolder(new PatternLineParser(), Collections.synchronizedList(new ArrayList<>()));

    @Test
    public void testReadPaymentsFileNotExist() throws Exception {
        PaymentReader reader = new PaymentFileReader(paymentsHolder, "src\\test\\resources\\payments_not_exist.txt");
        reader.run();
        assertTrue(paymentsHolder.getPayments().isEmpty());
    }

    @Test
    public void testReadPaymentsIncorrectPaymentFormat() throws Exception {
        PaymentReader reader = new PaymentFileReader(paymentsHolder, "src\\test\\resources\\payments_incorrect.txt");
        reader.run();
        assertTrue(paymentsHolder.getPayments().isEmpty());
    }

    @Test
    public void testReadPayments() throws Exception {
        PaymentReader reader = new PaymentFileReader(paymentsHolder, "src\\test\\resources\\payments.txt");
        reader.run();
        Stream<Payment> usdPayments = paymentsHolder.getPayments().stream().filter(p -> p.getCurrency().equals("USD"));
        assertEquals(2, usdPayments.count());
    }

}