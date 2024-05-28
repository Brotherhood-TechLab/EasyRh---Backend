package br.com.easyrh.domain.service.user.edit;

import br.com.easyrh.domain.Entities.Address;
import br.com.easyrh.domain.Entities.User;
import br.com.easyrh.domain.Enum.Role;
import br.com.easyrh.domain.repositories.user.readOnly.IUserReadOnlyRepository;
import br.com.easyrh.domain.repositories.user.writeOnly.IUserWriteOnlyRepository;
import br.com.easyrh.exceptions.ErrorOnValidationException;
import br.com.easyrh.infrastructure.security.passwordEncrypter.IPasswordEncrypter;
import br.com.easyrh.shared.request.address.RequestAddressRegisterJson;
import br.com.easyrh.shared.request.user.RequestUserEditJson;
import br.com.easyrh.shared.response.user.ResponseUserRegisterJson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditUserService implements IEditUserService
{
    private final IUserReadOnlyRepository _readOnlyRepository;
    private final IUserWriteOnlyRepository _writeOnlyRepository;
    private final IPasswordEncrypter _passwordEncrypter;
    private final ModelMapper _mapper;

    @Autowired
    public EditUserService(IUserReadOnlyRepository readOnlyRepository,
                           IUserWriteOnlyRepository writeOnlyRepository,
                           IPasswordEncrypter passwordEncrypter,
                           ModelMapper mapper)
    {
        _readOnlyRepository = readOnlyRepository;
        _writeOnlyRepository = writeOnlyRepository;
        _passwordEncrypter = passwordEncrypter;
        _mapper = mapper;
    }

    @Override
    public ResponseUserRegisterJson EditUserService(RequestUserEditJson request)
    {
        return SaveInfo(request);
    }

    private ResponseUserRegisterJson SaveInfo(RequestUserEditJson request)
    {
        User user = _readOnlyRepository.FindByCPF(request.Cpf());

        user = EmailEdit(request.Email(), user);
        user.setGender(request.Gender());
        user.setDateofbirth(request.Dateofbirth());
        user.setRole(GetRole(request));
        user.setAddress(BuildAddres(request.Address()));

        Save(user);

        return BuildResponse(user);
    }

    private boolean EmailAlreadyRegister(String email)
    {
        return _readOnlyRepository.EmailAlreadyRegistred(email);
    }

    private User EmailEdit(String email, User user)
    {
        if(!user.getEmail().equals(email) && EmailAlreadyRegister(email))
        {
            throw new ErrorOnValidationException("Cannot use this email address");
        }
        else
        {
            user.setEmail(email);
        }
        return user;
    }

    private Address BuildAddres(RequestAddressRegisterJson request)
    {
        return _mapper.map(request, Address.class);
    }

    private ResponseUserRegisterJson BuildResponse(User user)
    {
        return _mapper.map(user, ResponseUserRegisterJson.class);
    }

    private String GetEncryptedPassword(String password) {
        return _passwordEncrypter.Encrypt(password);
    }

    private Role GetRole(RequestUserEditJson request) {
        return request.Role() == true ? Role.ADMIN : Role.USER;
    }

    private void Save(User user)
    {
        _writeOnlyRepository.SaveUser(user);
    }
}
