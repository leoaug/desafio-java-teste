package br.com.desafiojava.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String nome;

	private Date dataNascimento;

	private String cpf;

	private Boolean funcionario;

	private Boolean gerente;

}
