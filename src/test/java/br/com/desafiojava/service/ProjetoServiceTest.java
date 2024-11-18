package br.com.desafiojava.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import br.com.desafiojava.exception.DesafioJavaException;
import br.com.desafiojava.jpa.especification.ProjetoSpecification;
import br.com.desafiojava.jpa.filter.ProjetoFilter;
import br.com.desafiojava.jpa.repository.ProjetoRepository;
import br.com.desafiojava.model.Projeto;

class ProjetoServiceTest {

	@Mock 
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private ProjetoService projetoService;
    
    @Mock
    private ProjetoFilter projetoFilter;

    @Mock
    private Specification<Projeto> specification;

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
    void testUpdate() throws DesafioJavaException {
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
        DesafioJavaException thrown = assertThrows(DesafioJavaException.class, () -> {
            projetoService.update(1L, projeto);
        });

        // Verificando se a mensagem de erro é a esperada
        assertEquals("Projeto não encontrado com ID: 1", thrown.getMessage());
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
    	   List<Projeto> projetos = Arrays.asList(
                   new Projeto(1L, "Projeto 1", null, null, null, null, null, null, null, null),
                   new Projeto(2L, "Projeto 2", null, null, null, null, null, null, null, null)
           );
           when(projetoRepository.findAll(Sort.by(Sort.Order.asc("nome")))).thenReturn(projetos);

           List<Projeto> result = projetoService.getAll();

           verify(projetoRepository, times(1)).findAll(Sort.by(Sort.Order.asc("nome")));
           assertEquals(projetos, result);

    }
    
    @SuppressWarnings("unchecked")
    @Test
    void testBuscarProjetosFiltrados() {
        // Dados simulados
        Projeto projeto1 = new Projeto(1L, "Projeto 1", null, null, null, null, null, null, null, null);
        Projeto projeto2 = new Projeto(2L, "Projeto 2", null, null, null, null, null, null, null, null);
        List<Projeto> listaProjetos = Arrays.asList(projeto1, projeto2);

        // Usando mockStatic para simular o comportamento estático
        try (MockedStatic<ProjetoSpecification> mockedStatic = mockStatic(ProjetoSpecification.class)) {
            
			Specification<Projeto> specificationMock = mock(Specification.class);
            mockedStatic.when(() -> ProjetoSpecification.comFiltros(projetoFilter)).thenReturn(specificationMock);

            // Quando o repositório for chamado com a Specification
            when(projetoRepository.findAll(specificationMock)).thenReturn(listaProjetos);

            // Chamada do método a ser testado
            List<Projeto> projetos = projetoService.buscarProjetosFiltrados(projetoFilter);

            // Verificar se o método findAll foi chamado uma vez com a Specification
            verify(projetoRepository, times(1)).findAll(specificationMock);

            // Verificar o retorno
            assertNotNull(projetos);
            assertEquals(2, projetos.size());
            assertEquals("Projeto 1", projetos.get(0).getNome());
            assertEquals("Projeto 2", projetos.get(1).getNome());
        }
    }

}
