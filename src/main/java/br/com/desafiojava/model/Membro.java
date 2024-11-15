package br.com.desafiojava.model;

import java.io.Serializable;

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
@Table(name = "MEMBRO", schema = DesafioJavaConstants.DESAFIO_JAVA_SCHEMA)
@Getter
@Setter
public class Membro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	
	private String atribuicao;
	
	@ManyToOne
	@JoinColumn(name = "idprojeto", referencedColumnName = "id")
	private Projeto projeto;

}