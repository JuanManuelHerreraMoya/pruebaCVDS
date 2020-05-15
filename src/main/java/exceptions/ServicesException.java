package exceptions;

public class ServicesException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ServicesException() {
    }

    public ServicesException(String message) {
        super(message);
    }

    public ServicesException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServicesException(Throwable cause) {
        super(cause);
    }

}
