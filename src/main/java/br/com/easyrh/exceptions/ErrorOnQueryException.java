package br.com.easyrh.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErrorOnQueryException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ErrorOnQueryException(String message) {
    super(message);
  }

  public ErrorOnQueryException(List<String> messages) {
    super(String.join("\n", messages));
  }
}
