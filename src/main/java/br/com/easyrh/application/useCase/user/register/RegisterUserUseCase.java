package br.com.easyrh.application.useCase.user.register;

import br.com.easyrh.domain.service.user.registerService.IRegisterUserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static br.com.easyrh.application.Utils.errorMessageOnValidation.ErrorMessage.GetErrorMessage;
import br.com.easyrh.domain.Entities.User;
import br.com.easyrh.domain.Enum.Role;
import br.com.easyrh.domain.repositories.user.readOnly.IUserReadOnlyRepository;
import br.com.easyrh.domain.repositories.user.writeOnly.IUserWriteOnlyRepository;
import br.com.easyrh.exceptions.ErrorOnValidationException;
import br.com.easyrh.infrastructure.security.jwt.IGenereteToken;
import br.com.easyrh.infrastructure.security.passwordEncrypter.IPasswordEncrypter;
import br.com.easyrh.shared.request.user.RequestUserRegisterJson;
import br.com.easyrh.shared.response.user.ResponseUserRegisterJson;

@Service
public class RegisterUserUseCase implements IRegisterUserUseCase
{
  private final RegisterUserValidator _validator;
  private final IRegisterUserSerivce _registerUserService;

  @Autowired
  public RegisterUserUseCase(RegisterUserValidator validator,
                             IRegisterUserSerivce registerUserService)
  {
      _validator = validator;
      _registerUserService = registerUserService;
  }

    @Override
  public ResponseUserRegisterJson Execute(RequestUserRegisterJson request)
  {
    ValidateRequest(request);

    return _registerUserService.RegisterUser(request);
  }

  private void ValidateRequest(RequestUserRegisterJson request)
  {
    var result = BuildValidator(request);

    if (result.hasErrors()) {
      var message = GetErrorMessage(result);
      throw new ErrorOnValidationException(message);
    }
  }

  private Errors BuildValidator(RequestUserRegisterJson request)
  {
    Errors errors = new BeanPropertyBindingResult(request, "request");

    _validator.validate(request, errors);

    return errors;
  }
}
