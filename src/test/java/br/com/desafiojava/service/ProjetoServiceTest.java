package br.com.desafiojava.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.desafiojava.jpa.repository.ProjetoRepository;
import br.com.desafiojava.model.Projeto;

class ProjetoServiceTest {

	@Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private ProjetoService projetoService;

    private Projeto projeto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        projeto = new Projeto();
        projeto.setId(1L);
        projeto.setNome("Projeto Teste");
    }

    @Test
    void testSave() {
        // Configurando o comportamento do mock
        when(projetoRepository.save(any(Projeto.class))).thenReturn(projeto);

        // Chamando o método do serviço
        Projeto savedProjeto = projetoService.save(projeto);

        // Verificando os resultados
        assertNotNull(savedProjeto);
        assertEquals("Projeto Teste", savedProjeto.getNome());
        verify(projetoRepository, times(1)).save(any(Projeto.class));
    }

    @Test
    void testDelete() {
        // Não precisa mockar o comportamento de delete, porque não estamos esperando retorno
        doNothing().when(projetoRepository).deleteById(1L);

        // Chamando o método de exclusão
        projetoService.delete(1L);

        // Verificando se o método de delete foi chamado corretamente
        verify(projetoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdate() {
        // Configurando o comportamento do mock
        when(projetoRepository.existsById(1L)).thenReturn(true);
        when(projetoRepository.save(any(Projeto.class))).thenReturn(projeto);

        // Chamando o método de atualização
        Projeto updatedProjeto = projetoService.update(1L, projeto);

        // Verificando os resultados
        assertNotNull(updatedProjeto);
        assertEquals("Projeto Teste", updatedProjeto.getNome());
        verify(projetoRepository, times(1)).save(any(Projeto.class));
    }

    @Test
    void testUpdateProjetoNaoExistente() {
        // Configurando o mock para o método existsById retornar false
        when(projetoRepository.existsById(1L)).thenReturn(false);

        // Chamando o método de atualização e verificando se a exceção é lançada
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            projetoService.update(1L, projeto);
        });

        // Verificando se a mensagem de erro é a esperada
        assertEquals("Produto não encontrado com ID: 1", thrown.getMessage());
    }

    @Test
    void testGetById() {
        // Configurando o comportamento do mock
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));

        // Chamando o método de busca
        Projeto foundProjeto = projetoService.getById(1L);

        // Verificando os resultados
        assertNotNull(foundProjeto);
        assertEquals(1L, foundProjeto.getId());
        assertEquals("Projeto Teste", foundProjeto.getNome());
    }

    @Test
    void testGetByIdProjetoNaoExistente() {
        // Configurando o mock para o método findById retornar um Optional vazio
        when(projetoRepository.findById(1L)).thenReturn(Optional.empty());

        // Chamando o método e verificando se a exceção é lançada
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            projetoService.getById(1L);
        });

        // Verificando a mensagem de erro
        assertEquals("Projeto não encontrado com ID: 1", thrown.getMessage());
    }

    @Test
    void testGetAll() {
        // Configurando o comportamento do mock para retornar uma lista de projetos
        List<Projeto> projetos = new ArrayList<>();
        projetos.add(projeto);
        when(projetoRepository.findAll()).thenReturn(projetos);

        // Chamando o método de busca de todos os projetos
        List<Projeto> allProjetos = projetoService.getAll();

        // Verificando os resultados
        assertNotNull(allProjetos);
        assertEquals(1, allProjetos.size());
        assertEquals("Projeto Teste", allProjetos.get(0).getNome());
    }

}
