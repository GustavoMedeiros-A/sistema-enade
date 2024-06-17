/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.enade.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author claud
 */
@Entity
@Table(name = "tbquestao")

public class Tbquestao implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idQuestao")
	private Long idQuestao;
	@Basic(optional = false)
	@Size(min = 1, max = 45)
	@Column(name = "descricaoQuestao")
	private String descricaoQuestao;
	@Size(max = 45)
	@Column(name = "alternativaA")
	private String alternativaA;
	@Size(max = 45)
	@Column(name = "alternativaB")
	private String alternativaB;
	@Size(max = 45)
	@Column(name = "alternativaC")
	private String alternativaC;
	@Size(max = 45)
	@Column(name = "alternativaD")
	private String alternativaD;
	@Size(max = 45)
	@Column(name = "alternativaE")
	private String alternativaE;
	@Column(name = "questaoCorreta")
	private Character questaoCorreta;
	@Basic(optional = false)
	@Column(name = "resposta")
	private String reposta;
	@Basic(optional = false)
	@Column(name = "estadoQuestao")
	private String estadoQuestao;
	@ManyToMany(mappedBy = "tbquestaoCollection", fetch = FetchType.LAZY)
	private Collection<Tbprova> tbprovaCollection;
	@JoinColumn(name = "tbTipoQuestao_idTipoQuestao", referencedColumnName = "idTipoQuestao")
	@ManyToOne(optional = false)
	private Tbtipoquestao tbTipoQuestaoidTipoQuestao;

	public Tbquestao() {
	}

	public Tbquestao(Long idQuestao) {
		this.idQuestao = idQuestao;
	}

	public Tbquestao(Long idQuestao, String descricaoQuestao, String estadoQuestao) {
		this.idQuestao = idQuestao;
		this.descricaoQuestao = descricaoQuestao;
		this.estadoQuestao = estadoQuestao;
	}

	public void gravarProva(Tbprova provaId) {
		tbprovaCollection.add(provaId);
	}

	public Long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Long idQuestao) {
		this.idQuestao = idQuestao;
	}

	public String getDescricaoQuestao() {
		return descricaoQuestao;
	}

	public void setDescricaoQuestao(String descricaoQuestao) {
		this.descricaoQuestao = descricaoQuestao;
	}

	public String getAlternativaA() {
		return alternativaA;
	}

	public void setAlternativaA(String alternativaA) {
		this.alternativaA = alternativaA;
	}

	public String getAlternativaB() {
		return alternativaB;
	}

	public void setAlternativaB(String alternativaB) {
		this.alternativaB = alternativaB;
	}

	public String getAlternativaC() {
		return alternativaC;
	}

	public void setAlternativaC(String alternativaC) {
		this.alternativaC = alternativaC;
	}

	public String getAlternativaD() {
		return alternativaD;
	}

	public void setAlternativaD(String alternativaD) {
		this.alternativaD = alternativaD;
	}

	public String getAlternativaE() {
		return alternativaE;
	}

	public void setAlternativaE(String alternativaE) {
		this.alternativaE = alternativaE;
	}

	public Character getQuestaoCorreta() {
		return questaoCorreta;
	}

	public void setQuestaoCorreta(Character questaoCorreta) {
		this.questaoCorreta = questaoCorreta;
	}

	public String getEstadoQuestao() {
		return estadoQuestao;
	}

	public void setEstadoQuestao(String estadoQuestao) {
		this.estadoQuestao = estadoQuestao;
	}

	@XmlTransient
	public Collection<Tbprova> getTbprovaCollection() {
		return tbprovaCollection;
	}

	public void setTbprovaCollection(Collection<Tbprova> tbprovaCollection) {
		this.tbprovaCollection = tbprovaCollection;
	}

	public Tbtipoquestao getTbTipoQuestaoidTipoQuestao() {
		return tbTipoQuestaoidTipoQuestao;
	}

	public void setTbTipoQuestaoidTipoQuestao(Tbtipoquestao tbTipoQuestaoidTipoQuestao) {
		this.tbTipoQuestaoidTipoQuestao = tbTipoQuestaoidTipoQuestao;
	}

	public String getReposta() {
		return reposta;
	}

	public void setReposta(String reposta) {
		this.reposta = reposta;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idQuestao != null ? idQuestao.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Tbquestao)) {
			return false;
		}
		Tbquestao other = (Tbquestao) object;
		if ((this.idQuestao == null && other.idQuestao != null)
				|| (this.idQuestao != null && !this.idQuestao.equals(other.idQuestao))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.enade.model.Tbquestao[ idQuestao=" + idQuestao + " ]";
	}

}
