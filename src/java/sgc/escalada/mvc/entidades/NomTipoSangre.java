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
@Table(name = "sgc_escalada.nom_tiposangre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NomTipoSangre.findAll", query = "SELECT n FROM NomTipoSangre n"),
    @NamedQuery(name = "NomTipoSangre.findByIdtiposangre", query = "SELECT n FROM NomTipoSangre n WHERE n.idtiposangre = :idtiposangre"),
    @NamedQuery(name = "NomTipoSangre.findByTiposangre", query = "SELECT n FROM NomTipoSangre n WHERE n.tiposangre = :tiposangre")})
public class NomTipoSangre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtiposangre")
    private Integer idtiposangre;
    @Basic(optional = false)
    @Column(name = "tiposangre")
    private String tiposangre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtiposangre")
    private Collection<DatDeportista> datDeportistaCollection;

    public NomTipoSangre() {
    }

    public NomTipoSangre(Integer idtiposangre) {
        this.idtiposangre = idtiposangre;
    }

    public NomTipoSangre(Integer idtiposangre, String tiposangre) {
        this.idtiposangre = idtiposangre;
        this.tiposangre = tiposangre;
    }

    public Integer getIdtiposangre() {
        return idtiposangre;
    }

    public void setIdtiposangre(Integer idtiposangre) {
        this.idtiposangre = idtiposangre;
    }

    public String getTiposangre() {
        return tiposangre;
    }

    public void setTiposangre(String tiposangre) {
        this.tiposangre = tiposangre;
    }

    @XmlTransient
    public Collection<DatDeportista> getDatDeportistaCollection() {
        return datDeportistaCollection;
    }

    public void setDatDeportistaCollection(Collection<DatDeportista> datDeportistaCollection) {
        this.datDeportistaCollection = datDeportistaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtiposangre != null ? idtiposangre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomTipoSangre)) {
            return false;
        }
        NomTipoSangre other = (NomTipoSangre) object;
        if ((this.idtiposangre == null && other.idtiposangre != null) || (this.idtiposangre != null && !this.idtiposangre.equals(other.idtiposangre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.NomTipoSangre[ idtiposangre=" + idtiposangre + " ]";
    }
    
}
