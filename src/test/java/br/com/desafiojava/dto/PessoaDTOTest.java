package br.com.desafiojava.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

class PessoaDTOTest {

    @Test
    void testPessoaDTO() {
        // Criação de dependências
        Date dataNascimento = new Date();

        // Criação do DTO com setters
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(1L);
        pessoaDTO.setNome("João Silva");
        pessoaDTO.setDataNascimento(dataNascimento);
        pessoaDTO.setCpf("123.456.789-00");
        pessoaDTO.setFuncionario(true);
        pessoaDTO.setGerente(false);

        // Verificações
        assertNotNull(pessoaDTO);
        assertEquals(1L, pessoaDTO.getId());
        assertEquals("João Silva", pessoaDTO.getNome());
        assertEquals(dataNascimento, pessoaDTO.getDataNascimento());
        assertEquals("123.456.789-00", pessoaDTO.getCpf());
        assertEquals(true, pessoaDTO.getFuncionario());
        assertEquals(false, pessoaDTO.getGerente());

        // Criação do DTO com construtor completo
        PessoaDTO pessoaDTO2 = new PessoaDTO(2L, "Maria Oliveira", dataNascimento, "987.654.321-00", false, true);

        // Verificações
        assertNotNull(pessoaDTO2);
        assertEquals(2L, pessoaDTO2.getId());
        assertEquals("Maria Oliveira", pessoaDTO2.getNome());
        assertEquals(dataNascimento, pessoaDTO2.getDataNascimento());
        assertEquals("987.654.321-00", pessoaDTO2.getCpf());
        assertEquals(false, pessoaDTO2.getFuncionario());
        assertEquals(true, pessoaDTO2.getGerente());

        // Testando o método equals
        PessoaDTO pessoaDTO3 = new PessoaDTO(1L, "João Silva", dataNascimento, "123.456.789-00", true, false);
        assertEquals(pessoaDTO, pessoaDTO3); // Deve ser igual ao primeiro DTO
    }
}
