package br.com.desafiojava.dto;

import java.io.Serializable;

import br.com.desafiojava.model.Pessoa;
import br.com.desafiojava.model.Projeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;
	
	private String atribuicao;

	private Projeto projeto;
	
	private Pessoa funcionario;
}
