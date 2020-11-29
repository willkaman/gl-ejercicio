package cl.globallogic.ejercicio.exception;

public class ConflictException extends RuntimeException {
    private static final long serialVersionUID = 7318682355154460851L;

    public ConflictException() {
        super("resource already exists");
    }
}