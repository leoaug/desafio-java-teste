package br.com.desafiojava.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

import br.com.desafiojava.dto.MembroDTO;
import br.com.desafiojava.exception.DesafioJavaException;
import br.com.desafiojava.model.Membro;
import br.com.desafiojava.model.Pessoa;
import br.com.desafiojava.model.Projeto;
import br.com.desafiojava.service.MembroService;
import br.com.desafiojava.service.PessoaService;
import br.com.desafiojava.service.ProjetoService;

class MembroControllerTest {

	 @Mock
		private MembroService membroService;

	    @Mock
	    private PessoaService pessoaService;

	    @Mock
	    private ProjetoService projetoService;

	    @Mock
	    private Model model;

	    @InjectMocks
	    private MembroController membroController;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testIndex() {
	        // Mocking
	        List<Membro> membros = Arrays.asList(new Membro(1L, null, null, null, null), new Membro(2L, null, null, null, null));
	        List<Projeto> projetos = Arrays.asList(new Projeto(1L, "Projeto A", null, null, null, null, null, null, null, null), new Projeto(2L, "Projeto B", null, null, null, null, null, null, null, null));
	        List<Pessoa> funcionarios = Arrays.asList(new Pessoa(1L, "João", null, null, null, null), new Pessoa(2L, "Maria", null, null, null, null));

	        when(membroService.getAll()).thenReturn(membros);
	        when(projetoService.getAll()).thenReturn(projetos);
	        when(pessoaService.findByFuncionario(true)).thenReturn(funcionarios);

	        // Call method
	        String viewName = membroController.index(model);

	        // Verifications
	        verify(model, times(1)).addAttribute("membros", membros);
	        verify(model, times(1)).addAttribute("projetos", projetos);
	        verify(model, times(1)).addAttribute("funcionarios", funcionarios);
	        assertEquals("membro/manterMembro", viewName);
	    }

	    @Test
	    void testSalvarNovoMembro() throws DesafioJavaException {
	        // Mocking
	        Pessoa funcionario = new Pessoa(1L, "João", null, null, null, null);
	        Projeto projeto = new Projeto(1L, "Projeto A", null, null, null, null, null, null, null, funcionario);
	        Membro novoMembro = new Membro(null, null, null, projeto, funcionario);
	        novoMembro.setFuncionario(funcionario);
	        novoMembro.setProjeto(projeto);

	        Membro membroSalvo = new Membro(1L, null, null, projeto, funcionario);
	        membroSalvo.setFuncionario(funcionario);
	        membroSalvo.setProjeto(projeto);

	        when(pessoaService.getById(funcionario.getId())).thenReturn(funcionario);
	        when(projetoService.getById(projeto.getId())).thenReturn(projeto);
	        when(membroService.save(novoMembro)).thenReturn(membroSalvo);

	        // Call method
	        Membro result = membroController.salvar(new ModelMapper().map(novoMembro, MembroDTO.class));

	        // Verifications
	        verify(pessoaService, times(1)).getById(funcionario.getId());
	        verify(projetoService, times(1)).getById(projeto.getId());
	        verify(membroService, times(1)).save(novoMembro);
	        assertEquals(membroSalvo, result);
	    }

	    @Test
	    void testSalvarMembroExistente() throws DesafioJavaException {
	        // Mocking
	        Pessoa funcionario = new Pessoa(1L, "João", null, null, null, null);
	        Projeto projeto = new Projeto(1L, "Projeto A", null, null, null, null, null, null, null, funcionario);
	        Membro membroExistente = new Membro(1L, null, null, projeto, funcionario);
	        membroExistente.setFuncionario(funcionario);
	        membroExistente.setProjeto(projeto);

	        when(pessoaService.getById(funcionario.getId())).thenReturn(funcionario);
	        when(projetoService.getById(projeto.getId())).thenReturn(projeto);
	        when(membroService.update(membroExistente.getId(), membroExistente)).thenReturn(membroExistente);

	        // Call method
	        Membro result = membroController.salvar(new ModelMapper().map(membroExistente, MembroDTO.class));

	        // Verifications
	        verify(pessoaService, times(1)).getById(funcionario.getId());
	        verify(projetoService, times(1)).getById(projeto.getId());
	        verify(membroService, times(1)).update(membroExistente.getId(), membroExistente);
	        assertEquals(membroExistente, result);
	    }

	    @Test
	    void testExcluirMembro() {
	        // Mocking
	        Long membroId = 1L;
	        doNothing().when(membroService).delete(membroId);

	        // Call method
	        membroController.excluir(membroId);

	        // Verifications
	        verify(membroService, times(1)).delete(membroId);
	    }

	    @Test
	    void testEditarMembro() {
	        // Mocking
	        Long membroId = 1L;
	        Membro membro = new Membro(membroId, null, null, null, null);
	        when(membroService.getById(membroId)).thenReturn(membro);

	        // Call method
	        Membro result = membroController.editar(membroId);

	        // Verifications
	        verify(membroService, times(1)).getById(membroId);
	        assertEquals(membro, result);
	    }

}
