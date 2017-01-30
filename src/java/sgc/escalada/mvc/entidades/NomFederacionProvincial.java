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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sgc_escalada.nom_federacionprovincial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NomFederacionProvincial.findAll", query = "SELECT n FROM NomFederacionProvincial n"),
    @NamedQuery(name = "NomFederacionProvincial.findByIdfederacion", query = "SELECT n FROM NomFederacionProvincial n WHERE n.idfederacion = :idfederacion"),
    @NamedQuery(name = "NomFederacionProvincial.findByFederacion", query = "SELECT n FROM NomFederacionProvincial n WHERE n.federacion = :federacion")})
public class NomFederacionProvincial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfederacion")
    private Integer idfederacion;
    @Basic(optional = false)
    @Column(name = "federacion")
    private String federacion;
    @JoinColumn(name = "idprovincia", referencedColumnName = "idprovincia")
    @ManyToOne(optional = false)
    private NomProvincia idprovincia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nomFederacionProvincial")
    private Collection<DatDeportistaFederacion> datDeportistaFederacionCollection;

    public NomFederacionProvincial() {
    }

    public NomFederacionProvincial(Integer idfederacion) {
        this.idfederacion = idfederacion;
    }

    public NomFederacionProvincial(Integer idfederacion, String federacion) {
        this.idfederacion = idfederacion;
        this.federacion = federacion;
    }

    public Integer getIdfederacion() {
        return idfederacion;
    }

    public void setIdfederacion(Integer idfederacion) {
        this.idfederacion = idfederacion;
    }

    public String getFederacion() {
        return federacion;
    }

    public void setFederacion(String federacion) {
        this.federacion = federacion;
    }

    public NomProvincia getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(NomProvincia idprovincia) {
        this.idprovincia = idprovincia;
    }

    @XmlTransient
    public Collection<DatDeportistaFederacion> getDatDeportistaFederacionCollection() {
        return datDeportistaFederacionCollection;
    }

    public void setDatDeportistaFederacionCollection(Collection<DatDeportistaFederacion> datDeportistaFederacionCollection) {
        this.datDeportistaFederacionCollection = datDeportistaFederacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfederacion != null ? idfederacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomFederacionProvincial)) {
            return false;
        }
        NomFederacionProvincial other = (NomFederacionProvincial) object;
        if ((this.idfederacion == null && other.idfederacion != null) || (this.idfederacion != null && !this.idfederacion.equals(other.idfederacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.NomFederacionProvincial[ idfederacion=" + idfederacion + " ]";
    }
    
}
