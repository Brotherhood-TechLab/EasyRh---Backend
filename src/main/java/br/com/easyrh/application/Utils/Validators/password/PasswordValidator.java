package br.com.easyrh.application.Utils.Validators.password;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidator implements IPasswordValidator
{
    private final String _regexRule = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private final Pattern _pattern = Pattern.compile(_regexRule);

    @Override
    public boolean IsValid(String password) {
        return _pattern.matcher(password).matches();
    }
}
