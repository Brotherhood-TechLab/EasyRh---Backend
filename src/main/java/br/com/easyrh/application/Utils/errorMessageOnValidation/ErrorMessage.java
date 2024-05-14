package br.com.easyrh.application.Utils.errorMessageOnValidation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.Errors;

public class ErrorMessage {
  public static List<String> GetErrorMessage(Errors error) {
    var message = error.getAllErrors().stream()
        .map(ex -> ex.getDefaultMessage())
        .collect(Collectors.toList());

    return message;
  }
}
