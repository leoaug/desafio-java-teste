package br.com.desafiojava.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import br.com.desafiojava.exception.DesafioJavaException;
import br.com.desafiojava.model.Pessoa;
import br.com.desafiojava.service.PessoaService;

class PessoaControllerTest {

	@Mock
    private PessoaService pessoaService;

    @Mock
    private Model model;

    @InjectMocks
    private PessoaController pessoaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIndex() {
        // Mocking
        List<Pessoa> pessoas = Arrays.asList(
                new Pessoa(1L, "João", new Date(), "12345678901", null, null),
                new Pessoa(2L, "Maria", new Date(), "98765432100", null, null)
        );
        when(pessoaService.getAll()).thenReturn(pessoas);

        // Call method
        String viewName = pessoaController.index(model);

        // Verifications
        verify(model, times(1)).addAttribute("pessoas", pessoas);
        assertEquals("pessoa/manterPessoa", viewName);
    }

    @Test
    void testSalvarNovaPessoa() throws DesafioJavaException {
        // Mocking
        Pessoa novaPessoa = new Pessoa(null, "Carlos", new Date(), "12312312300", null, null);
        Pessoa pessoaSalva = new Pessoa(1L, "Carlos", new Date(), "12312312300", null, null);
        when(pessoaService.save(novaPessoa)).thenReturn(pessoaSalva);

        // Call method
        Pessoa result = pessoaController.salvar(novaPessoa);

        // Verifications
        verify(pessoaService, times(1)).save(novaPessoa);
        assertEquals(pessoaSalva, result);
    }

    @Test
    void testSalvarPessoaExistente() throws DesafioJavaException {
        // Mocking
        Pessoa pessoaExistente = new Pessoa(1L, "Carlos", new Date(), "12312312300", null, null);
        when(pessoaService.update(pessoaExistente.getId(), pessoaExistente)).thenReturn(pessoaExistente);

        // Call method
        Pessoa result = pessoaController.salvar(pessoaExistente);

        // Verifications
        verify(pessoaService, times(1)).update(pessoaExistente.getId(), pessoaExistente);
        assertEquals(pessoaExistente, result);
    }

    @Test
    void testExcluirPessoa() {
        // Mocking
        Long pessoaId = 1L;
        doNothing().when(pessoaService).delete(pessoaId);

        // Call method
        pessoaController.excluir(pessoaId);

        // Verifications
        verify(pessoaService, times(1)).delete(pessoaId);
    }

    @Test
    void testEditarPessoa() {
        // Mocking
        Long pessoaId = 1L;
        Pessoa pessoa = new Pessoa(pessoaId, "João", new Date(), "12345678901", null, null);
        when(pessoaService.getById(pessoaId)).thenReturn(pessoa);

        // Call method
        Pessoa result = pessoaController.editar(pessoaId);

        // Verifications
        verify(pessoaService, times(1)).getById(pessoaId);
        assertEquals(pessoa, result);
    }

}
