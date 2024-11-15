package br.com.desafiojava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.desafiojava.model.Projeto;
import br.com.desafiojava.service.ProjetoService;

@Controller
@RequestMapping("/projeto/")
public class ProjetoController {

	@Autowired
	private ProjetoService projetoService;

	@GetMapping("index")
	public String index(Model model) {
		List<Projeto> projetos = projetoService.getAll();
		model.addAttribute("projetos", projetos);
		return "projeto/manterProjeto";
	}

	@PostMapping("salvar")
	@ResponseBody
	public Projeto salvar(@RequestBody Projeto projeto) {
		return projetoService.save(projeto);
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
}
