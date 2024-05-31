package br.com.easyrh.application.useCase.department.register;

import static br.com.easyrh.application.Utils.errorMessageOnValidation.ErrorMessage.GetErrorMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import br.com.easyrh.domain.service.department.register.IRegisterDepartmentService;
import br.com.easyrh.exceptions.ErrorOnValidationException;
import br.com.easyrh.shared.request.department.RequestDepartmentRegisterJson;

@Service
public class RegisterDepartmentUseCase implements IRegisterDepartmentUseCase {

  @Autowired
  private RegisterDepartmentValidator _registerDepartmentValidator;

  @Autowired
  private IRegisterDepartmentService _registerDepartmentService;

  public RegisterDepartmentUseCase(RegisterDepartmentValidator registerDepartmentValidator,
      IRegisterDepartmentService registerDepartmentService) {
    _registerDepartmentValidator = registerDepartmentValidator;
    _registerDepartmentService = registerDepartmentService;
  }

  @Override
  public void Execute(RequestDepartmentRegisterJson request) {
    ValidateRequest(request);

    _registerDepartmentService.RegisterDepartment(request);
  }

  private void ValidateRequest(RequestDepartmentRegisterJson department) {
    var result = BuildValidate(department);

    if (result.hasErrors()) {
      var message = GetErrorMessage(result);
      throw new ErrorOnValidationException(message);
    }
  }

  private Errors BuildValidate(RequestDepartmentRegisterJson department) {
    Errors errors = new BeanPropertyBindingResult(department, "department");

    _registerDepartmentValidator.validate(department, errors);

    return errors;
  }

}
