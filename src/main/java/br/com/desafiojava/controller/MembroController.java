package br.com.desafiojava.controller;

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
import br.com.desafiojava.model.Membro;
import br.com.desafiojava.service.MembroService;
import br.com.desafiojava.service.PessoaService;
import br.com.desafiojava.service.ProjetoService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/membro/")
@AllArgsConstructor
public class MembroController {

	
	private final MembroService membroService;
	
	
	private final PessoaService pessoaService;
	
	
	private final ProjetoService projetoService;

	@GetMapping("index")
	public String index(Model model) {
		model.addAttribute("membros", membroService.getAll());
		model.addAttribute("projetos", projetoService.getAll());		
        model.addAttribute("funcionarios", pessoaService.findByFuncionario(Boolean.TRUE));
		return "membro/manterMembro";
	}

	@PostMapping("salvar")
	@ResponseBody
	public Membro salvar(@RequestBody Membro membro) throws DesafioJavaException {
		membro.setFuncionario(pessoaService.getById(membro.getFuncionario().getId()));
		membro.setProjeto(projetoService.getById(membro.getProjeto().getId()));
		return membro.getId() == null ? membroService.save(membro) : membroService.update(membro.getId(), membro);		
	}

	@DeleteMapping("excluir/{id}")
	@ResponseBody
	public void excluir(@PathVariable Long id) {
		membroService.delete(id);
	}

	@GetMapping("editar/{id}")
	@ResponseBody
	public Membro editar(@PathVariable Long id) {
		return membroService.getById(id);
	}
}
