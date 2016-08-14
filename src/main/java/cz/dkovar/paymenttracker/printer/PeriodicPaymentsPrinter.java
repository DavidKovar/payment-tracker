package cz.dkovar.paymenttracker.printer;

import cz.dkovar.paymenttracker.reader.PaymentPromptReader;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.requireNonNull;

/**
 * Prints output of {@link PaymentPrinter#getOutput()} every minute.
 * @author dkovar
 */
public class PeriodicPaymentsPrinter {

    private static final int INITIAL_PERIOD = 1;
    private static final int PERIOD = 1;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final PaymentPrinter printer;

    public PeriodicPaymentsPrinter(PaymentPrinter paymentPrinter) {
        printer = requireNonNull(paymentPrinter, "The Parameter paymentsPresenter is required!");
    }

    /**
     * Starts periodic output.
     */
    public void run() {
        scheduler.scheduleAtFixedRate(
                () -> System.out.println(prettyPrint(printer.getOutput())),
                INITIAL_PERIOD,
                PERIOD,
                TimeUnit.MINUTES);
    }

    /**
     * Stops periodic output.
     */
    public void stop() {scheduler.shutdown();}

    /**
     * Creates user friendly output of payments
     * @param payments payments
     * @return user friendly output of payments
     */
    private String prettyPrint(String payments){
        StringBuilder sb = new StringBuilder()
                .append("\n")
                .append("List of all the currency and amounts: \n")
                .append(payments)
                .append("\n")
                .append(PaymentPromptReader.ENTRY_PAYMENT_MESSAGE);
        return sb.toString();
    }
}
