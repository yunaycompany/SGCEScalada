/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sgc.escalada.mvc.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ariam
 */
@Entity
@Table(name = "sgc_escalada.nom_provincia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NomProvincia.findAll", query = "SELECT n FROM NomProvincia n"),
    @NamedQuery(name = "NomProvincia.findByIdprovincia", query = "SELECT n FROM NomProvincia n WHERE n.idprovincia = :idprovincia"),
    @NamedQuery(name = "NomProvincia.findByProvincia", query = "SELECT n FROM NomProvincia n WHERE n.provincia = :provincia")})
public class NomProvincia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprovincia")
    private Integer idprovincia;
    @Column(name = "provincia")
    private String provincia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprovincia",orphanRemoval = true)
    private Collection<DatAllround> datAllroundCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprovincia",orphanRemoval = true)
    private Collection<NomFederacionProvincial> nomFederacionProvincialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprovincia",orphanRemoval = true)
    private Collection<NomCanton> nomCantonCollection;

    public NomProvincia() {
    }

    public NomProvincia(Integer idprovincia) {
        this.idprovincia = idprovincia;
    }

    public Integer getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(Integer idprovincia) {
        this.idprovincia = idprovincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @XmlTransient
    public Collection<DatAllround> getDatAllroundCollection() {
        return datAllroundCollection;
    }

    public void setDatAllroundCollection(Collection<DatAllround> datAllroundCollection) {
        this.datAllroundCollection = datAllroundCollection;
    }

    @XmlTransient
    public Collection<NomFederacionProvincial> getNomFederacionProvincialCollection() {
        return nomFederacionProvincialCollection;
    }

    public void setNomFederacionProvincialCollection(Collection<NomFederacionProvincial> nomFederacionProvincialCollection) {
        this.nomFederacionProvincialCollection = nomFederacionProvincialCollection;
    }

    @XmlTransient
    public Collection<NomCanton> getNomCantonCollection() {
        return nomCantonCollection;
    }

    public void setNomCantonCollection(Collection<NomCanton> nomCantonCollection) {
        this.nomCantonCollection = nomCantonCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprovincia != null ? idprovincia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomProvincia)) {
            return false;
        }
        NomProvincia other = (NomProvincia) object;
        if ((this.idprovincia == null && other.idprovincia != null) || (this.idprovincia != null && !this.idprovincia.equals(other.idprovincia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.NomProvincia[ idprovincia=" + idprovincia + " ]";
    }
    
}
