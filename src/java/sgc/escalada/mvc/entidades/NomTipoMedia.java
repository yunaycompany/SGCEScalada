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
@Table(name = "sgc_escalada.nom_tipomedia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NomTipoMedia.findAll", query = "SELECT n FROM NomTipoMedia n"),
    @NamedQuery(name = "NomTipoMedia.findByIdtipomedia", query = "SELECT n FROM NomTipoMedia n WHERE n.idtipomedia = :idtipomedia"),
    @NamedQuery(name = "NomTipoMedia.findByTipomedia", query = "SELECT n FROM NomTipoMedia n WHERE n.tipomedia = :tipomedia")})
public class NomTipoMedia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipomedia")
    private Integer idtipomedia;
    @Column(name = "tipomedia")
    private String tipomedia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipomedia")
    private Collection<DatGaleria> datGaleriaCollection;

    public NomTipoMedia() {
    }

    public NomTipoMedia(Integer idtipomedia) {
        this.idtipomedia = idtipomedia;
    }

    public Integer getIdtipomedia() {
        return idtipomedia;
    }

    public void setIdtipomedia(Integer idtipomedia) {
        this.idtipomedia = idtipomedia;
    }

    public String getTipomedia() {
        return tipomedia;
    }

    public void setTipomedia(String tipomedia) {
        this.tipomedia = tipomedia;
    }

    @XmlTransient
    public Collection<DatGaleria> getDatGaleriaCollection() {
        return datGaleriaCollection;
    }

    public void setDatGaleriaCollection(Collection<DatGaleria> datGaleriaCollection) {
        this.datGaleriaCollection = datGaleriaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipomedia != null ? idtipomedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomTipoMedia)) {
            return false;
        }
        NomTipoMedia other = (NomTipoMedia) object;
        if ((this.idtipomedia == null && other.idtipomedia != null) || (this.idtipomedia != null && !this.idtipomedia.equals(other.idtipomedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.NomTipoMedia[ idtipomedia=" + idtipomedia + " ]";
    }
    
}
