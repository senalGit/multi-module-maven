package fr.maetic.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"fr.maetic.*"})
@EntityScan(basePackages = {"fr.maetic.*"})
@EnableJpaRepositories(basePackages = {"fr.maetic.*"})
public class AppMaetic {
    public static void main(String[] args) {
        SpringApplication.run(AppMaetic.class, args);
    }
}
