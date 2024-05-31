package br.com.easyrh.application.useCase.user.edit;

import static br.com.easyrh.application.Utils.errorMessageOnValidation.ErrorMessage.GetErrorMessage;

import br.com.easyrh.domain.service.user.edit.IEditUserService;
import br.com.easyrh.shared.response.user.ResponseUserRegisterJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import br.com.easyrh.exceptions.ErrorOnValidationException;
import br.com.easyrh.shared.request.user.RequestUserEditJson;

@Service
public class EditUserUseCase implements IEditUserUseCase {
  private final EditUserValidator _validator;

  private final IEditUserService _editUserService;

  @Autowired
  public EditUserUseCase(EditUserValidator validator,
      IEditUserService editUserService) {
    this._validator = validator;
    this._editUserService = editUserService;
  }

  @Override
  public ResponseUserRegisterJson Execute(RequestUserEditJson request) {
    ValidateRequest(request);

    return _editUserService.EditUser(request);
  }

  private void ValidateRequest(RequestUserEditJson request) {
    var result = BuildValidator(request);

    if (result.hasErrors()) {
      var message = GetErrorMessage(result);
      throw new ErrorOnValidationException(message);
    }
  }

  private Errors BuildValidator(RequestUserEditJson request) {
    Errors errors = new BeanPropertyBindingResult(request, "request");

    _validator.validate(request, errors);

    return errors;
  }
}
