package br.dh.barbearia.java.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.dh.barbearia.java.entity.Funcionario;
import br.dh.barbearia.java.entity.Hora;
import br.dh.barbearia.java.service.FuncionariosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Funcionarios")
@RequestMapping("/funcionario")
public class FuncionariosController implements WebMvcConfigurer  {
	
	@Resource
	private FuncionariosService funcService;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}
	
	@ApiOperation(value = "Cria um novo funcionário")
	@PostMapping(value = "/salvaFuncionario/{funcionario}")
	public void adicionaFuncionario(@PathVariable("funcionario") Funcionario func) {
		 funcService.salvarNovoFunc(func.getCpf(), func.getNome(), func.getDtNascimento(), func.getNacionalidade(), func.getGenero(), func.getEmail(),
				 func.getTelefone(), func.getEndereco(), func.getUf(), func.getCep(), func.getNivelFunc(), func.getCategoria());
	}
	

	@ApiOperation(value = "Lista todos os funcionários")
	@GetMapping(value = "/funcionarios")
	public  ResponseEntity<?> buscarFuncionarios() {
		List<Funcionario> func = funcService.buscarTodosFuncionarios();
		return ResponseEntity.ok(func);
		
	}
	
	@ApiOperation(value = "Lista todos os horarios")
	@GetMapping(value = "/hora")
	public  ResponseEntity<?> buscarHorarios() {
		List<Hora> horas = funcService.buscarTodasHoras();
		return ResponseEntity.ok(horas);
		
	}
}
