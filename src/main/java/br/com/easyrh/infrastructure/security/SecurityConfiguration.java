package br.com.easyrh.infrastructure.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration 
{
    //Define a configuração de segurança 
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity) throws Exception{

        //Define a configuração de segurança
        return httpSecurity
                .csrf(_csrf -> _csrf.disable())
                .sessionManagement(_session -> _session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //desabilita a criação de sessão 
                .build();//finaliza a configuração
    }
}
