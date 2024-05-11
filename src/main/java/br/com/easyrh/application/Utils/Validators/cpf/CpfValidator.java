package br.com.easyrh.application.Utils.Validators.cpf;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class CpfValidator implements ICpfValidator
{
    private String _regexRule = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$";
    private Pattern _pattern = Pattern.compile(_regexRule);

    @Override
    public boolean IsValid(String cpf) {
        return _pattern.matcher(cpf).matches();
    }

}
