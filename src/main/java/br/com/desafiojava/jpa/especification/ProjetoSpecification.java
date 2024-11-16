package br.com.desafiojava.jpa.especification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.desafiojava.jpa.filter.ProjetoFilter;
import br.com.desafiojava.model.Projeto;

public class ProjetoSpecification {

	private ProjetoSpecification() {}
	
	public static Specification<Projeto> comFiltros(ProjetoFilter filter) {
		return (root, query, criteriaBuilder) -> {
			var predicates = new java.util.ArrayList<>();

			if (filter.getNome() != null && !filter.getNome().isEmpty()) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),
						"%" + filter.getNome().toLowerCase() + "%"));
			}

			if (filter.getDataInicio() != null) {
				predicates.add(criteriaBuilder.equal(root.get("dataInicio"), filter.getDataInicio()));
			}

			if (filter.getRisco() != null) {
				predicates.add(criteriaBuilder.equal(root.get("risco"), filter.getRisco()));
			}

			if (filter.getStatus() != null) {
				predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
