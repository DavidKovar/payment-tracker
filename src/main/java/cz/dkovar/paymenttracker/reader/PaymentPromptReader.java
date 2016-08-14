package cz.dkovar.paymenttracker.reader;

import cz.dkovar.paymenttracker.PaymentFormatException;
import cz.dkovar.paymenttracker.PaymentHolder;

import java.util.Scanner;

/**
 * Reads payments from given file into {@link PaymentHolder}.
 * @author dkovar
 */
public class PaymentPromptReader extends PaymentReader {

    private static final String QUIT = "quit";

    private static final String HELP_MESSAGE = "Enter 'quit' to quit";
    public static final String ENTRY_PAYMENT_MESSAGE = "Enter currency and amount. Example: USD 100";

    public PaymentPromptReader(PaymentHolder payments) {
        super(payments);
    }

    /**
     * Starts reading from the command line.
     */
    @Override
    public void run() {
        System.out.println(HELP_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        try {
            whileNotQuiting(scanner);
        } finally {
            scanner.close();
        }
        System.out.println("Exiting ...");
    }

    /**
     * Reads from command line until user enters 'quit'
     * @param scanner {@link Scanner}
     */
    private void whileNotQuiting(Scanner scanner){
        String line = readLine(scanner, ENTRY_PAYMENT_MESSAGE);
        while (!QUIT.equalsIgnoreCase(line)) {
            try {
                getPaymentHolder().addPayment(line);
            } catch (PaymentFormatException e) {
                System.out.print(e.getLocalizedMessage());
            } finally {
                line = readLine(scanner, ENTRY_PAYMENT_MESSAGE);
            }
        }
    }

    /**
     * Reads input from the command line
     * @param scanner {@link Scanner}
     * @param helpMessage a message displayed to the user
     * @return current line
     */
    private String readLine(Scanner scanner, String helpMessage){
        System.out.println("\n".concat(helpMessage));
        return scanner.nextLine();
    }

}
