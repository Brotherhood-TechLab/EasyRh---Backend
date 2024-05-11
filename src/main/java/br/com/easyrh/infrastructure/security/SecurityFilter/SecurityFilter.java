package br.com.easyrh.infrastructure.security.SecurityFilter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.easyrh.infrastructure.repository.userRepository.IUserRepository;
import br.com.easyrh.infrastructure.security.jwt.IGenereteToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter 
{
    @Autowired
    IGenereteToken _tokenService;

    @Autowired
    IUserRepository _userRepository;

    //Esse método só vai ser chamado uma vez por requisição 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
    throws IOException, ServletException
    {
        var token = GetToken(request);

        if(!IsTokenNull(token))
        {
            var email = GetTokenSubject(token);//Recuperando o assinante do token

            UserDetails user = GetUser(email);//Recuperando o usuário

            var authentication = GetAuthentication(user);//Autenticando o usuário

            SecurityContextHolder.getContext().setAuthentication(authentication);//Definindo o contexto de segurança com o usuário autenticado
        }
        
        filterChain.doFilter(request, response);
    }

    private String RecoverToken(HttpServletRequest request)
    {
        var authHeader= request.getHeader("Authorization"); //Recebe o token da requisição 
        if(authHeader == null)
        {
            return null;
        }
        else
        {
            //Por padrão o token vem com o formato Bearer <token>, pegando so o <token>
            return authHeader.replace("Bearer ", "");
        }
    }

    private String GetToken(HttpServletRequest request)
    {
        return this.RecoverToken(request);
    }

    private boolean IsTokenNull(String token)
    {
        return token == null;
    }

    private String GetTokenSubject(String token)
    {
        var subject = _tokenService.ValidateToken(token);
        return subject;
    }

    private UserDetails GetUser(String email)
    {
        return _userRepository.findByEmail(email);
    }

    private UsernamePasswordAuthenticationToken GetAuthentication(UserDetails user)
    {
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
}
