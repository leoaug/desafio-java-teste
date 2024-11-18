package br.com.desafiojava.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembroTest {

    @Test
    void testMembroConstructorAndGetters() {
        Projeto projeto = new Projeto(1L, "Projeto A", null, null, null, null, null, null, null, null);
        Pessoa funcionario = new Pessoa(1L, "João", null, null, null, null);

        Membro membro = new Membro(1L, "Carlos", "Desenvolvedor", projeto, funcionario);

        assertEquals(1L, membro.getId());
        assertEquals("Carlos", membro.getNome());
        assertEquals("Desenvolvedor", membro.getAtribuicao());
        assertEquals(projeto, membro.getProjeto());
        assertEquals(funcionario, membro.getFuncionario());
    }

    @Test
    void testMembroSetters() {
        Membro membro = new Membro();

        Projeto projeto = new Projeto(1L, "Projeto A", null, null, null, null, null, null, null, null);
        Pessoa funcionario = new Pessoa(1L, "João", null, null, null, null);

        membro.setId(2L);
        membro.setNome("Ana");
        membro.setAtribuicao("Gerente");
        membro.setProjeto(projeto);
        membro.setFuncionario(funcionario);

        assertEquals(2L, membro.getId());
        assertEquals("Ana", membro.getNome());
        assertEquals("Gerente", membro.getAtribuicao());
        assertEquals(projeto, membro.getProjeto());
        assertEquals(funcionario, membro.getFuncionario());
    }

    @Test
    void testNoArgsConstructor() {
        Membro membro = new Membro();

        assertNull(membro.getId());
        assertNull(membro.getNome());
        assertNull(membro.getAtribuicao());
        assertNull(membro.getProjeto());
        assertNull(membro.getFuncionario());
    }

    
}
