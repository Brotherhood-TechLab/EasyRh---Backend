package br.com.easyrh.application.useCase.department.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.easyrh.shared.request.enterprise.RequestEnterpriseRegisterJson;

@Service
public class RegisterDepartmentValidator implements Validator {

  @Autowired
  public RegisterDepartmentValidator() {
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return RequestEnterpriseRegisterJson.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty",
        "O nome do departament deve ser preenchido");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.empty",
        "A descrição deve ser preenchida");

  }
}
