/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.enade.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author claud
 */
@Entity
@Table(name = "tbProva")
public class Tbprova implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProva")
    private Long idProva;
    @Basic(optional = false)
    @Column(name = "dataProva")
    @Temporal(TemporalType.DATE)
    private Calendar dataProva = Calendar.getInstance();
    @JoinTable(name = "tbprova_has_tbquestao", joinColumns = {
        @JoinColumn(name = "tbProva_idProva", referencedColumnName = "idProva")}, inverseJoinColumns = {
        @JoinColumn(name = "tbQuestao_idQuestao", referencedColumnName = "idQuestao")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Tbquestao> tbquestaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbProvaidProva")
    private Collection<Tbresultado> tbresultadoCollection;

    public Tbprova() {
    }

    public Tbprova(Long idProva) {
        this.idProva = idProva;
    }

    public void gravarQuestal(Tbquestao questao) {
        tbquestaoCollection.add(questao);
    }

    public Tbprova(Long idProva, Calendar dataProva) {
        this.idProva = idProva;
        this.dataProva = dataProva;
    }

    public Long getIdProva() {
        return idProva;
    }

    public void setIdProva(Long idProva) {
        this.idProva = idProva;
    }

    public Calendar getDataProva() {
        return dataProva;
    }

    public void setDataProva(Calendar dataProva) {
        this.dataProva = dataProva;
    }

    @XmlTransient
    public Collection<Tbquestao> getTbquestaoCollection() {
        return tbquestaoCollection;
    }

    public void setTbquestaoCollection(Collection<Tbquestao> tbquestaoCollection) {
        this.tbquestaoCollection = tbquestaoCollection;
    }

    @XmlTransient
    public Collection<Tbresultado> getTbresultadoCollection() {
        return tbresultadoCollection;
    }

    public void setTbresultadoCollection(Collection<Tbresultado> tbresultadoCollection) {
        this.tbresultadoCollection = tbresultadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProva != null ? idProva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbprova)) {
            return false;
        }
        Tbprova other = (Tbprova) object;
        if ((this.idProva == null && other.idProva != null)
                || (this.idProva != null && !this.idProva.equals(other.idProva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.enade.model.Tbprova[ idProva=" + idProva + " ]";
    }

}
