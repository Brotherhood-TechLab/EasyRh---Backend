package br.com.easyrh.application.useCase.login;

import br.com.easyrh.domain.service.login.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static br.com.easyrh.application.Utils.errorMessageOnValidation.ErrorMessage.GetErrorMessage;
import br.com.easyrh.exceptions.ErrorOnValidationException;
import br.com.easyrh.infrastructure.security.jwt.IGenereteToken;
import br.com.easyrh.shared.request.login.RequestLoginJson;
import br.com.easyrh.shared.response.login.ResponseLoginJson;

@Service
public class UserLoginUseCase implements IUserLoginUseCase {

  private final UserLoginValidator _validator;
  private final ILoginService _loginService;

  @Autowired
  public UserLoginUseCase(UserLoginValidator validator,
                          ILoginService loginService)
  {
      _validator = validator;
      _loginService = loginService;
  }

  @Override
  public ResponseLoginJson Execute(RequestLoginJson request)
  {
    ValidateRequest(request);
    return _loginService.DoLogin(request);
  }

  private void ValidateRequest(RequestLoginJson request) {
    var result = BuildValidator(request);

    if (result.hasErrors()) {
      var message = GetErrorMessage(result);
      throw new ErrorOnValidationException(message);
    }
  }

  private Errors BuildValidator(RequestLoginJson request) {
    Errors errors = new BeanPropertyBindingResult(request, "request");
    _validator.validate(request, errors);
    return errors;
  }

}
