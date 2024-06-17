package br.com.enade.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.enade.model.Tbtipousuario;

public class TipoUsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	private DAO<Tbtipousuario> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Tbtipousuario>(this.em, Tbtipousuario.class);
	}

	public Tbtipousuario buscarPorId(Long tipoUsuarioId) {
		return this.dao.buscaPorId(tipoUsuarioId);
	}

	public List<Tbtipousuario> listaTodos() {
		return this.dao.listaTodos();
	}

}
