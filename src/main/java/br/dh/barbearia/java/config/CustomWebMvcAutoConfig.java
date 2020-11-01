package br.dh.barbearia.java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebMvcAutoConfig implements WebMvcConfigurer {

    String myExternalFilePath = System.getProperty("user.dir") + "/recibo/"; // end your path with a /

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/recibo/").addResourceLocations(myExternalFilePath);
    }
}