package br.com.easyrh.application.useCase.user.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static br.com.easyrh.application.Utils.erroMessageOnValidation.ErrorMessage.GetErrorMessage;
import br.com.easyrh.domain.Entities.User;
import br.com.easyrh.domain.Enum.Role;
import br.com.easyrh.domain.repositories.user.readOnly.IUserReadOnlyRepository;
import br.com.easyrh.domain.repositories.user.writeOnly.IUserWriteOnlyrepository;
import br.com.easyrh.exceptions.ErrorOnValidationException;
import br.com.easyrh.infrastructure.security.passwordEncrypter.IPasswordEncrypter;
import br.com.easyrh.shered.request.user.RequestUserRegisterJson;
import br.com.easyrh.shered.response.user.ResponseUserRegisterJson;

@Service
public class RegisterUserUseCase implements IRegisterUserUseCase 
{
    private final RegisterUserValidator _validator;
    private final IUserReadOnlyRepository _readRepository;
    private final IUserWriteOnlyrepository _writeRepository;
    private final IPasswordEncrypter _passwordEncrypter;

    @Autowired
    public RegisterUserUseCase(RegisterUserValidator validator
    , IUserReadOnlyRepository repository
    , IUserWriteOnlyrepository writeRepository
    , IPasswordEncrypter passwordEncrypter) 
    {
        this._validator = validator;
        this._readRepository = repository;
        this._writeRepository = writeRepository;
        this._passwordEncrypter = passwordEncrypter;
    }

    @Override
    public ResponseUserRegisterJson Execute(RequestUserRegisterJson request) 
    {
        ValidateRequest(request);

        SaveUser(request);

        return BuildResponse(request.getName(), request.getPassword(), request.getEmail(), GetRole(request));
    }

    private void ValidateRequest(RequestUserRegisterJson request) 
    {
        var result = BuildValidator(request);

        if (result.hasErrors()) 
        {
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

    private void SaveUser(RequestUserRegisterJson request) 
    {
        UserAlreadyExists(request.getEmail(), request.getCpf());

        var userEntity = BuildUserEntity(request);

        _writeRepository.SaveUser(userEntity);
    }

    private void UserAlreadyExists(String email, String cpf)
    {
        if(EmailAlreadyExists(email) || CpfAlreadyExists(cpf))
            throw new ErrorOnValidationException("Usuário já cadastrado no sistema.");
    }

    private boolean EmailAlreadyExists(String email)
    {
        return _readRepository.ExistsUserByEmail(email);
    }

    private boolean CpfAlreadyExists(String cpf)
    {
        return _readRepository.ExistsUserByCpf(cpf);
    }

    private User BuildUserEntity(RequestUserRegisterJson request)
    {
        var userEntity = new User(request);

        userEntity.setRole(GetRole(request));
        
        userEntity.setPassword(GetEncryptedPassword(request.getPassword()));

        return userEntity;
    }

    private ResponseUserRegisterJson BuildResponse(String name, String password, String email, Role role)
    {
        return new ResponseUserRegisterJson
        (
            name,
            GetEncryptedPassword(password),
            email,
            role
        );
    }

    private String GetEncryptedPassword(String password)
    {
        return _passwordEncrypter.Encrypt(password);
    }

    private Role GetRole(RequestUserRegisterJson request)
    {
        return request.getRole() == true ? Role.ADMIN : Role.USER;
    }

}
