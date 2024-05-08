package br.com.easyrh.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErrorOnValidationException extends RuntimeException{

    public ErrorOnValidationException(String message) {
        super(message);
    }

    public ErrorOnValidationException(List<String> messages) 
    {
        super(String.join("\n", messages));
    }
    
    private static final long serialVersionUID = 1L;

}
