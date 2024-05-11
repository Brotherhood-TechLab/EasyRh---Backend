package br.com.easyrh.application.useCase.user.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.easyrh.application.Utils.Validators.address.AddressValidator;
import br.com.easyrh.application.Utils.Validators.cpf.ICpfValidator;
import br.com.easyrh.application.Utils.Validators.email.IEmailValidator;
import br.com.easyrh.application.Utils.Validators.password.IPasswordValidator;
import br.com.easyrh.application.Utils.Validators.phoneNumber.IPhoneNumberValidator;
import br.com.easyrh.shered.request.address.RequestAddressRegisterJson;
import br.com.easyrh.shered.request.user.RequestUserRegisterJson;

@Service
public class RegisterUserValidator implements Validator 
{
    private final IPhoneNumberValidator _phoneNumberValidator;
    private final ICpfValidator _cpfValidator;
    private final IEmailValidator _emailValidator;
    private final AddressValidator _addressValidator;  
    private final IPasswordValidator _passwordValidator;  

    @Override
    public boolean supports(Class<?> clazz) {
        return RequestUserRegisterJson.class.equals(clazz);
    }

    @Autowired
    public RegisterUserValidator(IPhoneNumberValidator phoneNumberValidator, ICpfValidator cpfValidator,
            IEmailValidator emailValidator, AddressValidator addressValidator, IPasswordValidator passwordValidator) {
        this._phoneNumberValidator = phoneNumberValidator;
        this._cpfValidator = cpfValidator;
        this._emailValidator = emailValidator;
        this._addressValidator = addressValidator;
        _passwordValidator = passwordValidator;
    }

    @Override
    public void validate(Object target, Errors errors) 
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "O nome deve ser preenchido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "O email deve ser preenchido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "A senha deve ser preenchida");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "phone.empty", "O telefone deve ser preenchido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "cpf.empty", "O CPF deve ser preenchido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.empty", "O cargo deve ser preenchido");
        
        RequestUserRegisterJson user = (RequestUserRegisterJson) target;
        RequestAddressRegisterJson address = user.getAddress();

        if (!_passwordValidator.IsValid(user.getPassword()))
            errors.rejectValue("password", "password.invalid", 
            "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número, um caractere especial e deve ter pelo menos 8 caracteres");

        if (!_phoneNumberValidator.IsValid(user.getPhone()))
            errors.rejectValue("phone", "phone.invalid", "O telefone informado é inválido");

        if (!_cpfValidator.IsValid(user.getCpf()))
            errors.rejectValue("cpf", "cpf.invalid", "O CPF informado é inválido");

        if (!_emailValidator.IsValid(user.getEmail()))
            errors.rejectValue("email", "email.invalid", "O email informado é inválido");

        _addressValidator.validate(address, errors);
    }

}
