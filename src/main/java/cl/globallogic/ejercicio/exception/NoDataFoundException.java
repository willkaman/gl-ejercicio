package cl.globallogic.ejercicio.exception;

public class NoDataFoundException extends RuntimeException {
    private static final long serialVersionUID = 7318682355154460851L;

    public NoDataFoundException() {

        super("No data found");
    }
}