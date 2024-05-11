package br.com.easyrh.infrastructure.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.easyrh.infrastructure.security.SecurityFilter.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration 
{
    @Autowired
    private SecurityFilter SecurityFilter;

    //Define a configuração de segurança 
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity) throws Exception{

        //Define a configuração de segurança
        return httpSecurity
                .csrf(_csrf -> _csrf.disable())
                .sessionManagement(_session -> _session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //desabilita a criação de sessão 
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() //define as rotas que o usuário anônimo tem acesso
                    .requestMatchers(HttpMethod.POST, "/enterprise").hasRole("ADMIN")//define as rotas que o usuário do tipo ADMIN tem acesso
                    .anyRequest().authenticated()
                )
                .addFilterBefore(SecurityFilter, UsernamePasswordAuthenticationFilter.class) //define o filtro de autenticação antes do filtro de autorização
                .build();//finaliza a configuração
    }

    @Bean 
    public PasswordEncoder Enconde()
    {
        //O BCryptPasswordEncoder gera senhas criptografadas com o algoritmo BCrypt,
        //onde o salt e o hash sao gerados automaticamente
        return new BCryptPasswordEncoder();
    }

    @Bean //define o gerenciador de autenticação
    public AuthenticationManager AuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception 
    {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
