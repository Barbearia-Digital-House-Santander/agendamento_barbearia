package br.dh.barbearia.java.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.dh.barbearia.java.commun.Constantes;
import br.dh.barbearia.java.commun.RandomCommun;
import br.dh.barbearia.java.entity.DisponibilidadeFuncionario;
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
	@PostMapping(value = "/salvaFuncionario",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> adicionaFuncionario(@RequestBody  Funcionario func, RandomCommun randomCommun) {
		if(Boolean.TRUE.equals(func.getNivelFuncLogado().equals(Constantes.NIVEL_GERENTE))) {
			func.setMatricula(randomCommun.geradorLetrasNumerosAleatorios(Constantes.CARACTERES, Constantes.TAMANHO_RANDOM));
			func.setSenha(randomCommun.geradorLetrasNumerosAleatorios(Constantes.CARACTERES, Constantes.TAMANHO_RANDOM));
			funcService.salvarNovoFunc(func.getCpf(), func.getNome(), func.getDtNasc(), func.getNacionalidade(), func.getGenero(), func.getEmail(),
					 func.getTelefone(), func.getEndereco(), func.getUfs(), func.getCep(), func.getNivels(), func.getCategorias(), func.getMatricula(), func.getSenha());
			 return ResponseEntity.ok(func);
		}
		 return ResponseEntity.ok("Erro");

	}
	
	@ApiOperation(value = "Cria disponilidade")
	@PostMapping(value = "/salvarDisponibilidade",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> adicionaFuncionario(@RequestBody  DisponibilidadeFuncionario disp) {
		String resultado = funcService.salvarNovaDisponibilidade(disp.getFuncionario(), disp.getData(), disp.getHora());
			 return ResponseEntity.ok(resultado);
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
	
	@ApiOperation(value = "Lista todos os ufs")
	@GetMapping(value = "/getDisponibilidadeDoFuncionario/{funcionario}")
	public  ResponseEntity<?> buscarDisponibilidadeDoFuncionario(@PathVariable("funcionario") String funcionario) {
		List<DisponibilidadeFuncionario> disp = funcService.buscarTodasDisponibilidadeDoFuncionario(funcionario);
		return ResponseEntity.ok(disp);
		
	}
}
