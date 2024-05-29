package br.com.easyrh.application.Utils.Validators.address;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.easyrh.shared.request.address.RequestAddressRegisterJson;

@Service
public class AddressValidator implements Validator {

  private final String regexRuleBrazilianZipCode = "^\\d{5}-\\d{3}$";
  private final String regexRuleUSAZipCode = "^\\d{5}(?:-\\d{4})?$";

  private final Pattern patternBrazilianZipCode = Pattern.compile(regexRuleBrazilianZipCode);
  private final Pattern patternUSAZipCode = Pattern.compile(regexRuleUSAZipCode);

  @Override
  public boolean supports(Class<?> clazz) {
    return RequestAddressRegisterJson.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    RequestAddressRegisterJson address = (RequestAddressRegisterJson) target;

    if (IsNullOrEmpty(address.getNumber())) {
      errors.rejectValue("number", "number.empty", "O número deve ser informado");
    }

    if (IsNullOrEmpty(address.getComplement())) {
      errors.rejectValue("complement", "complement.empty", "O complemento deve ser informado");
    }

    if (IsNullOrEmpty(address.getNeighborhood())) {
      errors.rejectValue("neighborhood", "neighborhood.empty", "O bairro deve ser informado");
    }

    if (IsNullOrEmpty(address.getCity())) {
      errors.rejectValue("city", "city.empty", "A cidade deve ser informada");
    }

    if (IsNullOrEmpty(address.getState())) {
      errors.rejectValue("state", "state.empty", "O estado deve ser informado");
    }

    if (IsNullOrEmpty(address.getCountry())) {
      errors.rejectValue("country", "country.empty", "O país deve ser informado");
    }

    if (IsNullOrEmpty(address.getStreet())) {
      errors.rejectValue("street", "street.empty", "O logradouro deve ser informado");
    }

    String zipCode = address.getZipCode();

    if (!isZipCodeValid(zipCode))
      errors.rejectValue("zipCode", "zipCode.invalid", "O código postal informado é inválido");
  }

  private boolean isZipCodeValid(String zipCode) {
    return isBrazilianZipCodeValid(zipCode) || isUSAZipCodeValid(zipCode);
  }

  private boolean isUSAZipCodeValid(String zipCode) {
    return patternUSAZipCode.matcher(zipCode).matches();
  }

  private boolean isBrazilianZipCodeValid(String zipCode) {
    return patternBrazilianZipCode.matcher(zipCode).matches();
  }

  private boolean IsNullOrEmpty(Object obj) {
    return obj == null || obj.toString().isBlank();
  }
}
