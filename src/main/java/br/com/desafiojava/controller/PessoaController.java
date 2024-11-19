package br.com.desafiojava.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.desafiojava.dto.PessoaDTO;
import br.com.desafiojava.exception.DesafioJavaException;
import br.com.desafiojava.model.Pessoa;
import br.com.desafiojava.service.PessoaService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/pessoa/")
@AllArgsConstructor
public class PessoaController {
	
	private final PessoaService pessoaService;
	

	@GetMapping("index")
	public String index(Model model) {		
        this.inicializarConsulta(model,pessoaService.getAll());
		return "pessoa/manterPessoa";
	}

	private void inicializarConsulta(Model model, List<Pessoa> pessoas) {
		model.addAttribute("pessoas", pessoas);		
	}

	@PostMapping("salvar")
	@ResponseBody
	public Pessoa salvar(@RequestBody PessoaDTO pessoaDTO) throws DesafioJavaException {
		Pessoa pessoa = new ModelMapper().map(pessoaDTO,Pessoa.class);
		return pessoa.getId() == null ? pessoaService.save(pessoa) : pessoaService.update(pessoa.getId(), pessoa);		
	}

	@DeleteMapping("excluir/{id}")
	@ResponseBody
	public void excluir(@PathVariable Long id) {
		pessoaService.delete(id);
	}

	@GetMapping("editar/{id}")
	@ResponseBody
	public Pessoa editar(@PathVariable Long id) {
		return pessoaService.getById(id);
	}
	
	
}
