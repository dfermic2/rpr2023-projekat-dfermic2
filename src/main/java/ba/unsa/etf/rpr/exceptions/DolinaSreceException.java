package ba.unsa.etf.rpr.exceptions;

public class DolinaSreceException extends Exception {
    public DolinaSreceException(String msg, Exception reason) {
        super(msg, reason);
    }

    public DolinaSreceException(String msg) {
        super(msg);
    }
}
