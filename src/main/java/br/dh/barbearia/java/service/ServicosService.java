package br.dh.barbearia.java.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.dh.barbearia.java.entity.Servicos;
import br.dh.barbearia.java.repository.ServicosRepository;

@Service
public class ServicosService {
	
	@Resource
	private ServicosRepository servicosRepository;

	public List<Servicos> buscarServicosDisponiveis() {
		return servicosRepository.findAll();
	}

	public List<Servicos> listaServicosCategoria(Integer id) {
		return servicosRepository.findByIdCategoria(id);
	}
}
