package br.com.desafiojava.jpa.filter;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ProjetoFilter implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nome;
	private Date dataInicio;
	private Integer risco;
	private Integer status;
}
