package cz.dkovar.paymenttracker;

import cz.dkovar.paymenttracker.printer.PeriodicPaymentsPrinter;
import cz.dkovar.paymenttracker.printer.PaymentPrinter;
import cz.dkovar.paymenttracker.reader.PaymentFileReader;
import cz.dkovar.paymenttracker.reader.PaymentPromptReader;
import cz.dkovar.paymenttracker.reader.PaymentReader;

import java.util.*;

/**
 * @author dkovar
 */
public class App {

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.start(args);
    }

    public void start(String[] args) {
        Collection<Payment> payments = Collections.synchronizedList(new ArrayList<>());
        PaymentHolder paymentsHolder = new PaymentHolder(new PatternLineParser(), payments);

        loadFromFile(args, paymentsHolder);
        printToCommandLine(paymentsHolder);
        readFromCommandLine(paymentsHolder);
    }

    private void loadFromFile(String[] args, PaymentHolder paymentsHolder){
        List<String> path = Arrays.asList(args);
        if(!path.isEmpty()){
            // TODO: Je mozne dat do smycky a zpracovat libovolny pocet souboru
            PaymentReader paymentsFileReader = new PaymentFileReader(paymentsHolder, path.get(0));
            new Thread(paymentsFileReader).start();
        }
    }

    private void printToCommandLine(PaymentHolder paymentsHolder){
        PaymentPrinter paymentsPresenter = new PaymentPrinter(paymentsHolder);
        PeriodicPaymentsPrinter intervalPrinter = new PeriodicPaymentsPrinter(paymentsPresenter);
        intervalPrinter.run();
    }

    private void readFromCommandLine(PaymentHolder paymentsHolder){
        PaymentReader paymentsPromtReader = new PaymentPromptReader(paymentsHolder);
        paymentsPromtReader.run();
    }

}
