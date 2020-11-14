package br.dh.barbearia.java.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EntityScan(basePackages = { "br.dh.barbearia.java.entity" })
@EnableJpaRepositories(basePackages = { "br.dh.barbearia.java.repository" })
@ComponentScan(basePackages = {"br.dh.barbearia.java.controller", "br.dh.barbearia.java.service"})
@EnableSwagger2

@SpringBootApplication
public class AgendamentoBarbeariaDhApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoBarbeariaDhApplication.class, args);
	}
	
}
