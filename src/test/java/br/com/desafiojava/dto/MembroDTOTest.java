package br.com.desafiojava.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import br.com.desafiojava.model.Pessoa;
import br.com.desafiojava.model.Projeto;

class MembroDTOTest {

    @Test
    void testMembroDTO() {
        // Criação de dependências
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setNome("Projeto A");
        
        Pessoa funcionario = new Pessoa();
        funcionario.setId(1L);
        funcionario.setNome("João Silva");

        // Criação do DTO
        MembroDTO membroDTO = new MembroDTO();
        membroDTO.setId(1L);
        membroDTO.setNome("Carlos");
        membroDTO.setAtribuicao("Desenvolvedor");
        membroDTO.setProjeto(projeto);
        membroDTO.setFuncionario(funcionario);

        // Verificações
        assertNotNull(membroDTO);
        assertEquals(1L, membroDTO.getId());
        assertEquals("Carlos", membroDTO.getNome());
        assertEquals("Desenvolvedor", membroDTO.getAtribuicao());
        assertEquals(projeto, membroDTO.getProjeto());
        assertEquals(funcionario, membroDTO.getFuncionario());

        // Testando o construtor com argumentos
        MembroDTO membroDTO2 = new MembroDTO(2L, "Maria", "Tester", projeto, funcionario);

        assertNotNull(membroDTO2);
        assertEquals(2L, membroDTO2.getId());
        assertEquals("Maria", membroDTO2.getNome());
        assertEquals("Tester", membroDTO2.getAtribuicao());
        assertEquals(projeto, membroDTO2.getProjeto());
        assertEquals(funcionario, membroDTO2.getFuncionario());

        // Testando o método equals
        MembroDTO membroDTO3 = new MembroDTO(1L, "Carlos", "Desenvolvedor", projeto, funcionario);
        assertEquals(membroDTO, membroDTO3); // Deve ser igual ao membroDTO original
    }
}
