package br.com.easyrh.easyrh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.easyrh.controllers", "br.com.easyrh.exceptions", "br.com.easyrh.application"})
public class Stautup {

    public static void main(String[] args) {
        SpringApplication.run(Stautup.class, args);
    }

}
