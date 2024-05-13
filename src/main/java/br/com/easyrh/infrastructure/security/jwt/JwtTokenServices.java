package br.com.easyrh.infrastructure.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.easyrh.exceptions.ErrorOnValidationException;

@Service
public class JwtTokenServices implements IGenereteToken
{
    @Value("${api.security.secret}")
    private String SECRET_KEY;

    @Value("${api.security.expire-lenght}")
    private int EXPIRATION_TIME;

    private final String ISSUER = "EasyRH-API";

    public String GenereteToken(String email)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); //A secrete define uma chave para assinar o token que será unica para cada aplicação
            String token = JWT.create()
                                .withIssuer(ISSUER)//Define o emissor do token
                                .withSubject(email)//Define o assinante do token
                                .withExpiresAt(GenExpirationDate())//Define o tempo de expiração do token
                                .sign(algorithm);//Assina o token

            return token;
        }
        catch(JWTCreationException ex)
        {
            throw new ErrorOnValidationException("Ocorreu um erro ao gerar o token");
        }
    }

    private Instant GenExpirationDate()
    {
        //Define o tempo de expiração do token, adicionando 2 horas a partir da hora atual
        return LocalDateTime.now().plusHours(EXPIRATION_TIME).toInstant(ZoneOffset.of("-03:00")); //Timezone brasilia
    }

    public String ValidateToken(String token)
    {
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
            .withIssuer(ISSUER)//Define o emissor do token
            .build()//Construir o token
            .verify(token)//Verifica o token
            .getSubject();//Retorna o assinante do token
        }
        catch(JWTVerificationException ex)
        {
            return "";
        }
    }
}
