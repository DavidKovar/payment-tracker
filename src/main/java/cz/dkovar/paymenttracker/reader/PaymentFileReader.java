package cz.dkovar.paymenttracker.reader;

import cz.dkovar.paymenttracker.Payment;
import cz.dkovar.paymenttracker.PaymentFormatException;
import cz.dkovar.paymenttracker.PaymentHolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.*;

/**
 * Reads payements from given file into {@link PaymentHolder}.
 * @author dkovar
 */
public class PaymentFileReader extends PaymentReader {

    private final String fileName;

    public PaymentFileReader(PaymentHolder payments, String fileName) {
        super(payments);
        this.fileName = requireNonNull(fileName, "The Parameter fileName is required!");
    }

    /**
     * Starts reading from the file.
     */
    @Override
    public void run() {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            Collection<Payment> payments = stream.map(e -> getPaymentHolder().getLineParser().parse(e)).collect(toList());
            getPaymentHolder().addPayments(payments);
        } catch (IOException e) {
            System.out.println("\nThe file with name " + fileName + " does not exists!");
            System.out.println(e.getMessage());
        } catch (PaymentFormatException e) {
            System.out.println("\nThe file " + fileName + " could not be processed!");
            System.out.println(e.getMessage());
        }
    }

}
