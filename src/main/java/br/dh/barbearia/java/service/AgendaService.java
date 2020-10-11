package br.dh.barbearia.java.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.dh.barbearia.java.commun.Constantes;
import br.dh.barbearia.java.commun.DataUtils;
import br.dh.barbearia.java.commun.RandomCommun;
import br.dh.barbearia.java.entity.Agenda;
import br.dh.barbearia.java.repository.AgendaRepository;

@Service
public class AgendaService {

	@Resource
	private AgendaRepository agendaRepository;

	
	private String geradorAleatorio() {
		RandomCommun random = new RandomCommun();
		return random.geradorLetrasNumerosAleatorios(Constantes.CARACTERES, Constantes.TAMANHO_RANDOM);
	}

	private List<Agenda> buscaDadosClientePelaChaveDeCancelamento(String chaveDeCancelamento) {
		return agendaRepository.findByChaveDeCancelamento(chaveDeCancelamento);
	}
	
	public String salvarMarcacaoNaAgenda(String cpf, String nome, String servico, Date dataAgendamento, String horaAgendamento,
  		  String genero, String email, String chaveDeCancelamento,String telefone) {

		  Agenda agenda = new Agenda();
		  agenda.setCpf(cpf);
		  agenda.setNome(nome);
		  agenda.setServico(servico);
		  agenda.setDataAgendamento(DataUtils.DataSemHoras(dataAgendamento));
		  agenda.setHoraAgendamento(horaAgendamento);
		  agenda.setGenero(genero);
		  agenda.setEmail(email);
		  agenda.setTelefone(telefone);
		  
		  agenda.setCancelado(Constantes.NAO);
		  agenda.setChaveDeCancelamento(geradorAleatorio());
		  
		  agendaRepository.save(agenda);
		return "redirect:/mensagem/agendamento/ok";
	}
	
	public String atualizarMarcacaoNaAgenda(String chaveDeCancelamento) {
		List<Agenda> dados = buscaDadosClientePelaChaveDeCancelamento(chaveDeCancelamento);
	    Agenda agenda = new Agenda();

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
}
