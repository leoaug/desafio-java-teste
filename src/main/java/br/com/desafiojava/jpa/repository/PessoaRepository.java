package br.com.desafiojava.jpa.repository;

import br.com.desafiojava.model.Pessoa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	 // Consulta personalizada para buscar pessoas pelo gerente
    List <Pessoa> findByGerente(Boolean gerente);
    
    List <Pessoa> findByFuncionario(Boolean funcionario);
}
