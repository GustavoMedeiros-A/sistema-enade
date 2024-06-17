package br.com.enade.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.enade.model.Tbquestao;

public class QuestaoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	private DAO<Tbquestao> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Tbquestao>(this.em, Tbquestao.class);
	}

	public Tbquestao buscarPorId(Long questao) {
		return this.dao.buscaPorId(questao);
	}

	public void adiciona(Tbquestao questao) {
		this.dao.adiciona(questao);
	}

	public void remove(Tbquestao questao) {
		this.dao.remove(questao);
	}

	public List<Tbquestao> listarTodos() {
		return this.dao.listaTodos();
	}
	
	 public void atualiza(Tbquestao questao) {
			this.dao.atualiza(questao);
		}

}
