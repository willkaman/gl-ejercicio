package cl.globallogic.ejercicio.controller;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> handleNodataFoundException(
        NoDataFoundException ex, WebRequest request) {

        return ResponseFactory.buildNotfoundErrorResponse();
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflictException(
        ConflictException ex, WebRequest request) {

        return ResponseFactory.buildConflictErrorResponse();
    }

    @ExceptionHandler(UnauthException.class)
    public ResponseEntity<?> handleUnauthException(UnauthException ex, WebRequest request){
        return ResponseFactory.buildUnauthErrorResponse();
    }

}