package br.com.easyrh.infrastructure.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration 
{
    @Bean
    public OpenAPI CustomAPI()
    {
        return new OpenAPI().info(new Info()
                .title("EasyRH API")
                .description("API para gerenciamento de funcionários")
                .version("1.0.0")
                .license(new io.swagger.v3.oas.models.info.License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                )
                .contact(new io.swagger.v3.oas.models.info.Contact()
                        .name("Brotherhood TechLab")
                        .url("https://github.com/Brotherhood-TechLab")
                        .email("amadeumartim@gmail.com")
                ));
    }
}
