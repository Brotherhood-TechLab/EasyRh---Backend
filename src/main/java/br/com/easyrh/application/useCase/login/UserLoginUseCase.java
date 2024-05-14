package br.com.easyrh.application.useCase.login;

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
  private final AuthenticationManager _authenticationManager;
  private final IGenereteToken _genereteToken;

  @Autowired
  public UserLoginUseCase(UserLoginValidator validator,
      AuthenticationManager authenticationManager,
      IGenereteToken genereteToken) {
    this._validator = validator;
    _authenticationManager = authenticationManager;
    _genereteToken = genereteToken;
  }

  @Override
  public ResponseLoginJson Execute(RequestLoginJson request) {
    ValidateRequest(request);
    UserValidate(request);
    return BuildResponse(request);
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

  private void UserValidate(RequestLoginJson request) {
    _authenticationManager.authenticate(GetUsernamePasswordToken(request));
  }

  private UsernamePasswordAuthenticationToken GetUsernamePasswordToken(RequestLoginJson request) {
    return new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
  }

  private ResponseLoginJson BuildResponse(RequestLoginJson request) {
    return new ResponseLoginJson(
        GetToken(request.getEmail()),
        request.getEmail());
  }

  private String GetToken(String email) {
    return _genereteToken.GenereteToken(email);
  }

}
