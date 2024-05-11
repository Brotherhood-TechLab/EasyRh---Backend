package br.com.easyrh.infrastructure.security.passwordEncrypter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncrypter implements IPasswordEncrypter
{
    @Autowired
    private PasswordEncoder _passwordEncoder;

    public PasswordEncrypter(PasswordEncoder passwordEncoder)
    {
        this._passwordEncoder = passwordEncoder;
    }

    @Override
    public String Encrypt(String password) 
    {
        return _passwordEncoder.encode(password);    
    }
}
