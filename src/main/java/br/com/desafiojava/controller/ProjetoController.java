package br.com.desafiojava.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.desafiojava.exception.DesafioJavaException;
import br.com.desafiojava.jpa.filter.ProjetoFilter;
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
		model.addAttribute("projetos", projetoService.getAll());		
        model.addAttribute("gerentes", pessoaService.findByGerente(Boolean.TRUE));
		return "projeto/manterProjeto";
	}

	@PostMapping("salvar")
	@ResponseBody
	public Projeto salvar(@RequestBody Projeto projeto) throws DesafioJavaException {
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
	
	@PostMapping("/consultar")
	public List<Projeto> consultar(@RequestBody Projeto projeto) {

		ProjetoFilter filter = new ProjetoFilter ();
		filter.setNome(projeto.getNome());
		filter.setDataInicio(projeto.getDataInicio());
		filter.setRisco(projeto.getRisco());
		filter.setStatus(projeto.getStatus());

		return projetoService.buscarProjetosFiltrados(filter);
	}
}
