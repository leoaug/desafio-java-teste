package br.com.desafiojava.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.desafiojava.constants.DesafioJavaConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PESSOA", schema = DesafioJavaConstants.DESAFIO_JAVA_SCHEMA)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private Date dataNascimento;

	private String cpf;

	private Boolean funcionario;

	private Boolean gerente;

}
