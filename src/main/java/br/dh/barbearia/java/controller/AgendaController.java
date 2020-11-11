package br.dh.barbearia.java.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.dh.barbearia.java.entity.Categoria;
import br.dh.barbearia.java.entity.Servicos;
import br.dh.barbearia.java.service.AgendaService;
import br.dh.barbearia.java.service.CategoriaService;
import br.dh.barbearia.java.service.ServicosService;

@Controller
@RequestMapping("/barbearia")
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
	
	@RequestMapping(value = "/agendamento", method = RequestMethod.POST)
	public String adicionaAgenda(String cpf, String nome, String servico, String dataAgendamento, String horaAgendamento,
			String genero, String email, String telefone) {

		LocalDate data = LocalDate.parse(dataAgendamento);
		return agendaService.salvarMarcacaoNaAgenda(cpf, nome, servico, data, horaAgendamento, genero, email,
				telefone);
	}

	@RequestMapping(value = "/agendamento", method = RequestMethod.GET)
	public String abrirPaginaAgendamento(Model model) {
		this.selectCategoria(model);
		this.selectServicos(model);
		return "agendamento";
	}

	@RequestMapping(value = "/cancelamento", method = RequestMethod.POST)
	public String atualizarAgenda(String chaveDeCancelamento) {
		return agendaService.atualizarMarcacaoNaAgenda(chaveDeCancelamento);
	}

	@RequestMapping(value = "/cancelamento", method = RequestMethod.GET)
	public String abrirPaginaCancelamento() {
		return "cancelamento";
	}
	
	@RequestMapping(value = "/cancelamentoEfetuado", method = RequestMethod.GET)
	public String cancelamentoEfetuado() {
		return "cancelamentoEfetuado";
	}
	
	@RequestMapping(value = "/cancelamento/falha", method = RequestMethod.GET)
	public String cancelamentoFalha() {
		return "falha";
	}
	
	@RequestMapping(value = "/cancelamento/falha", method = RequestMethod.POST)
	public String retornarCancelamento() {
		return "cancelamento";
	}
	
	@ResponseBody
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

	@RequestMapping(value = "notificacaoAgendamentoOK", method = RequestMethod.GET)
	public String abrirPaginaAgendamentoOK() {
		return "notificacaoAgendamentoOK";
	}

	@RequestMapping(value = "/menuAgenda", method = RequestMethod.GET)
	public String abrirMenuAgenda() {
		return "menuAgenda";
	}

	@RequestMapping(value = "/ajuda", method = RequestMethod.GET)
	public String abrirPaginaAjuda() {
		return "ajuda";
	}

	/// selects pelo banco

	public void selectServicos(Model model) {
		List<Servicos> ls = new ArrayList<Servicos>(Arrays.asList(new Servicos(100,"Selecione o serviço")));
		ls.addAll(servicosService.buscarServicosDisponiveis());
		model.addAttribute("ls", ls);
		model.addAttribute("selecionado", new Servicos(100, "Selecione o serviço"));
	}

	public void selectCategoria(Model model) {
		List<Categoria> ls = new ArrayList<Categoria>(Arrays.asList(new Categoria(59,"Selecione a categoria")));
		ls.addAll(categoriaService.buscarCategoria());
		model.addAttribute("listaCat", ls);
		model.addAttribute("selecionado", new Categoria(59, "Selecione a categoria"));
	}
	///////////////////
	
	
	@GetMapping(value = "/categorias")
	public  ResponseEntity<?> buscarCategorias() {
		List<Categoria> cat = categoriaService.buscarCategoria();
		return ResponseEntity.ok(cat);
		
	}
	
	
	@GetMapping(value = "/servicosDaCategoria/{categoria}")
	public  ResponseEntity<?> buscarServicosDaCategoria(@PathVariable("categoria") Integer categoria) {
		List<Servicos> servs = servicosService.listaServicosCategoria(categoria);
		return ResponseEntity.ok(servs);
		
	}
}
