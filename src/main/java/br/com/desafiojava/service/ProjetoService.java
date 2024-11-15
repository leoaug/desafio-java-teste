package br.com.desafiojava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiojava.jpa.repository.ProjetoRepository;
import br.com.desafiojava.model.Projeto;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository projetoRepository;
	
	 // Criar ou Atualizar Projeto
    public Projeto save(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    // Deletar Projeto
    public void delete(Long id) {
    	projetoRepository.deleteById(id);
    }

    // Atualizar Projeto (usando merge)
    public Projeto update(Long id, Projeto projeto) {
        if (projetoRepository.existsById(id)) {
        	projeto.setId(id);
            return projetoRepository.save(projeto);
        } else {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
    }

    // Buscar Projeto por ID
    public Projeto getById(Long id) {
        Optional<Projeto> produto = projetoRepository.findById(id);
        return produto.orElseThrow(() -> new RuntimeException("Projeto não encontrado com ID: " + id));
    }

    // Buscar Todos os Projetos
    public List<Projeto> getAll() {
        return projetoRepository.findAll();
    }
}
