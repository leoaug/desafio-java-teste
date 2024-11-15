package br.com.desafiojava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiojava.jpa.repository.PessoaRepository;
import br.com.desafiojava.model.Pessoa;

@Service
public class PessoaService {

	@Autowired
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
    public Pessoa update(Long id, Pessoa pessoa) {
        if (pessoaRepository.existsById(id)) {
        	pessoa.setId(id);
            return pessoaRepository.save(pessoa);
        } else {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
    }

    // Buscar Pessoa por ID
    public Pessoa getById(Long id) {
        Optional<Pessoa> produto = pessoaRepository.findById(id);
        return produto.orElseThrow(() -> new RuntimeException("Pessoa não encontrado com ID: " + id));
    }

    // Buscar Todos os Produtos
    public List<Pessoa> getAll() {
        return pessoaRepository.findAll();
    }
    
    public List<Pessoa> findByGerente(Boolean gerente) {
    	return pessoaRepository.findByGerente(gerente);
    }
}
