package br.com.desafiojava.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

import br.com.desafiojava.model.Pessoa;

class ProjetoDTOTest {

    @Test
    void testProjetoDTO() {
        // Criação de dependências
        Date dataInicio = new Date();
        Date dataFim = new Date();
        Date dataPrevisao = new Date();
        Pessoa gerente = new Pessoa(1L, "Gerente Nome", new Date(), "123.456.789-00", true, true);

        // Criação do DTO com setters
        ProjetoDTO projetoDTO = new ProjetoDTO();
        projetoDTO.setId(1L);
        projetoDTO.setNome("Projeto Exemplo");
        projetoDTO.setDataInicio(dataInicio);
        projetoDTO.setDataFim(dataFim);
        projetoDTO.setDataPrevisao(dataPrevisao);
        projetoDTO.setDescricao("Descrição do projeto");
        projetoDTO.setRisco(2);
        projetoDTO.setStatus(1);
        projetoDTO.setOrcamento(50000.0);
        projetoDTO.setGerente(gerente);

        // Verificações
        assertNotNull(projetoDTO);
        assertEquals(1L, projetoDTO.getId());
        assertEquals("Projeto Exemplo", projetoDTO.getNome());
        assertEquals(dataInicio, projetoDTO.getDataInicio());
        assertEquals(dataFim, projetoDTO.getDataFim());
        assertEquals(dataPrevisao, projetoDTO.getDataPrevisao());
        assertEquals("Descrição do projeto", projetoDTO.getDescricao());
        assertEquals(2, projetoDTO.getRisco());
        assertEquals(1, projetoDTO.getStatus());
        assertEquals(50000.0, projetoDTO.getOrcamento());
        assertEquals(gerente, projetoDTO.getGerente());

        // Criação do DTO com construtor completo
        ProjetoDTO projetoDTO2 = new ProjetoDTO(2L, "Projeto Exemplo 2", dataInicio, dataFim, dataPrevisao,
                "Nova descrição", 3, 2, 100000.0, gerente);

        // Verificações
        assertNotNull(projetoDTO2);
        assertEquals(2L, projetoDTO2.getId());
        assertEquals("Projeto Exemplo 2", projetoDTO2.getNome());
        assertEquals(dataInicio, projetoDTO2.getDataInicio());
        assertEquals(dataFim, projetoDTO2.getDataFim());
        assertEquals(dataPrevisao, projetoDTO2.getDataPrevisao());
        assertEquals("Nova descrição", projetoDTO2.getDescricao());
        assertEquals(3, projetoDTO2.getRisco());
        assertEquals(2, projetoDTO2.getStatus());
        assertEquals(100000.0, projetoDTO2.getOrcamento());
        assertEquals(gerente, projetoDTO2.getGerente());

        // Testando o método equals
        ProjetoDTO projetoDTO3 = new ProjetoDTO(1L, "Projeto Exemplo", dataInicio, dataFim, dataPrevisao,
                "Descrição do projeto", 2, 1, 50000.0, gerente);

        assertEquals(projetoDTO, projetoDTO3); // Deve ser igual ao primeiro DTO
    }
}
