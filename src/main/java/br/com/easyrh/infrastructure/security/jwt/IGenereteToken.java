package br.com.easyrh.infrastructure.security.jwt;

public interface IGenereteToken 
{
    public String GenereteToken(String userIdentifier);

    public String ValidateToken(String token);
}
