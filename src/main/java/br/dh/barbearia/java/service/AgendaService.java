package br.dh.barbearia.java.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.dh.barbearia.java.commun.Constantes;
import br.dh.barbearia.java.commun.RandomCommun;
import br.dh.barbearia.java.entity.Agenda;
import br.dh.barbearia.java.repository.AgendaRepository;

@Service
public class AgendaService {

	@Resource
	private AgendaRepository agendaRepository;
	
	public String geradorAleatorio() {
		RandomCommun random = new RandomCommun();
		return random.geradorLetrasNumerosAleatorios(Constantes.CARACTERES, Constantes.TAMANHO_RANDOM);
	}

	public List<Agenda> buscaDadosClientePelaChaveDeCancelamento(String chaveDeCancelamento) {
		return agendaRepository.findByChaveDeCancelamento(chaveDeCancelamento);
	}
}
