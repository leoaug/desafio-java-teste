package br.com.desafiojava.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.desafiojava.service.PessoaService;



@Controller
public class IndexController {

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/")
	public String home(Model model, HttpServletRequest httpServletRequest) {
		
		pessoaService.getById(1L);
		
		return "index";
	}
}
