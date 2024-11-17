package br.com.desafiojava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.desafiojava.exception.DesafioJavaException;
import br.com.desafiojava.jpa.repository.PessoaRepository;
import br.com.desafiojava.model.Pessoa;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaService {

	private PessoaRepository pessoaRepository;
	
	 // Criar ou Atualizar Pessoa
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    // Deletar Pessoa
    public void delete(Long id) {
    	pessoaRepository.deleteById(id);
    }

    // Atualizar Pessoa (usando merge)
    public Pessoa update(Long id, Pessoa pessoa) throws DesafioJavaException {
        if (pessoaRepository.existsById(id)) {
        	pessoa.setId(id);
            return pessoaRepository.save(pessoa);
        } else {
            throw new DesafioJavaException("Produto não encontrado com ID: " + id);
        }
    }

    // Buscar Pessoa por ID
    public Pessoa getById(Long id) {
        Optional<Pessoa> produto = pessoaRepository.findById(id);
        return produto.orElseThrow(() -> new RuntimeException("Pessoa não encontrado com ID: " + id));
    }

    // Buscar Todos os Produtos
    public List<Pessoa> getAll() {
        return pessoaRepository.findAll(Sort.by(Sort.Order.asc("nome")));
    }
    
    public List<Pessoa> findByGerente(Boolean gerente) {
    	return pessoaRepository.findByGerente(gerente);
    }
    public List<Pessoa> findByFuncionario(Boolean funcionario) {
    	return pessoaRepository.findByFuncionario(funcionario);
    }
}
