package br.com.desafiojava.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.desafiojava.exception.DesafioJavaException;
import br.com.desafiojava.jpa.especification.ProjetoSpecification;
import br.com.desafiojava.jpa.filter.ProjetoFilter;
import br.com.desafiojava.jpa.repository.ProjetoRepository;
import br.com.desafiojava.model.Projeto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjetoService {

	private ProjetoRepository projetoRepository;
	
	// Criar ou Atualizar Projeto
	public Projeto save(Projeto projeto) {
		return projetoRepository.save(projeto);
	}

	// Deletar Projeto
	public void delete(Long id) throws PersistenceException {
		projetoRepository.deleteById(id);
	}

	// Atualizar Projeto (usando merge)
	public Projeto update(Long id, Projeto projeto) throws DesafioJavaException {
		if (projetoRepository.existsById(id)) {
			projeto.setId(id);
			return projetoRepository.save(projeto);
		} else {
			throw new DesafioJavaException("Produto não encontrado com ID: " + id);
		}
	}

	// Buscar Projeto por ID
	public Projeto getById(Long id) {
		Optional<Projeto> produto = projetoRepository.findById(id);
		return produto.orElseThrow(() -> new RuntimeException("Projeto não encontrado com ID: " + id));
	}

	 // Buscar Todos os Produtos
    public List<Projeto> getAll() {
        return projetoRepository.findAll(Sort.by(Sort.Order.asc("nome")));
    }

	public List<Projeto> buscarProjetosFiltrados(ProjetoFilter filter) {
		Specification<Projeto> specification = ProjetoSpecification.comFiltros(filter);
		return projetoRepository.findAll(specification);
	}
	
}
