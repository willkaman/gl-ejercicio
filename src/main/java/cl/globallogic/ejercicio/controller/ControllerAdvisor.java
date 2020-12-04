package cl.globallogic.ejercicio.controller;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cl.globallogic.ejercicio.exception.ConflictException;
import cl.globallogic.ejercicio.exception.NoDataFoundException;
import cl.globallogic.ejercicio.exception.UnauthException;
import cl.globallogic.ejercicio.factory.ResponseFactory;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> handleNodataFoundException(NoDataFoundException ex, WebRequest request) {

        return ResponseFactory.buildNotfoundErrorResponse();
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflictException(ConflictException ex, WebRequest request) {

        return ResponseFactory.buildConflictErrorResponse();
    }

    @ExceptionHandler(UnauthException.class)
    public ResponseEntity<?> handleUnauthException(UnauthException ex, WebRequest request) {
        return ResponseFactory.buildUnauthErrorResponse();
    }

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<?>
    // handleMethodArgumentNotValidExceptionOver(MethodArgumentNotValidException ex,
    // WebRequest request){
    // return new ResponseEntity<>(HttpStatus.GONE);
    // }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse(ex.getMessage());
        return ResponseFactory.buildBadRequestResponse();
    }

    // private ResponseEntity<Object> response(Exception ex, WebRequest request, HttpStatus status, String message) {
    //     return handleExceptionInternal(ex, message, header(), status, request);
    // }

}