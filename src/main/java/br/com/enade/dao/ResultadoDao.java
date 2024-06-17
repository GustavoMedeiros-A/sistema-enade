package br.com.enade.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.enade.model.Tbresultado;

public class ResultadoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	private DAO<Tbresultado> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Tbresultado>(this.em, Tbresultado.class);
	}

	public Tbresultado buscarPorId(Long result) {
		return this.dao.buscaPorId(result);
	}

	public void adiciona(Tbresultado result) {
		this.dao.adiciona(result);
	}

	public void remove(Tbresultado result) {
		this.dao.remove(result);
	}

	public List<Tbresultado> listarTodos() {
		return this.dao.listaTodos();
	}

	public void atualiza(Tbresultado result) {
		this.dao.atualiza(result);
	}

}
