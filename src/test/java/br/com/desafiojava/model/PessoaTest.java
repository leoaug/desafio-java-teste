
package br.com.desafiojava.model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    @Test
    void testPessoaConstructorAndGetters() {
        Date dataNascimento = new Date();
        Pessoa pessoa = new Pessoa(1L, "João", dataNascimento, "12345678900", true, false);

        assertEquals(1L, pessoa.getId());
        assertEquals("João", pessoa.getNome());
        assertEquals(dataNascimento, pessoa.getDataNascimento());
        assertEquals("12345678900", pessoa.getCpf());
        assertTrue(pessoa.getFuncionario());
        assertFalse(pessoa.getGerente());
    }

    @Test
    void testPessoaSetters() {
        Pessoa pessoa = new Pessoa();

        Date dataNascimento = new Date();
        pessoa.setId(2L);
        pessoa.setNome("Ana");
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setCpf("98765432100");
        pessoa.setFuncionario(false);
        pessoa.setGerente(true);

        assertEquals(2L, pessoa.getId());
        assertEquals("Ana", pessoa.getNome());
        assertEquals(dataNascimento, pessoa.getDataNascimento());
        assertEquals("98765432100", pessoa.getCpf());
        assertFalse(pessoa.getFuncionario());
        assertTrue(pessoa.getGerente());
    }

    @Test
    void testNoArgsConstructor() {
        Pessoa pessoa = new Pessoa();

        assertNull(pessoa.getId());
        assertNull(pessoa.getNome());
        assertNull(pessoa.getDataNascimento());
        assertNull(pessoa.getCpf());
        assertNull(pessoa.getFuncionario());
        assertNull(pessoa.getGerente());
    }

   
}
