package br.com.easyrh.application.Utils.Validators.address;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.easyrh.shered.request.address.RequestAddressRegisterJson;

@Service
public class AddressValidator implements Validator{

    private final String _regexRuleBrazilianZipCode = "^\\d{5}-\\d{3}$";
    private final String _regexRuleUSAZipCode = "\\\\d{5}(?:-\\\\d{4})?";
    
    private final Pattern _patternBrazilianZipCode = Pattern.compile(_regexRuleBrazilianZipCode);
    private final Pattern _patternUSAZipCode = Pattern.compile(_regexRuleUSAZipCode);

    @Override
    public boolean supports(Class<?> clazz) {
        return RequestAddressRegisterJson.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "street.empty", "O logradouro deve ser informado");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "number.empty", "O número deve ser informado");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "complement", "complement.empty", "O complemento deve ser informado");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "neighborhood", "neighborhood.empty", "O bairro deve ser informado");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "city.empty", "A cidade deve ser informada");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "state.empty", "O estado deve ser informado");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", "zipCode.empty", "O CEP deve ser informado");  
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "country.empty", "O país deve ser informado");

        RequestAddressRegisterJson address = (RequestAddressRegisterJson) target;

        String zipCode = address.getZipCode();
        if (!IsZipCodeValid(zipCode)) 
            errors.rejectValue("zipCode", "zipCode.invalid", "O Código postal informado é inválido");
    }

    private boolean IsZipCodeValid(String zipCode) 
    {
        if (IsBrazilianZipCodeValid(zipCode) || IsUSAZipCodeValid(zipCode)) 
            return true;

        return false;
    }

    private boolean IsUSAZipCodeValid(String zipCode) 
    {
        return _patternUSAZipCode.matcher(zipCode).matches();
    }

    private boolean IsBrazilianZipCodeValid(String zipCode) 
    {
        return _patternBrazilianZipCode.matcher(zipCode).matches();
    }

}
