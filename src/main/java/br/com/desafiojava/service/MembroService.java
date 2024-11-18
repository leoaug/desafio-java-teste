package br.com.desafiojava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.desafiojava.exception.DesafioJavaException;
import br.com.desafiojava.jpa.repository.MembroRepository;
import br.com.desafiojava.model.Membro;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MembroService {

	private MembroRepository membroRepository;
	
	 // Criar ou Atualizar Membro
    public Membro save(Membro membro) {
        return membroRepository.save(membro);
    }

    // Deletar Membro
    public void delete(Long id) {
    	membroRepository.deleteById(id);
    }

    // Atualizar Membro (usando merge)
    public Membro update(Long id, Membro membro) throws DesafioJavaException {
        if (membroRepository.existsById(id)) {
        	membro.setId(id);
            return membroRepository.save(membro);
        } else {
            throw new DesafioJavaException("Produto não encontrado com ID: " + id);
        }
    }

    // Buscar Membro por ID
    public Membro getById(Long id) {
        Optional<Membro> produto = membroRepository.findById(id);
        return produto.orElseThrow(() -> new RuntimeException("Membro não encontrado com ID: " + id));
    }

    // Buscar Todos os Membros
    public List<Membro> getAll() {
        return membroRepository.findAll(Sort.by(Sort.Order.asc("nome")));
    }
}
