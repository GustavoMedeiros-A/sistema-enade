package br.com.enade.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.enade.model.Tbtipoquestao;

public class TipoQuestaoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	private DAO<Tbtipoquestao> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Tbtipoquestao>(this.em, Tbtipoquestao.class);
	}

	public List<Tbtipoquestao> listarTodos() {
		return this.dao.listaTodos();
	}

	public Tbtipoquestao buscarPorId(Long tipoQuestaoId) {
		return this.dao.buscaPorId(tipoQuestaoId);
	}

}
