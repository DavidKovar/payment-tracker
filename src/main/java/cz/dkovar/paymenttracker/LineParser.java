package cz.dkovar.paymenttracker;

/**
 * @author dkovar
 */
public interface LineParser {
    Payment parse(String line);
}
