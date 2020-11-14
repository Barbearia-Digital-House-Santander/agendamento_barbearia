package br.dh.barbearia.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Navegação entre as páginas da barbearia")
@RequestMapping("/barbearia")
public class TemplateController {
	
	  @Autowired
	  public TemplateController() {
		// TODO Auto-generated constructor stub
	}
	  @ApiOperation(value = "Página da galeria")
	  @RequestMapping(value = "/galeria", method = RequestMethod.GET)
      public String abrirPaginaGaleria() {
            return "galeria";
      }
	  
	  @ApiOperation(value = "Página de serviços")
	  @RequestMapping(value = "/servicos", method = RequestMethod.GET)
      public String abrirPaginaServicos() {
            return "servicos";
      }
	  
	  @ApiOperation(value = "Página sobre da barbearia")
	  @RequestMapping(value = "/sobre", method = RequestMethod.GET)
      public String abrirPaginaSobre() {
            return "sobre";
      }
	  
	  @ApiOperation(value = "Página de login")
	  @RequestMapping(value = "/login", method = RequestMethod.GET)
      public String abrirPaginaLogin() {
            return "login";
      }
	  
	  @ApiOperation(value = "Página inicial")
	  @RequestMapping(value = "/index", method = RequestMethod.GET)
      public String abrirPaginaIndex() {
            return "index";
      }
	  
	  @ApiOperation(value = "Página da administração")
	  @RequestMapping(value = "/adm", method = RequestMethod.GET)
      public String abrirPaginaAdm() {
            return "adm";
      }
}
