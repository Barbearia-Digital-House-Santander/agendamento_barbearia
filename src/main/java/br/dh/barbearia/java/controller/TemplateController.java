package br.dh.barbearia.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/barbearia")
public class TemplateController {
	
	  @Autowired
	  public TemplateController() {
		// TODO Auto-generated constructor stub
	}
	  @RequestMapping(value = "/galeria", method = RequestMethod.GET)
      public String abrirPaginaGaleria() {
            return "galeria";
      }
	  
	  @RequestMapping(value = "/servicos", method = RequestMethod.GET)
      public String abrirPaginaServicos() {
            return "servicos";
      }
	  
	  @RequestMapping(value = "/sobre", method = RequestMethod.GET)
      public String abrirPaginaSobre() {
            return "sobre";
      }
	  
	  @RequestMapping(value = "/login", method = RequestMethod.GET)
      public String abrirPaginaLogin() {
            return "login";
      }
}
