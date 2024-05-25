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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.easyrh.infrastructure.security.SecurityFilter.SecurityFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration implements WebMvcConfigurer {
  @Autowired
  private SecurityFilter SecurityFilter;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("http://localhost:5173") // Define o domínio que o CORS deve ser habilitado
        .allowedMethods("GET", "POST", "PUT", "DELETE");
    // Esse método existe para definir as rotas que o usuário anônimo tem acesso e
    // o CORS deve ser habilitado
  }

  private static final String[] AUTH_WHITELIST = {
      // -- Swagger UI v2
      "/v2/api-docs",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**",
      // -- Swagger UI v3 (OpenAPI)
      "/v3/api-docs/**",
      "/swagger-ui/**",
      // other public endpoints of your API may be appended to this array
      "/api/auth/v1/login",
      "/api/user/v1/**"
  };

  // Define a configuração de segurança
  @Bean
  public SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
    // Define a configuração de segurança
    return httpSecurity
        .csrf(_csrf -> _csrf.disable())
        .sessionManagement(_session -> _session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // desabilita a
                                                                                                        // criação de
                                                                                                        // sessão
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(AUTH_WHITELIST).permitAll()
            .requestMatchers(HttpMethod.POST, "/api/enterprise/v1").hasRole("ADMIN")// define as rotas que o usuário do
                                                                                    // tipo ADMIN tem acesso
            .anyRequest().authenticated())
        .addFilterBefore(SecurityFilter, UsernamePasswordAuthenticationFilter.class) // define o filtro de
                                                                                     // autenticação antes do filtro
                                                                                     // de autorização
        .build();// finaliza a configuração
  }

  @Bean
  public PasswordEncoder Enconde() {
    // O BCryptPasswordEncoder gera senhas criptografadas com o algoritmo BCrypt,
    // onde o salt e o hash sao gerados automaticamente
    return new BCryptPasswordEncoder();
  }

  @Bean // define o gerenciador de autenticação
  public AuthenticationManager AuthenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
