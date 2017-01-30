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
@Table(name = "sgc_escalada.nom_canton")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NomCanton.findAll", query = "SELECT n FROM NomCanton n"),
    @NamedQuery(name = "NomCanton.findByIdcanton", query = "SELECT n FROM NomCanton n WHERE n.idcanton = :idcanton"),
    @NamedQuery(name = "NomCanton.findByCanton", query = "SELECT n FROM NomCanton n WHERE n.canton = :canton")})
public class NomCanton implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcanton")
    private Integer idcanton;
    @Basic(optional = false)
    @Column(name = "canton")
    private String canton;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcanton")
    private Collection<NomCiudad> nomCiudadCollection;
    @JoinColumn(name = "idprovincia", referencedColumnName = "idprovincia")
    @ManyToOne(optional = false)
    private NomProvincia idprovincia;

    public NomCanton() {
    }

    public NomCanton(Integer idcanton) {
        this.idcanton = idcanton;
    }

    public NomCanton(Integer idcanton, String canton) {
        this.idcanton = idcanton;
        this.canton = canton;
    }

    public Integer getIdcanton() {
        return idcanton;
    }

    public void setIdcanton(Integer idcanton) {
        this.idcanton = idcanton;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    @XmlTransient
    public Collection<NomCiudad> getNomCiudadCollection() {
        return nomCiudadCollection;
    }

    public void setNomCiudadCollection(Collection<NomCiudad> nomCiudadCollection) {
        this.nomCiudadCollection = nomCiudadCollection;
    }

    public NomProvincia getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(NomProvincia idprovincia) {
        this.idprovincia = idprovincia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcanton != null ? idcanton.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomCanton)) {
            return false;
        }
        NomCanton other = (NomCanton) object;
        if ((this.idcanton == null && other.idcanton != null) || (this.idcanton != null && !this.idcanton.equals(other.idcanton))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.NomCanton[ idcanton=" + idcanton + " ]";
    }
    
}
