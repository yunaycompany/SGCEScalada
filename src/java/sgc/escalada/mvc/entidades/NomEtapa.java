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
 * @author Yosbel
 */
@Entity
@Table(name = "sgc_escalada.nom_etapa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NomEtapa.findAll", query = "SELECT n FROM NomEtapa n"),
    @NamedQuery(name = "NomEtapa.findByIdetapa", query = "SELECT n FROM NomEtapa n WHERE n.idetapa = :idetapa"),
    @NamedQuery(name = "NomEtapa.findByEtapa", query = "SELECT n FROM NomEtapa n WHERE n.etapa = :etapa")})
public class NomEtapa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idetapa")
    private Integer idetapa;
    @Basic(optional = false)
    @Column(name = "etapa")
    private String etapa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nomEtapa")
    private Collection<DatFaseBloque> datFaseBloqueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nomEtapa")
    private Collection<DatFaseVelocidad> datFaseVelocidadCollection;

    public NomEtapa() {
    }

    public NomEtapa(Integer idetapa) {
        this.idetapa = idetapa;
    }

    public NomEtapa(Integer idetapa, String etapa) {
        this.idetapa = idetapa;
        this.etapa = etapa;
    }

    public Integer getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(Integer idetapa) {
        this.idetapa = idetapa;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    @XmlTransient
    public Collection<DatFaseBloque> getDatFaseBloqueCollection() {
        return datFaseBloqueCollection;
    }

    public void setDatFaseBloqueCollection(Collection<DatFaseBloque> datFaseBloqueCollection) {
        this.datFaseBloqueCollection = datFaseBloqueCollection;
    }

    @XmlTransient
    public Collection<DatFaseVelocidad> getDatFaseVelocidadCollection() {
        return datFaseVelocidadCollection;
    }

    public void setDatFaseVelocidadCollection(Collection<DatFaseVelocidad> datFaseVelocidadCollection) {
        this.datFaseVelocidadCollection = datFaseVelocidadCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idetapa != null ? idetapa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomEtapa)) {
            return false;
        }
        NomEtapa other = (NomEtapa) object;
        if ((this.idetapa == null && other.idetapa != null) || (this.idetapa != null && !this.idetapa.equals(other.idetapa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.NomEtapa[ idetapa=" + idetapa + " ]";
    }
    
}
