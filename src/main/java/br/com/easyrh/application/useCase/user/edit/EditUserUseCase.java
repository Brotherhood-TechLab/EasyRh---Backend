package br.com.easyrh.application.useCase.user.edit;

import static br.com.easyrh.application.Utils.errorMessageOnValidation.ErrorMessage.GetErrorMessage;

import br.com.easyrh.domain.service.user.edit.IEditUserService;
import br.com.easyrh.shared.response.user.ResponseUserRegisterJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import br.com.easyrh.domain.Entities.User;
import br.com.easyrh.domain.Enum.Role;
import br.com.easyrh.domain.repositories.user.readOnly.IUserReadOnlyRepository;
import br.com.easyrh.domain.repositories.user.writeOnly.IUserWriteOnlyRepository;
import br.com.easyrh.exceptions.ErrorOnValidationException;
import br.com.easyrh.infrastructure.security.passwordEncrypter.IPasswordEncrypter;
import br.com.easyrh.shared.request.user.RequestUserEditJson;

@Service
public class EditUserUseCase implements IEditUserUseCase {
  private final EditUserValidator _validator;

  private final IEditUserService _editUserService;

  @Autowired
  public EditUserUseCase(EditUserValidator validator,
                         IEditUserService editUserService)
  {
    this._validator = validator;
    this._editUserService = editUserService;
  }

  @Override
  public ResponseUserRegisterJson Execute(RequestUserEditJson request)
  {
    ValidateRequest(request);

    return _editUserService.EditUserService(request);
  }

  private void ValidateRequest(RequestUserEditJson request)
  {
    var result = BuildValidator(request);

    if (result.hasErrors()) {
      var message = GetErrorMessage(result);
      throw new ErrorOnValidationException(message);
    }
  }
  private Errors BuildValidator(RequestUserEditJson request)
  {
    Errors errors = new BeanPropertyBindingResult(request, "request");

    _validator.validate(request, errors);

    return errors;
  }

  private void SaveUser(RequestUserEditJson request) {

    var newUserEntity = BuildUserEntity(request);

    var oldUserEntity = _readRepository.FindByCPF(newUserEntity.getCpf());

    newUserEntity.setId(oldUserEntity.getId());
    newUserEntity.getAddress().setId(oldUserEntity.getAddress().getId());

    _writeRepository.SaveUser(newUserEntity);
  }

  private User BuildUserEntity(RequestUserEditJson request) {
    var userEntity = new User(request);

    userEntity.setRole(GetRole(request));

    userEntity.setPassword(GetEncryptedPassword(request.Password()));

    return userEntity;
  }

  private String GetEncryptedPassword(String password) {
    return _passwordEncrypter.Encrypt(password);
  }

  private Role GetRole(RequestUserEditJson request) {
    return request.Role() == true ? Role.ADMIN : Role.USER;
  }
