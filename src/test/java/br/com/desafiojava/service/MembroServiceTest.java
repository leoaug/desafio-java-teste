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
import br.com.desafiojava.jpa.repository.MembroRepository;
import br.com.desafiojava.model.Membro;

class MembroServiceTest {


    @Mock
    private MembroRepository membroRepository;

    @InjectMocks
    private MembroService membroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Membro membro = new Membro(1L, "Carlos", null, null, null);
        when(membroRepository.save(membro)).thenReturn(membro);

        Membro result = membroService.save(membro);

        verify(membroRepository, times(1)).save(membro);
        assertEquals(membro, result);
    }

    @Test
    void testDelete() {
        Long membroId = 1L;
        doNothing().when(membroRepository).deleteById(membroId);

        membroService.delete(membroId);

        verify(membroRepository, times(1)).deleteById(membroId);
    }

    @Test
    void testUpdate_Success() throws DesafioJavaException {
        Long membroId = 1L;
        Membro membro = new Membro(null, "Carlos Atualizado", null, null, null);

        when(membroRepository.existsById(membroId)).thenReturn(true);
        when(membroRepository.save(membro)).thenReturn(membro);

        Membro result = membroService.update(membroId, membro);

        verify(membroRepository, times(1)).existsById(membroId);
        verify(membroRepository, times(1)).save(membro);
        assertEquals(membroId, membro.getId());
        assertEquals(membro, result);
    }

    @Test
    void testUpdate_NotFound() {
        Long membroId = 1L;
        Membro membro = new Membro(null, "Carlos Atualizado", null, null, null);

        when(membroRepository.existsById(membroId)).thenReturn(false);

        DesafioJavaException exception = assertThrows(DesafioJavaException.class, () -> membroService.update(membroId, membro));

        assertEquals("Membro não encontrado com ID: " + membroId, exception.getMessage());
        verify(membroRepository, times(1)).existsById(membroId);
        verify(membroRepository, never()).save(any());
    }

    @Test
    void testGetById_Success() {
        Long membroId = 1L;
        Membro membro = new Membro(membroId, "Carlos", null, null, null);
        when(membroRepository.findById(membroId)).thenReturn(Optional.of(membro));

        Membro result = membroService.getById(membroId);

        verify(membroRepository, times(1)).findById(membroId);
        assertEquals(membro, result);
    }

    @Test
    void testGetById_NotFound() {
        Long membroId = 1L;
        when(membroRepository.findById(membroId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> membroService.getById(membroId));

        assertEquals("Membro não encontrado com ID: " + membroId, exception.getMessage());
        verify(membroRepository, times(1)).findById(membroId);
    }

    @Test
    void testGetAll() {
        List<Membro> membros = Arrays.asList(
                new Membro(1L, "Carlos", null, null, null),
                new Membro(2L, "Ana", null, null, null)
        );
        when(membroRepository.findAll(Sort.by(Sort.Order.asc("nome")))).thenReturn(membros);

        List<Membro> result = membroService.getAll();

        verify(membroRepository, times(1)).findAll(Sort.by(Sort.Order.asc("nome")));
        assertEquals(membros, result);
    }

}
