package br.com.desafiojava.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.desafiojava.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private Date dataInicio;

	private Date dataFim;

	private Date dataPrevisao;

	private String descricao;

	private Integer risco;

	private Integer status;

	private Double orcamento;

	private Pessoa gerente;
}
