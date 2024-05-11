package br.com.easyrh.infrastructure.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                // .authorizeHttpRequests(authorize -> authorize
                //     .requestMatchers(HttpMethod.GET, "/enterprise").hasRole("ADMIN")//define as rotas que o usuário do tipo ADMIN tem acesso
                // )
                .build();//finaliza a configuração
    }

    @Bean 
    public PasswordEncoder Enconde()
    {
        //O BCryptPasswordEncoder gera senhas criptografadas com o algoritmo BCrypt,
        //onde o salt e o hash sao gerados automaticamente
        return new BCryptPasswordEncoder();
    }
}
