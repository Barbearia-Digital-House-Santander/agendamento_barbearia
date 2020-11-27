package br.dh.barbearia.java.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.dh.barbearia.java.entity.Contato;
import br.dh.barbearia.java.repository.ContatoRepository;

@Service
public class ContatoService {
	@Resource
	private ContatoRepository contatoRepository;
	
	public Contato salvarContato(Contato contato) {
		return contatoRepository.save(contato);
	}

	public List<Contato> getAllContatos() {
		return contatoRepository.findAll();
	}
}
