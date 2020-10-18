package br.dh.barbearia.java.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//configuração Spring MVC

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { JPAConfiguration.class };
	}

	@Override
	// Classes de configuração do spring
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { AppWebConfiguration.class };
	}

	@Override
	// Mapeamento dos endpoints
	protected String[] getServletMappings() {
		return new String[] { "/barbearia" };
	}

}
