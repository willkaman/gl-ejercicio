package cl.globallogic.ejercicio.exception;

public class UnauthException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public UnauthException() {
        super("No data found");
    }
}
