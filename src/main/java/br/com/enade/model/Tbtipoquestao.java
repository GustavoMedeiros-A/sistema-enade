/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.enade.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author claud
 */
@Entity
@Table(name = "tbTipoQuestao")
public class Tbtipoquestao implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idTipoQuestao")
	private Long idTipoQuestao;
	@Basic(optional = false)
	@Size(min = 1, max = 45)
	@Column(name = "nomeTipoQuestaocol")
	private String nomeTipoQuestaocol;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tbTipoQuestaoidTipoQuestao")
	private Collection<Tbquestao> tbquestaoCollection;

	public Tbtipoquestao() {
	}

	public Tbtipoquestao(Long idTipoQuestao) {
		this.idTipoQuestao = idTipoQuestao;
	}

	public Tbtipoquestao(Long idTipoQuestao, String nomeTipoQuestaocol) {
		this.idTipoQuestao = idTipoQuestao;
		this.nomeTipoQuestaocol = nomeTipoQuestaocol;
	}

	public Long getIdTipoQuestao() {
		return idTipoQuestao;
	}

	public void setIdTipoQuestao(Long idTipoQuestao) {
		this.idTipoQuestao = idTipoQuestao;
	}

	public String getNomeTipoQuestaocol() {
		return nomeTipoQuestaocol;
	}

	public void setNomeTipoQuestaocol(String nomeTipoQuestaocol) {
		this.nomeTipoQuestaocol = nomeTipoQuestaocol;
	}

	@XmlTransient
	public Collection<Tbquestao> getTbquestaoCollection() {
		return tbquestaoCollection;
	}

	public void setTbquestaoCollection(Collection<Tbquestao> tbquestaoCollection) {
		this.tbquestaoCollection = tbquestaoCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTipoQuestao != null ? idTipoQuestao.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Tbtipoquestao)) {
			return false;
		}
		Tbtipoquestao other = (Tbtipoquestao) object;
		if ((this.idTipoQuestao == null && other.idTipoQuestao != null)
				|| (this.idTipoQuestao != null && !this.idTipoQuestao.equals(other.idTipoQuestao))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.enade.model.Tbtipoquestao[ idTipoQuestao=" + idTipoQuestao + " ]";
	}

}
