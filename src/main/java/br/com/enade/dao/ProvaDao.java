package br.com.enade.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.enade.model.Tbprova;
import br.com.enade.model.Tbquestao;

public class ProvaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	private DAO<Tbprova> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Tbprova>(this.em, Tbprova.class);
	}

	public Tbprova buscorPorId(Long provaId) {
		return this.dao.buscaPorId(provaId);
	}

	public void adicionar(Tbprova prova) {
		this.dao.adiciona(prova);
	}

	public void atualiza(Tbprova prova) {
		this.dao.atualiza(prova);
	}

	public void remove(Tbprova prova) {
		this.dao.remove(prova);
	}

	public List<Tbprova> listaTodos() {
		return this.dao.listaTodos();
	}

	@SuppressWarnings("unchecked")
	public List<Object> listProvas(Tbprova prova, Tbquestao questao) {
		TypedQuery<Object> query = (TypedQuery<Object>) em.createQuery(
				"select pq from tbprova_has_tbquestao join tbProva p on  tbProva_idProva = p.idProva join tbQuestao q on tbQuestao_idQuestao = q.idQuestao ");

		return query.getResultList();
	}

}
