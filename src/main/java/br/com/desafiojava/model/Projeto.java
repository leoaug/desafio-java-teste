package br.com.desafiojava.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.desafiojava.constants.DesafioJavaConstants;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PROJETO", schema = DesafioJavaConstants.DESAFIO_JAVA_SCHEMA)
@Getter
@Setter
public class Projeto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private Date dataInicio;

	private Date dataFim;

	private Date dataPrevisao;

	private String descricao;

	private Integer risco;

	private Integer status;

	private Double orcamento;

	@ManyToOne
	@JoinColumn(name = "idgerente", referencedColumnName = "id")
	private Pessoa gerente;

}
