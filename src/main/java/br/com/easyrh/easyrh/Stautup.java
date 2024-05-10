package br.com.easyrh.easyrh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.easyrh.api", "br.com.easyrh.exceptions", "br.com.easyrh.application"
, "br.com.easyrh.shered", "br.com.easyrh.utils", "br.com.easyrh.infrastructure", "br.com.easyrh.domain"
,"br.com.easyrh.domain.Entities",  "br.com.easyrh.infrastructure"})
@EntityScan(basePackages = "br.com.easyrh.domain.Entities")
@EnableJpaRepositories(basePackages = "br.com.easyrh.infrastructure.repository")
public class Stautup {

    public static void main(String[] args) {
        SpringApplication.run(Stautup.class, args);
    } 

}
