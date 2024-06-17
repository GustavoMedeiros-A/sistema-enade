package br.com.enade.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.enade.dao.ProvaDao;
import br.com.enade.dao.QuestaoDao;
import br.com.enade.dao.ResultadoDao;
import br.com.enade.model.Tbprova;
import br.com.enade.model.Tbquestao;
import br.com.enade.tx.Transacional;

@Named
@ViewScoped
public class ProvaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Tbprova prova;

	@Inject
	private ProvaDao provadao;

	@Inject
	private QuestaoDao quedao;

	private Long provaId;

	private Long questaoId;

	@SuppressWarnings("unused")
	private List<Tbprova> provas;

	@Inject
	private QuestaoDao questaodao;

	private Long questao;

	@Inject
	private ResultadoDao resultadodao;

	private Long resultado;

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

	public QuestaoDao getQuestaodao() {
		return questaodao;
	}

	public void setQuestaodao(QuestaoDao questaodao) {
		this.questaodao = questaodao;
	}

	public Long getQuestao() {
		return questao;
	}

	public void setQuestao(Long questao) {
		this.questao = questao;
	}

	public ResultadoDao getResultadodao() {
		return resultadodao;
	}

	public void setResultadodao(ResultadoDao resultadodao) {
		this.resultadodao = resultadodao;
	}

	public Long getResultado() {
		return resultado;
	}

	public void setResultado(Long resultado) {
		this.resultado = resultado;
	}

	public List<Tbquestao> getQuestoes() {
		return this.quedao.listarTodos();
	}

	public List<Tbprova> getProvas() {
		return this.provadao.listaTodos();
	}

	public void gravarQuestao() {
		Tbquestao questao = this.quedao.buscarPorId(this.questaoId);
		this.prova.gravarQuestal(questao);
	}

	@Transacional
	public String grava() {
		if (this.prova.getIdProva() == null) {
			this.gravarQuestao();
			this.provadao.adicionar(this.prova);
			this.provas = this.provadao.listaTodos();
		} else {
			this.gravarQuestao();
			this.provadao.atualiza(this.prova);
			this.provas = this.provadao.listaTodos();
		}
		this.prova = new Tbprova();
		return "provas?faces-redirect=true";
	}

	@Transacional
	public void remover(Tbprova prova) {
		this.provadao.remove(prova);
		this.provas = this.provadao.listaTodos();
	}

	public Long getQuestaoId() {
		return questaoId;
	}

	public void setQuestaoId(Long questaoId) {
		this.questaoId = questaoId;
	}

	public void setProvas(List<Tbprova> provas) {
		this.provas = provas;
	}

}
