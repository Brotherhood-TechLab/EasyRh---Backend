package br.com.easyrh.application.Utils.Validators.cnpj;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
@Service
public class CnpjValidator implements ICnpjValidator {

    private final String _regexRule = "^\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}$";
    private final Pattern _pattern = Pattern.compile(_regexRule);

    @Override
    public boolean IsValid(String cnpj) {
        if (IsNullOrEmpty(cnpj))
            return false;
        else
        {
            return IsCnpjValid(cnpj);
        }
    }

    private boolean IsCnpjValid(String cnpj) {
        return _pattern.matcher(cnpj).matches();
    }

    private boolean IsNullOrEmpty(String cnpj) {
        return (cnpj == null || cnpj.isEmpty());
    }

}
