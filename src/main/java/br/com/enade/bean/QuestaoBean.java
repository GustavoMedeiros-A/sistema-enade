package br.com.enade.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.enade.dao.ProvaDao;
import br.com.enade.dao.QuestaoDao;
import br.com.enade.dao.TipoQuestaoDao;
import br.com.enade.model.Tbprova;
import br.com.enade.model.Tbquestao;
import br.com.enade.model.Tbtipoquestao;
import br.com.enade.tx.Transacional;

@Named
@ViewScoped
public class QuestaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Tbquestao questao;

	@Inject
	private Tbprova prova;

	@Inject
	private LoginBean login;

	@Inject
	private ProvaDao provadao;

	@Inject
	private QuestaoDao questaoDao;

	@Inject
	private TipoQuestaoDao tipoQuestaoDao;

	@SuppressWarnings("unused")
	private List<Tbquestao> questoes;

	private Long provaId;

	private Long questaoId;

	private Long tipoQuestaoId;

	public void gravarProva() {
		Tbprova prova = this.provadao.buscorPorId(this.provaId);
		this.questao.gravarProva(prova);
	}

	public void gravarTipoQuesta() {
		Tbtipoquestao tbtipoquestao = this.tipoQuestaoDao.buscarPorId(this.tipoQuestaoId);
		this.questao.setTbTipoQuestaoidTipoQuestao(tbtipoquestao);
	}

	@Transacional
	public void gravar() {
		System.out.println("Gravando usuario " + this.questao.getIdQuestao());

		if (this.questao.getIdQuestao() == null
				&& login.getUsuarioLogado().getTbTipoUsuarioidTipoUsuario().getIdTipoUsuario() != 2) {
			this.questaoDao.adiciona(this.questao);
		} else {
			this.gravarProva();
			this.questaoDao.atualiza(this.questao);
		}

		this.gravarTipoQuesta();
		this.questoes = this.questaoDao.listarTodos();
		this.questao = new Tbquestao();

	}

	@Transacional
	public void gravarResposta() {
		System.out.println("Gravando usuario " + this.questao.getIdQuestao());

		if (this.questao.getIdQuestao() != null
				&& login.getUsuarioLogado().getTbTipoUsuarioidTipoUsuario().getIdTipoUsuario() == 2L) {
			this.questao.setTbTipoQuestaoidTipoQuestao(this.questao.getTbTipoQuestaoidTipoQuestao());
			this.questaoDao.adiciona(this.questao);
		}

		this.questoes = this.questaoDao.listarTodos();
		this.questao = new Tbquestao();
	}

	@Transacional
	public void remover(Tbquestao questao) {
		System.out.println("Removendo questa " + questao.getIdQuestao());

		this.questaoDao.remove(questao);
	}

	public Tbquestao getQuestao() {
		return questao;
	}

	public void setQuestao(Tbquestao questao) {
		this.questao = questao;
	}

	public List<Tbtipoquestao> getTipoQuestoes() {
		return this.tipoQuestaoDao.listarTodos();
	}

	public Tbtipoquestao getTipoQuestaoDeQuestao() {
		return this.questao.getTbTipoQuestaoidTipoQuestao();
	}

	public List<Tbquestao> getQuestoes() {
		return this.questaoDao.listarTodos();
	}

	public Long getQuestaoId() {
		return questaoId;
	}

	public void setQuestaoId(Long questaoId) {
		this.questaoId = questaoId;
	}

	public Long getTipoQuestaoId() {
		return tipoQuestaoId;
	}

	public void setTipoQuestaoId(Long tipoQuestaoId) {
		this.tipoQuestaoId = tipoQuestaoId;
	}

	public void novo() {
		this.questao = new Tbquestao();

	}

	public Tbprova getProva() {
		return prova;
	}

	public void setProva(Tbprova prova) {
		this.prova = prova;
	}

	public Long getProvaId() {
		return provaId;
	}

	public void setProvaId(Long provaId) {
		this.provaId = provaId;
	}

	public List<Tbprova> getProvas() {
		return this.provadao.listaTodos();
	}

}
