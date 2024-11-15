package br.com.desafiojava.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.desafiojava.constants.DesafioJavaConstants;
import br.com.desafiojava.constants.RiscoProjetoEnum;
import br.com.desafiojava.constants.StatusProjetoEnum;
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

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "risco", length = 1)
	private RiscoProjetoEnum riscoProjetoEnum;
	
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status", length = 1)
	private StatusProjetoEnum statusProjetoEnum;
	

	private Double orcamento;

	@ManyToOne
	@JoinColumn(name = "idgerente", referencedColumnName = "id")
	private Pessoa gerente;

}
