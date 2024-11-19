package br.com.desafiojava.controller;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.desafiojava.dto.ProjetoDTO;
import br.com.desafiojava.exception.DesafioJavaException;
import br.com.desafiojava.jpa.filter.ProjetoFilter;
import br.com.desafiojava.model.Pessoa;
import br.com.desafiojava.model.Projeto;
import br.com.desafiojava.service.PessoaService;
import br.com.desafiojava.service.ProjetoService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/projeto/")
@AllArgsConstructor
public class ProjetoController {

	private final ProjetoService projetoService;
	
	private final PessoaService pessoaService;
	

	@GetMapping("index")
	public String index(Model model) {		
        this.inicializarConsulta(model,projetoService.getAll(),pessoaService.findByGerente(Boolean.TRUE));
		return "projeto/manterProjeto";
	}

	private void inicializarConsulta(Model model, List<Projeto> projetos, List<Pessoa> pessoas) {
		model.addAttribute("projetos", projetos);		
        model.addAttribute("gerentes", pessoas);		
	}

	@PostMapping("salvar")
	@ResponseBody
	public Projeto salvar(@RequestBody ProjetoDTO projetoDTO) throws DesafioJavaException {
		Projeto projeto = new ModelMapper().map(projetoDTO,Projeto.class);
		projeto.setGerente(pessoaService.getById(projeto.getGerente().getId()));
		return projeto.getId() == null ? projetoService.save(projeto) : projetoService.update(projeto.getId(), projeto);		
	}

	@DeleteMapping("excluir/{id}")
	@ResponseBody
	public void excluir(@PathVariable Long id) {
		projetoService.delete(id);
	}

	@GetMapping("editar/{id}")
	@ResponseBody
	public Projeto editar(@PathVariable Long id) {
		return projetoService.getById(id);
	}
	
	@GetMapping("consultar")
	public String consultar(@RequestParam(required = false)  String nome,
							@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")  Date dataInicio,
							@RequestParam(required = false)  Integer risco,
							@RequestParam(required = false)  Integer status,Model model) {

		ProjetoFilter filter = new ProjetoFilter ();
		filter.setNome(nome);
		filter.setDataInicio(dataInicio);
		filter.setRisco(risco);
		filter.setStatus(status);

		this.inicializarConsulta(model,projetoService.buscarProjetosFiltrados(filter),pessoaService.findByGerente(Boolean.TRUE));

		return "projeto/manterProjeto";

	}
}
