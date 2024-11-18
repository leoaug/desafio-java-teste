package br.com.desafiojava.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProjetoTest {

    @Test
    void testProjetoConstructorAndGetters() {
        Date dataInicio = new Date();
        Date dataFim = new Date();
        Date dataPrevisao = new Date();
        Pessoa gerente = new Pessoa(1L, "João", new Date(), "12345678900", true, true);

        Projeto projeto = new Projeto(1L, "Projeto A", dataInicio, dataFim, dataPrevisao, "Descrição do projeto", 2, 1, 50000.0, gerente);

        assertEquals(1L, projeto.getId());
        assertEquals("Projeto A", projeto.getNome());
        assertEquals(dataInicio, projeto.getDataInicio());
        assertEquals(dataFim, projeto.getDataFim());
        assertEquals(dataPrevisao, projeto.getDataPrevisao());
        assertEquals("Descrição do projeto", projeto.getDescricao());
        assertEquals(2, projeto.getRisco());
        assertEquals(1, projeto.getStatus());
        assertEquals(50000.0, projeto.getOrcamento());
        assertEquals(gerente, projeto.getGerente());
    }

    @Test
    void testProjetoSetters() {
        Projeto projeto = new Projeto();

        Date dataInicio = new Date();
        Date dataFim = new Date();
        Date dataPrevisao = new Date();
        Pessoa gerente = new Pessoa(2L, "Ana", new Date(), "98765432100", true, false);

        projeto.setId(2L);
        projeto.setNome("Projeto B");
        projeto.setDataInicio(dataInicio);
        projeto.setDataFim(dataFim);
        projeto.setDataPrevisao(dataPrevisao);
        projeto.setDescricao("Outro projeto");
        projeto.setRisco(3);
        projeto.setStatus(0);
        projeto.setOrcamento(75000.0);
        projeto.setGerente(gerente);

        assertEquals(2L, projeto.getId());
        assertEquals("Projeto B", projeto.getNome());
        assertEquals(dataInicio, projeto.getDataInicio());
        assertEquals(dataFim, projeto.getDataFim());
        assertEquals(dataPrevisao, projeto.getDataPrevisao());
        assertEquals("Outro projeto", projeto.getDescricao());
        assertEquals(3, projeto.getRisco());
        assertEquals(0, projeto.getStatus());
        assertEquals(75000.0, projeto.getOrcamento());
        assertEquals(gerente, projeto.getGerente());
    }

    @Test
    void testNoArgsConstructor() {
        Projeto projeto = new Projeto();

        assertNull(projeto.getId());
        assertNull(projeto.getNome());
        assertNull(projeto.getDataInicio());
        assertNull(projeto.getDataFim());
        assertNull(projeto.getDataPrevisao());
        assertNull(projeto.getDescricao());
        assertNull(projeto.getRisco());
        assertNull(projeto.getStatus());
        assertNull(projeto.getOrcamento());
        assertNull(projeto.getGerente());
    }

    
}
