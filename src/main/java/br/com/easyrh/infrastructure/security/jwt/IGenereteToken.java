package br.com.easyrh.infrastructure.security.jwt;

public interface IGenereteToken 
{
    public String GenereteToken(String email);

    public String ValidateToken(String token);
}
