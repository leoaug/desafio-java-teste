package br.com.desafiojava.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.desafiojava.constants.DesafioJavaConstants;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PESSOA", schema = DesafioJavaConstants.DESAFIO_JAVA_SCHEMA)
@Getter
@Setter
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_API_GERAL", nullable = false)
	private Long id;

	private String nome;

	private Date dataNascimento;

	private String cpf;

	private Boolean funcionario;

	private Boolean gerente;

}
