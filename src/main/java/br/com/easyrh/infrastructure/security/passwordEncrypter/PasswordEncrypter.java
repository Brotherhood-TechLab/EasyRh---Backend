package br.com.easyrh.infrastructure.security.passwordEncrypter;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncrypter implements IPasswordEncrypter
{
    private final static String _passwordComplement = UUID.randomUUID().toString();

    @Autowired
    private PasswordEncoder _passwordEncoder;

    public PasswordEncrypter(PasswordEncoder passwordEncoder)
    {
        this._passwordEncoder = passwordEncoder;
    }

    @Override
    public String Encrypt(String password) 
    {
        password = password + _passwordComplement;
        return _passwordEncoder.encode(password);    
    }
}
