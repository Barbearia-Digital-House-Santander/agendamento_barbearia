package br.dh.barbearia.java.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.dh.barbearia.java.entity.Funcionario;
import br.dh.barbearia.java.service.FuncionariosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Autentificar")
@RequestMapping("/autentificar")
public class AutentificacaoController implements WebMvcConfigurer {
	
	@Resource
	private FuncionariosService funcService;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}
	
	@ApiOperation(value = "Autentica login do funcionarionario")
	@GetMapping(value = "/login/{funcionario}")
	public List<Funcionario> logarFuncionario(HttpSession session, @PathVariable("funcionario") Funcionario func) {
	
		List<Funcionario> funcionario = funcService.buscaFuncionarioEspecifico(func.getMatricula(), func.getSenha());	
		  if(!funcionario.isEmpty()) {
	          session.setAttribute("usuarioLogado", func);
	          return funcionario;
	      }
		  return funcionario;
	  }
	
	  public String logout(HttpSession session) {
	      session.invalidate();
	      return "redirect:loginForm";
	  }
}
