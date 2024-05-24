package br.com.easyrh.domain.service.user.registerService;

import br.com.easyrh.domain.Entities.User;
import br.com.easyrh.domain.Enum.Role;
import br.com.easyrh.domain.repositories.user.readOnly.IUserReadOnlyRepository;
import br.com.easyrh.domain.repositories.user.writeOnly.IUserWriteOnlyRepository;
import br.com.easyrh.exceptions.ErrorOnValidationException;
import br.com.easyrh.infrastructure.security.jwt.IGenereteToken;
import br.com.easyrh.infrastructure.security.passwordEncrypter.IPasswordEncrypter;
import br.com.easyrh.shared.request.user.RequestUserRegisterJson;
import br.com.easyrh.shared.response.user.ResponseUserRegisterJson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService implements IRegisterUserSerivce
{
    private final IPasswordEncrypter _passwordEncrypter;
    private final IGenereteToken _genereteToken;
    private final IUserReadOnlyRepository _readOnlyRepository;
    private final IUserWriteOnlyRepository _writeOnlyRepository;
    private final ModelMapper _mapper;

    @Autowired
    public RegisterUserService(IPasswordEncrypter passwordEncrypter,
                               IGenereteToken genereteToken,
                               IUserReadOnlyRepository readOnlyRepository,
                               IUserWriteOnlyRepository writeOnlyRepository,
                               ModelMapper mapper) {
        _passwordEncrypter = passwordEncrypter;
        _genereteToken = genereteToken;
        _readOnlyRepository = readOnlyRepository;
        _writeOnlyRepository = writeOnlyRepository;
        _mapper = mapper;
    }

    @Override
    public ResponseUserRegisterJson RegisterUser(RequestUserRegisterJson request)
    {
        ApplyValidationRules(request.getEmail(), request.getPassword());

        return  SaveUser(request);
    }

    private void ApplyValidationRules(String email, String cpf)
    {
        if(EmailOrCpfAlreadyExistis(email, cpf))
        {
            throw new ErrorOnValidationException("User already exists.");
        }
    }

    private boolean EmailOrCpfAlreadyExistis(String emailRegister, String cpf)
    {
        return _readOnlyRepository.ExisteUserByEmailOrCpf(emailRegister, cpf);
    }

    private ResponseUserRegisterJson SaveUser(RequestUserRegisterJson request)
    {
        var user = BuildUserEntity(request);

        PersistUser(user);

        return BuildResponse(user);
    }

    private User BuildUserEntity(RequestUserRegisterJson request)
    {
        var user = _mapper.map(request, User.class);

        user.setRole(GetRole(request.getRole()));

        user.setPassword(_passwordEncrypter.Encrypt(request.getPassword()));

        return user;
    }

    private Role GetRole(boolean role) {
        return role ? Role.ADMIN : Role.USER;
    }

    private void PersistUser(User user)
    {
        _writeOnlyRepository.SaveUser(user);
    }

    private ResponseUserRegisterJson BuildResponse(User user)
    {
        var response = _mapper.map(user, ResponseUserRegisterJson.class);

        response.setAccessToken(_genereteToken.GenereteToken(user.getGuid_Identifier()));

        return response;
    }
}
