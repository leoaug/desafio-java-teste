package br.com.desafiojava.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiojava.model.Projeto;


public interface ProjetoRepository extends JpaRepository <Projeto, Long> {

	 List<Projeto> findAll(Specification<Projeto>  spec);
}
