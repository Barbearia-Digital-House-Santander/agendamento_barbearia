package br.dh.barbearia.java.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.dh.barbearia.java.entity.Servicos;
import br.dh.barbearia.java.repository.AgendaRepository;
import br.dh.barbearia.java.service.AgendaService;
import br.dh.barbearia.java.service.ServicosService;

@Controller
@RequestMapping("/barbearia")
public class AgendaController  extends HttpServlet {
	  
	  @Resource
	  private AgendaService agendaService;
	  
	  @Resource
	  private ServicosService servicosService;

      @Autowired
      public AgendaController( AgendaRepository agendaRepository) {
      }
	  
	  @RequestMapping(value = "/agendamento", method = RequestMethod.POST)
      public String adicionaAgenda(String cpf, String nome, String servico, Date dataAgendamento, String horaAgendamento,
    		  String genero, String email, String telefone) {
		  
          return agendaService.salvarMarcacaoNaAgenda( cpf,  nome,  servico,  dataAgendamento,  horaAgendamento, genero,  email,  telefone);
      }
	  
	  @RequestMapping(value = "/agendamento", method = RequestMethod.GET)
      public String abrirPaginaAgendamento(Model model) {
		  List<Servicos> ls = servicosService.buscarServicosDisponiveis();		  
		  model.addAttribute("ls", ls);
		  model.addAttribute("selecionado", new Servicos(100, "Selecione o serviço"));
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
	  
	  @RequestMapping(value = "notificacaoAgendamentoOK", method = RequestMethod.POST)
      public String AgendamentoOK(String ok, String recibo) {
		 return agendaService.agendamentoOK(ok, recibo);
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
	  
	  ///selects pelo banco
//	  
//	  @GetMapping("/agendamento")
		public String index(Model model) {
		  
			
			model.addAttribute( "selecionado");

			return "agendamento";
		}
}
