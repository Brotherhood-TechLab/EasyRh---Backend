package br.com.easyrh.application.useCase.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.easyrh.application.Utils.Validators.email.IEmailValidator;
import br.com.easyrh.application.Utils.Validators.password.IPasswordValidator;
import br.com.easyrh.shered.request.login.RequestLoginJson;

@Service
public class UserLoginValidator implements Validator
{
    private final IEmailValidator _emailValidator;
    private final IPasswordValidator _passwordValidator;

    @Autowired
    public UserLoginValidator(IEmailValidator emailValidator, IPasswordValidator passwordValidator) {
        this._emailValidator = emailValidator;
        this._passwordValidator = passwordValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void validate(Object target, Errors errors) 
    {   
        RequestLoginJson user = (RequestLoginJson) target;
        if (!_emailValidator.IsValid(user.getEmail()) || !_passwordValidator.IsValid(user.getPassword()))
            errors.rejectValue("email", "credentials.invalid", "Credenciais inválidas.");
    }
}
