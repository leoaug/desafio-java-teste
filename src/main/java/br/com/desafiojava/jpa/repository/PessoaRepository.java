package br.com.desafiojava.jpa.repository;

import br.com.desafiojava.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
