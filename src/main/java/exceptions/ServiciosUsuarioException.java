package exceptions;

public class ServiciosUsuarioException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ServiciosUsuarioException(String msg) {
        super(msg);
    }

    public ServiciosUsuarioException(String msg, Exception e) {
        super(msg,e);
    }

}