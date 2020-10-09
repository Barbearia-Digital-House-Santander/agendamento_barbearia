package br.dh.barbearia.java.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		  agenda.setEmail(email);;
		  agenda.setTelefone(telefone);
	
		  agenda.setNumProcCancelamento(agendaService.geradorAleatorio());
		  
          agendaRepository.save(agenda);
          return "redirect:/agendamento";
      }
	  
	  @RequestMapping(value = "/agendamento", method = RequestMethod.GET)
      public String abrirPaginaAgendamento() {
            return "agendamento";
      }
	
}
