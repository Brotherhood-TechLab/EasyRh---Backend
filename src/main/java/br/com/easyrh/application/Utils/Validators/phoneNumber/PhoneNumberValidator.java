package br.com.easyrh.application.Utils.Validators.phoneNumber;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class PhoneNumberValidator implements IPhoneNumberValidator{

    private final String _regexRule = "^(?:(?:\\+|00)?(55)\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)?(?:((?:9\\d|[2-9])\\d{3})[-\\s]?(\\d{4}))$";
    private final Pattern _pattern = Pattern.compile(_regexRule);

    public boolean IsValid(String phoneNumber) 
    {
        if(IsNullOrEmpty(phoneNumber)) 
            return false;
        else
            return IsMatchPattern(phoneNumber); 
    }

    private boolean IsNullOrEmpty(String phoneNumber) 
    {
        return (phoneNumber == null || phoneNumber.isBlank());
    }

    public boolean IsMatchPattern(String phoneNumber) 
    {
        return _pattern.matcher(phoneNumber).matches();
    }

}
