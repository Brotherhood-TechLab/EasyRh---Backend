package br.com.easyrh.application.Utils.Validators.email;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements IEmailValidator {

    private final String _regexRule = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final Pattern _pattern = Pattern.compile(_regexRule);

    @Override
    public boolean IsValid(String email) {
        if(IsNullOrEmpty(email))
            return false;
        else
            return IsEmailValid(email);
    }

    private boolean IsNullOrEmpty(String email) {
        return (email == null || email.isEmpty());
    }

    private boolean IsEmailValid(String email) {
        return _pattern.matcher(email).matches();
    }

}
