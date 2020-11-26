package br.dh.barbearia.java.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.dh.barbearia.java.entity.Agenda;
import br.dh.barbearia.java.entity.Categoria;
import br.dh.barbearia.java.entity.DisponibilidadeFuncionario;
import br.dh.barbearia.java.entity.Hora;
import br.dh.barbearia.java.entity.Servicos;
import br.dh.barbearia.java.service.AgendaService;
import br.dh.barbearia.java.service.CategoriaService;
import br.dh.barbearia.java.service.ServicosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Agenda")
@RequestMapping("/agenda")
public class AgendaController implements WebMvcConfigurer{

	@Resource
	private AgendaService agendaService;

	@Resource
	private ServicosService servicosService;

	@Resource
	private CategoriaService categoriaService;

	@Autowired
	public AgendaController() {
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}
	
	@ApiOperation(value = "Cria um novo agendamento")
	@RequestMapping(value = "/agendamento", method = RequestMethod.POST)
	public void adicionaAgenda(String cpf, String nome, String servico, String dataAgendamento, String horaAgendamento,
			String genero, String email, String telefone) {

		LocalDate data = LocalDate.parse(dataAgendamento);
		 agendaService.salvarMarcacaoNaAgenda(cpf, nome, servico, data, horaAgendamento, genero, email,
				telefone);
	}

	
	
	@ResponseBody
	@ApiOperation(value = "Cria um novo agendamento")
	@RequestMapping(value = "notificacaoAgendamentoOK", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> AgendamentoOK(String ok, String recibo) throws IOException {
		
		HttpHeaders headers = new HttpHeaders();

	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    String filename = agendaService.agendamentoOK(ok, recibo);
	    Path filePath = Paths.get(System.getProperty("user.dir") + "/recibo/" + filename);

	    headers.add("Content-Disposition", "attachment; filename=" + filename);

	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    byte[] pdfBytes = Files.readAllBytes(filePath);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfBytes, headers, HttpStatus.OK);
		
	    return response;
	}
	
	@ApiOperation(value = "Lista todas as categorias")
	@GetMapping(value = "/categorias")
	public  ResponseEntity<?> buscarCategorias() {
		List<Categoria> cat = categoriaService.buscarCategoria();
		return ResponseEntity.ok(cat);
		
	}
	
	@ApiOperation(value = "Busca todos os serviços de uma categoria")
	@GetMapping(value = "/servicosDaCategoria/{categoria}")
	public  ResponseEntity<?> buscarServicosDaCategoria(@PathVariable("categoria") Integer categoria) {
		List<Servicos> servs = servicosService.listaServicosCategoria(categoria);
		return ResponseEntity.ok(servs);
		
	}
	
	@ApiOperation(value = "Busca todos agendamentos")
	@GetMapping(value = "/agendamentos")
	public  ResponseEntity<?> buscarAgendamentos() {
		List<Agenda> ags = agendaService.buscarMarcacoesAgenda();
		return ResponseEntity.ok(ags);
		
	}
	
	@ApiOperation(value = "Lista todos os horarios disponiveis")
	@GetMapping(value = "/horaDisponivel/{data}")
	public  ResponseEntity<?> buscarHorariosDisponiveis(@PathVariable("data") String data) {
		List<DisponibilidadeFuncionario> disp = agendaService.buscarTodasHorasDisponiveis(data);
		List<Hora> hora = agendaService.buscarHoras();
		for(DisponibilidadeFuncionario d : disp) {
			Integer h = d.getHora();
			for(Hora y : hora) {
				if(y.getIdHora().equals(h)) {
					d.setHoraS(y.getHora());
				}
			
			}
		}
		return ResponseEntity.ok(disp);
		
	}
	
	@ApiOperation(value = "Lista todos as datas")
	@GetMapping(value = "/datahoraDisponiveis")
	public  ResponseEntity<?> buscarDatasDisponiveis() {
		List<DisponibilidadeFuncionario> datas = agendaService.buscarTodasDatasDisponiveis();
		return ResponseEntity.ok(datas);
	}

}
