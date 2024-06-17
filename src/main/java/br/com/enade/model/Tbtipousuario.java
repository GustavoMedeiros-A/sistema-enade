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
@Table(name = "tbTipoUsuario")

public class Tbtipousuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "idTipoUsuario")
	private Long idTipoUsuario;
	@Basic(optional = false)
	@Size(min = 1, max = 9)
	@Column(name = "nomeTipoUsuario")
	private String nomeTipoUsuario;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tbTipoUsuarioidTipoUsuario")
	private Collection<Tbusuario> tbusuarioCollection;

	public Tbtipousuario() {
	}

	public Tbtipousuario(Long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public Tbtipousuario(Long idTipoUsuario, String nomeTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
		this.nomeTipoUsuario = nomeTipoUsuario;
	}

	public Long getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(Long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getNomeTipoUsuario() {
		return nomeTipoUsuario;
	}

	public void setNomeTipoUsuario(String nomeTipoUsuario) {
		this.nomeTipoUsuario = nomeTipoUsuario;
	}

	@XmlTransient
	public Collection<Tbusuario> getTbusuarioCollection() {
		return tbusuarioCollection;
	}

	public void setTbusuarioCollection(Collection<Tbusuario> tbusuarioCollection) {
		this.tbusuarioCollection = tbusuarioCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTipoUsuario != null ? idTipoUsuario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Tbtipousuario)) {
			return false;
		}
		Tbtipousuario other = (Tbtipousuario) object;
		if ((this.idTipoUsuario == null && other.idTipoUsuario != null)
				|| (this.idTipoUsuario != null && !this.idTipoUsuario.equals(other.idTipoUsuario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.enade.model.Tbtipousuario[ idTipoUsuario=" + idTipoUsuario + " ]";
	}

}
