package br.dh.barbearia.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dh.barbearia.java.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
