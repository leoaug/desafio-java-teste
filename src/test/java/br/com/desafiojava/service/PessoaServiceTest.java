package br.com.desafiojava.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
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
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import br.com.desafiojava.exception.DesafioJavaException;
import br.com.desafiojava.jpa.repository.PessoaRepository;
import br.com.desafiojava.model.Pessoa;

class PessoaServiceTest {

	@Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Pessoa pessoa = new Pessoa(1L, "João", null, null, null, null);
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        Pessoa result = pessoaService.save(pessoa);

        verify(pessoaRepository, times(1)).save(pessoa);
        assertEquals(pessoa, result);
    }

    @Test
    void testDelete() {
        Long pessoaId = 1L;
        doNothing().when(pessoaRepository).deleteById(pessoaId);

        pessoaService.delete(pessoaId);

        verify(pessoaRepository, times(1)).deleteById(pessoaId);
    }

    @Test
    void testUpdate_Success() throws DesafioJavaException {
        Long pessoaId = 1L;
        Pessoa pessoa = new Pessoa(null, "João Atualizado", null, null, null, null);

        when(pessoaRepository.existsById(pessoaId)).thenReturn(true);
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        Pessoa result = pessoaService.update(pessoaId, pessoa);

        verify(pessoaRepository, times(1)).existsById(pessoaId);
        verify(pessoaRepository, times(1)).save(pessoa);
        assertEquals(pessoaId, pessoa.getId());
        assertEquals(pessoa, result);
    }

    @Test
    void testUpdate_NotFound() {
        Long pessoaId = 1L;
        Pessoa pessoa = new Pessoa(null, "João Atualizado", null, null, null, null);

        when(pessoaRepository.existsById(pessoaId)).thenReturn(false);

        DesafioJavaException exception = assertThrows(DesafioJavaException.class, () -> pessoaService.update(pessoaId, pessoa));

        assertEquals("Produto não encontrado com ID: " + pessoaId, exception.getMessage());
        verify(pessoaRepository, times(1)).existsById(pessoaId);
        verify(pessoaRepository, never()).save(any());
    }

    @Test
    void testGetById_Success() {
        Long pessoaId = 1L;
        Pessoa pessoa = new Pessoa(pessoaId, "João", null, null, null, null);
        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.of(pessoa));

        Pessoa result = pessoaService.getById(pessoaId);

        verify(pessoaRepository, times(1)).findById(pessoaId);
        assertEquals(pessoa, result);
    }

    @Test
    void testGetById_NotFound() {
        Long pessoaId = 1L;
        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> pessoaService.getById(pessoaId));

        assertEquals("Pessoa não encontrado com ID: " + pessoaId, exception.getMessage());
        verify(pessoaRepository, times(1)).findById(pessoaId);
    }

    @Test
    void testGetAll() {
        List<Pessoa> pessoas = Arrays.asList(new Pessoa(1L, "João", null, null, null, null), new Pessoa(2L, "Maria", null, null, null, null));
        when(pessoaRepository.findAll(Sort.by(Sort.Order.asc("nome")))).thenReturn(pessoas);

        List<Pessoa> result = pessoaService.getAll();

        verify(pessoaRepository, times(1)).findAll(Sort.by(Sort.Order.asc("nome")));
        assertEquals(pessoas, result);
    }

    @Test
    void testFindByGerente() {
        Boolean gerente = true;
        List<Pessoa> pessoas = Arrays.asList(new Pessoa(1L, "João", null, null, gerente, gerente), new Pessoa(2L, "Maria", null, null, gerente, gerente));
        when(pessoaRepository.findByGerente(gerente)).thenReturn(pessoas);

        List<Pessoa> result = pessoaService.findByGerente(gerente);

        verify(pessoaRepository, times(1)).findByGerente(gerente);
        assertEquals(pessoas, result);
    }

    @Test
    void testFindByFuncionario() {
        Boolean funcionario = true;
        List<Pessoa> pessoas = Arrays.asList(new Pessoa(1L, "João", null, null, funcionario, funcionario), new Pessoa(2L, "Maria", null, null, funcionario, funcionario));
        when(pessoaRepository.findByFuncionario(funcionario)).thenReturn(pessoas);

        List<Pessoa> result = pessoaService.findByFuncionario(funcionario);

        verify(pessoaRepository, times(1)).findByFuncionario(funcionario);
        assertEquals(pessoas, result);
    }

}
