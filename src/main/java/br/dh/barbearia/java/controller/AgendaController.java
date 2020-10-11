package br.dh.barbearia.java.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.dh.barbearia.java.commun.Constantes;
import br.dh.barbearia.java.commun.DataUtils;
import br.dh.barbearia.java.entity.Agenda;
import br.dh.barbearia.java.repository.AgendaRepository;
import br.dh.barbearia.java.service.AgendaService;

@Controller
@RequestMapping("/barbearia")
public class AgendaController {
	
	  private AgendaRepository agendaRepository;
	  
	  @Resource
	  private AgendaService agendaService;

      @Autowired
      public AgendaController( AgendaRepository agendaRepository) {
            this.agendaRepository = agendaRepository;
      }
	  
	  @RequestMapping(value = "/agendamento", method = RequestMethod.POST)
      public String adicionaAgenda(String cpf, String nome, String servico, Date dataAgendamento, String horaAgendamento,
    		  String genero, String email, String numProc,String telefone, Agenda agenda) {
		  agenda.setCpf(cpf);
		  agenda.setNome(nome);
		  agenda.setServico(servico);
		  agenda.setDataAgendamento(DataUtils.DataSemHoras(dataAgendamento));
		  agenda.setHoraAgendamento(horaAgendamento);
		  agenda.setGenero(genero);
		  agenda.setEmail(email);
		  agenda.setTelefone(telefone);
		  
		  agenda.setCancelado(Constantes.NAO);
		  agenda.setChaveDeCancelamento(agendaService.geradorAleatorio());
		  
          agendaRepository.save(agenda);
          return "redirect:/mensagem/agendamento/ok";
      }
	  
	  @RequestMapping(value = "/agendamento", method = RequestMethod.GET)
      public String abrirPaginaAgendamento() {
            return "agendamento";
      }
	  
	  @RequestMapping(value = "/cancelamento", method = RequestMethod.POST)
      public String adicionaAgenda(String chaveDeCancelamento, Agenda agenda) {
		  List<Agenda> dados = agendaService.buscaDadosClientePelaChaveDeCancelamento(chaveDeCancelamento);
		  
		  if(!dados.isEmpty()) {
			  agenda.setIdAgendamento(dados.get(0).getIdAgendamento());
			  agenda.setCpf(dados.get(0).getCpf());
			  agenda.setNome(dados.get(0).getNome());
			  agenda.setServico(dados.get(0).getServico());
			  agenda.setDataAgendamento(dados.get(0).getDataAgendamento());
			  agenda.setHoraAgendamento(dados.get(0).getHoraAgendamento());
			  agenda.setGenero(dados.get(0).getGenero());
			  agenda.setEmail(dados.get(0).getEmail());
			  agenda.setTelefone(dados.get(0).getTelefone());
			  agenda.setChaveDeCancelamento(chaveDeCancelamento);
			  agenda.setCancelado(Constantes.SIM);
			  agenda.setDataCancelamento(LocalDate.now());
			  agendaRepository.save(agenda);
	          return "redirect:/mensagem/cancelamento/ok";
		  }
		  else {
          return "redirect:/mensagem/cancelamento/falha";
          }
      }
	
	  @RequestMapping(value = "/cancelamento", method = RequestMethod.GET)
      public String abrirPaginaCancelamento() {
            return "cancelamento";
      }
	  
}
