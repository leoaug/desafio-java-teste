package br.com.desafiojava.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiojava.model.Membro;


public interface MembroRepository extends JpaRepository <Membro, Long> {

}
