package br.dh.barbearia.java.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.dh.barbearia.java.config.Password;
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
	@GetMapping(value = "/login/{matricula}/{senha}")
	public ResponseEntity<?> logarFuncionario(HttpSession session, @PathVariable("matricula") String matricula,
			@PathVariable("senha") String senha) {
		
		List<Funcionario> funcionario = funcService.buscaFuncionarioEspecifico(matricula);	
		  if(!funcionario.isEmpty()) {
			  boolean senhaChecada = Password.checkPassword(senha, funcionario.get(0).getSenha());
			  if(Boolean.TRUE.equals(senhaChecada)) {
				  session.setAttribute("usuarioLogado", funcionario.get(0).getNome());
				  funcionario.get(0).setMsg("ok");
		          return ResponseEntity.ok(funcionario);
			  }
	        
	      }
		  funcionario.get(0).setMsg("erro");
		  return ResponseEntity.ok(funcionario);
	  }
	
	@ApiOperation(value = "Deslogar login do funcionarionario")
	@GetMapping(value = "/logout/{usuario}")
	  public ResponseEntity<?>  logout(HttpSession session, @PathVariable String usuario) {
		  session.setAttribute("usuarioLogado", usuario);
	      session.invalidate();
	      return ResponseEntity.ok("Deslogado");
	  }
}
