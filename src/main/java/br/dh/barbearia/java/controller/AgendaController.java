package br.dh.barbearia.java.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.dh.barbearia.java.repository.AgendaRepository;
import br.dh.barbearia.java.service.AgendaService;

@Controller
@RequestMapping("/barbearia")
public class AgendaController {
	  
	  @Resource
	  private AgendaService agendaService;

      @Autowired
      public AgendaController( AgendaRepository agendaRepository) {
      }
	  
	  @RequestMapping(value = "/agendamento", method = RequestMethod.POST)
      public String adicionaAgenda(String cpf, String nome, String servico, Date dataAgendamento, String horaAgendamento,
    		  String genero, String email, String telefone) {
		  
          return agendaService.salvarMarcacaoNaAgenda( cpf,  nome,  servico,  dataAgendamento,  horaAgendamento, genero,  email,  telefone);
      }
	  
	  @RequestMapping(value = "/agendamento", method = RequestMethod.GET)
      public String abrirPaginaAgendamento() {
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
	  
}
