package br.com.easyrh.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.easyrh.exceptions.ErrorOnValidationException;
import br.com.easyrh.exceptions.ExceptionResponse;

@ControllerAdvice //Serve para interceptar erros em todos os controllers
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) 
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            new Date(),
            ex.getMessage(),
            request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorOnValidationException.class)
    public final ResponseEntity<ExceptionResponse> handleErrorOnValidationExceptions(ErrorOnValidationException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            new Date(),
            ex.getMessage(),
            request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
