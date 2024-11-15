package br.com.desafiojava.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.desafiojava.model.Pessoa;
import br.com.desafiojava.model.Projeto;
import br.com.desafiojava.service.PessoaService;
import br.com.desafiojava.service.ProjetoService;

class ProjetoControllerTest {

	@Mock
	private ProjetoService projetoService;

	@Mock
	private PessoaService pessoaService;

	@InjectMocks
	private ProjetoController projetoController;

	@Autowired
	private MockMvc mockMvc;

	private Projeto projeto;
	
	private Pessoa gerente;

	@BeforeEach
	public void setUp() {
		
		MockitoAnnotations.openMocks(this);  

		
		// Configuração do MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(projetoController).build();

		// Criando um projeto de exemplo para os testes
		projeto = new Projeto();
		projeto.setId(1L);
		projeto.setNome("Projeto Teste");
		// Defina outros campos conforme necessário
		
		gerente = new Pessoa();
		gerente.setId(1L);
		gerente.setNome("Fulano");
		
	    projeto.setGerente(gerente);
	}

	@Test
	void testIndex() throws Exception {
		when(projetoService.getAll()).thenReturn(List.of(projeto));
		when(pessoaService.findByGerente(Boolean.TRUE)).thenReturn(List.of());

		mockMvc.perform(get("/projeto/index")).andExpect(status().isOk())
				.andExpect(view().name("projeto/manterProjeto")).andExpect(model().attributeExists("projetos"))
				.andExpect(model().attributeExists("gerentes"));

		verify(projetoService, times(1)).getAll();
		verify(pessoaService, times(1)).findByGerente(Boolean.TRUE);
	}

	@Test
	void testSalvarNovoProjeto() throws Exception {
		
		 // Mockando o comportamento do serviço de projeto para retornar o projeto com gerente
	    when(projetoService.save(any(Projeto.class))).thenReturn(projeto);
	    when(pessoaService.getById(anyLong())).thenReturn(gerente);
	    

	    // Enviando uma requisição POST para salvar o projeto
	    mockMvc.perform(post("/projeto/salvar")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content("{\"nome\":\"Projeto Teste\",\"gerente\":{\"id\":1}}"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.nome").value("Projeto Teste"))
	            .andExpect(jsonPath("$.gerente.id").value(1));

	    // Verificando se o serviço de salvar foi chamado com o projeto correto
	    verify(projetoService, times(1)).save(any(Projeto.class));
	    verify(pessoaService, times(1)).getById(anyLong());
	}

	@Test
	void testSalvarProjetoExistente() throws Exception {
		
		projeto.setNome("Projeto Existente");
		
		// Mockando o comportamento do serviço de projeto para retornar o projeto existente
	    when(projetoService.getById(1L)).thenReturn(projeto);
	    when(projetoService.update(eq(1L), any(Projeto.class))).thenReturn(projeto); // Mockando a atualização

	    // Mockando o serviço de pessoa (gerente)
	    when(pessoaService.getById(1L)).thenReturn(gerente);

	    // Enviando uma requisição POST para salvar o projeto
	    mockMvc.perform(post("/projeto/salvar")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content("{\"id\":1,\"nome\":\"Projeto Existente\",\"gerente\":{\"id\":1}}"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.nome").value("Projeto Existente"))
	            .andExpect(jsonPath("$.gerente.id").value(1)); // Verificando se o gerente foi corretamente associado ao projeto

	    // Verificando se os métodos do serviço foram chamados corretamente
	    verify(projetoService, times(1)).update(eq(1L), any(Projeto.class)); // Garantindo que o método de atualização foi chamado
	    verify(pessoaService, times(1)).getById(eq(1L)); // Garantindo que o método getById do gerente foi chamado
	}

	@Test
	void testExcluirProjeto() throws Exception {
		doNothing().when(projetoService).delete(1L);

		mockMvc.perform(delete("/projeto/excluir/1")).andExpect(status().isOk());

		verify(projetoService, times(1)).delete(1L);
	}

	@Test
	public void testEditarProjeto() throws Exception {
		when(projetoService.getById(1L)).thenReturn(projeto);

		mockMvc.perform(get("/projeto/editar/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value("Projeto Teste"));

		verify(projetoService, times(1)).getById(1L);
	}

}
