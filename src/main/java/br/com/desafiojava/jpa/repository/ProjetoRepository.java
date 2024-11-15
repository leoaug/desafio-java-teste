package br.com.desafiojava.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiojava.model.Projeto;


public interface ProjetoRepository extends JpaRepository <Projeto, Long> {

}
