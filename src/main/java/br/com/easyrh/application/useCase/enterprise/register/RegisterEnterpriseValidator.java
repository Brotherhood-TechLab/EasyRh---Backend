package br.com.easyrh.application.useCase.enterprise.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.easyrh.application.Utils.Validators.address.AddressValidator;
import br.com.easyrh.application.Utils.Validators.cnpj.ICnpjValidator;
import br.com.easyrh.application.Utils.Validators.email.IEmailValidator;
import br.com.easyrh.application.Utils.Validators.phoneNumber.IPhoneNumberValidator;
import br.com.easyrh.shered.request.enterprise.RequestEnterpriseRegisterJson;

@Service
public class RegisterEnterpriseValidator implements Validator{

    @Autowired
    private final IPhoneNumberValidator _phoneNumberValidator;
    private final ICnpjValidator _cnpjValidator;
    private final IEmailValidator _emailValidator;
    private final AddressValidator _addressValidator;

    public RegisterEnterpriseValidator(IPhoneNumberValidator phoneNumberValidator,
     ICnpjValidator cnpjValidator,
     IEmailValidator emailValidator,
     AddressValidator addressValidator) {
        this._phoneNumberValidator = phoneNumberValidator;
        this._cnpjValidator = cnpjValidator;
        this._emailValidator = emailValidator;
        this._addressValidator = addressValidator;
    }

    @Override
    //Esse metodo verifica se o objeto implementa a interface
    public boolean supports(Class<?> clazz) {
        return RequestEnterpriseRegisterJson.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "O nome da empresa deve ser preenchido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cnpj", "cnpj.empty", "O CNPJ da empresa deve ser preenchido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "phoneNumber.empty", "O número de telefone da empresa deve ser preenchido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "O email da empresa deve ser preenchido");

        RequestEnterpriseRegisterJson enterprise = (RequestEnterpriseRegisterJson) target; //converte o objeto em RequestEnterpriseRegisterJson

        if (!_phoneNumberValidator.IsValid(enterprise.getPhoneNumber())) 
            errors.rejectValue("phoneNumber", "phoneNumber.invalid", "O número de telefone informado é inválido");

        if (!_cnpjValidator.IsValid(enterprise.getCnpj())) 
            errors.rejectValue("cnpj", "cnpj.invalid", "O CNPJ informado é inválido");

        if(!_emailValidator.IsValid(enterprise.getEmail()))
            errors.rejectValue("email", "email.invalid", "O email informado é inválido");

        _addressValidator.validate(enterprise.getAddress(), errors);
    }

}
