package br.dh.barbearia.java.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import br.dh.barbearia.java.entity.Categoria;
import br.dh.barbearia.java.entity.DisponibilidadeFuncionario;
import br.dh.barbearia.java.entity.Funcionario;
import br.dh.barbearia.java.entity.Hora;
import br.dh.barbearia.java.entity.NivelHierarquico;
import br.dh.barbearia.java.entity.UF;
import br.dh.barbearia.java.service.CategoriaService;
import br.dh.barbearia.java.service.FuncionariosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Funcionarios")
@RequestMapping("/funcionario")
public class FuncionariosController implements WebMvcConfigurer  {	
	
	@Resource
	private FuncionariosService funcService;
	
	@Resource
	private CategoriaService catService;
	

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
			Funcionario funcio = funcService.salvarNovoFunc(func.getCpf(), func.getNome(), func.getDtNasc(), func.getNacionalidade(), func.getGenero(), func.getEmail(),
					 func.getTelefone(), func.getEndereco(), func.getUfs(), func.getCep(), func.getNivels(), func.getCategorias(), func.getMatricula());
			 return ResponseEntity.ok(funcio);
		}
		func.setMsg("Erro");
		 return ResponseEntity.ok(func);

	}
	
	@ApiOperation(value = "atualiza senha do funcionário")
	@PostMapping(value = "/atualizarSenha",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> atualizarSenha(@RequestBody  Funcionario func) {
			Funcionario funcio = funcService.atualizarSenhaFunc(func.getNome(), func.getSenha(), func.getNovaSenha(), func.getMatricula());
	
		 return ResponseEntity.ok(funcio);
	}
	
	@ApiOperation(value = "atualiza dados do funcionário")
	@PostMapping(value = "/atualizarDados",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> atualizarDados(@RequestBody  Funcionario func) {
			Funcionario funcio = funcService.atualizarDadosFunc(func.getNome(),func.getMatricula(),func.getEndereco(), func.getCep(), func.getEmail(), func.getTelefone());
	
		 return ResponseEntity.ok(funcio);
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
		List<Categoria> categoria = catService.buscarCategoria();
		List<Funcionario> func = funcService.buscarTodosFuncionarios();
		categoria.stream().filter(f -> f.getIdCategoria().equals(func.get(0).getCategorias())).collect(Collectors.toList());
		func.get(0).setCategoriass(categoria.get(0).getNome());
		
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
	
	@ApiOperation(value = "Lista todas disponibilidades do funcionario")
	@GetMapping(value = "/getDisponibilidadeDoFuncionario/{funcionario}")
	public  ResponseEntity<?> buscarDisponibilidadeDoFuncionario(@PathVariable("funcionario") String funcionario) {
		List<DisponibilidadeFuncionario> disp = funcService.buscarTodasDisponibilidadeDoFuncionario(funcionario);
		List<DisponibilidadeFuncionario> dt = funcService.datasNaoRepetidasFuncEsp(disp);
				
		return ResponseEntity.ok(dt);
  }
	
	@ApiOperation(value = "Lista todas disponibilidades do funcionario")
	@GetMapping(value = "/getHoraFuncionario/{funcionario}/{data}")
	public  ResponseEntity<?> buscarHorasFuncionario(@PathVariable("funcionario") String funcionario,
			@PathVariable("data") String data) {
		List<DisponibilidadeFuncionario> disp = funcService.buscarHoraDoFuncionarioNaData(funcionario, data);
				
		return ResponseEntity.ok(disp);
  }
	
	@ApiOperation(value = "verifica se a data é passada")
	@GetMapping(value = "/dataPassada/{data}")
	public ResponseEntity<?> verificaDataIsPassada(@PathVariable String data) {
		 String parteAno = data.substring(0,4);
		 String parteMes = data.substring(5,7);
		 String parteDia = data.substring(8,10);
		 String dt = parteDia+"-"+parteMes+"-"+parteAno;
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 Boolean resposta = false;
		 try {
			Date date = formatter.parse(dt);
			resposta= this.funcService.isDataPassada(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return ResponseEntity.ok(resposta);

	}
	
}