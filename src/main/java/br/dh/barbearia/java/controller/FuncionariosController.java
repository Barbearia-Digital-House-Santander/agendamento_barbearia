package br.dh.barbearia.java.controller;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

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
import br.dh.barbearia.java.entity.NivelHierarquico;
import br.dh.barbearia.java.entity.UF;
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
	@PostMapping(value = "/salvaFuncionario/{nome}/{cpf}/{dtNasc}/{endereco}/{uf}/{cep}/{nacionalidade}/{genero}/{telefone}/{categoria}/{email}/{nivel}")
	public ResponseEntity<?> adicionaFuncionario(@PathVariable("nome") String nome,@PathVariable("cpf") String cpf,
			@PathVariable("endereco") String endereco,@PathVariable("cep") String cep,
			@PathVariable("uf") Integer uf,@PathVariable("telefone") String telefone,
			@PathVariable("email") String email,@PathVariable("categoria") Integer categoria,
			@PathVariable("nivel") Integer nivel,@PathVariable("genero") String genero,
			@PathVariable("nacionalidade") String nacionalidade,@PathVariable("dtNasc") LocalDate dtNasc) {
		
		 funcService.salvarNovoFunc(cpf, nome, dtNasc, nacionalidade, genero, email,
				 telefone, endereco, uf, cep, nivel, categoria);
		 return ResponseEntity.ok("Salvo");
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
	
	@ApiOperation(value = "Lista todos os niveis")
	@GetMapping(value = "/nivel")
	public  ResponseEntity<?> buscarNivel() {
		List<NivelHierarquico> nivel = funcService.buscarTodosOsNiveis();
		return ResponseEntity.ok(nivel);
		
	}
	
	@ApiOperation(value = "Lista todos os ufs")
	@GetMapping(value = "/ufs")
	public  ResponseEntity<?> buscarUF() {
		List<UF> ufs = funcService.buscarTodosUFs();
		return ResponseEntity.ok(ufs);
		
	}
}
