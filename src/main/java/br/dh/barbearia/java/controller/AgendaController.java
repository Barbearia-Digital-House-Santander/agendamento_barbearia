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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.dh.barbearia.java.commun.Constantes;
import br.dh.barbearia.java.email.EnviarEmail;
import br.dh.barbearia.java.entity.Agenda;
import br.dh.barbearia.java.entity.Categoria;
import br.dh.barbearia.java.entity.DisponibilidadeFuncionario;
import br.dh.barbearia.java.entity.Funcionario;
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

	private EnviarEmail enviarEmail;
	
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
	@RequestMapping(value = "/salvarAgenda", method = RequestMethod.POST)
	public  ResponseEntity<?> adicionaAgenda(@RequestBody  Agenda agenda) {

		 agendaService.salvarMarcacaoNaAgenda(agenda.getCpf(), agenda.getNome(), agenda.getTelefone(), agenda.getCategorias(),
				 agenda.getData(), agenda.getEmail(), agenda.getSexo(), agenda.getValor(), agenda.getFuncionario(),
				 agenda.getHora(), agenda.getServicos());
		 
		 List<Hora> hora = agendaService.buscarHoras();
				for(Hora y : hora) {
					if(y.getIdHora().equals(agenda.getHora())) {
						agenda.setHoras(y.getHora());
					}
				
				}
		String msg = agendaService.mensagemEmail(agenda.getNome(),  agenda.getData(), agenda.getHoras(),  agenda.getFuncionario());

		 enviarEmail.enviarGmail(agenda.getEmail(), Constantes.ASSUNTO_AGENDAMENTO, msg);
		 //String filename = agendaService.agendamentoOK("recibo");
		 return ResponseEntity.ok("OK");
	}

	@ApiOperation(value = "Verifica se pode salvar agendamento")
	@RequestMapping(value = "/podeSalvar", method = RequestMethod.POST)
	public  ResponseEntity<?> verificaAgenda(@RequestBody  Agenda agenda) {

		String func = agendaService.setarFuncionario(agenda.getFuncionario(), agenda.getCategorias());
		String verifica = agendaService.verificarSePodeSalvarAgendamento(agenda.getData(), agenda.getHora(), func);
		agenda.setMsg(verifica);
		 return ResponseEntity.ok(agenda);
	}
	
	@ResponseBody
	@ApiOperation(value = "Cria um novo agendamento")
	@RequestMapping(value = "notificacaoAgendamentoOK", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> AgendamentoOK(String recibo) throws IOException {
		
		HttpHeaders headers = new HttpHeaders();

	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    String filename = agendaService.agendamentoOK( recibo);
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
	
	@ApiOperation(value = "Busca os serviço do id")
	@GetMapping(value = "/valorDoServico/{id}")
	public  ResponseEntity<?> buscarServicoSelecionado(@PathVariable("id") Integer idServico) {
		List<Servicos> servs = servicosService.listaServicoDoId(idServico);
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
