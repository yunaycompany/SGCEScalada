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
@Table(name = "sgc_escalada.nom_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NomRol.findAll", query = "SELECT n FROM NomRol n"),
    @NamedQuery(name = "NomRol.findByIdrol", query = "SELECT n FROM NomRol n WHERE n.idrol = :idrol"),
    @NamedQuery(name = "NomRol.findByRol", query = "SELECT n FROM NomRol n WHERE n.rol = :rol")})
public class NomRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrol")
    private Integer idrol;
    @Basic(optional = false)
    @Column(name = "rol")
    private String rol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idrol")
    private Collection<DatUsuario> datUsuarioCollection;

    public NomRol() {
    }

    public NomRol(Integer idrol) {
        this.idrol = idrol;
    }

    public NomRol(Integer idrol, String rol) {
        this.idrol = idrol;
        this.rol = rol;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @XmlTransient
    public Collection<DatUsuario> getDatUsuarioCollection() {
        return datUsuarioCollection;
    }

    public void setDatUsuarioCollection(Collection<DatUsuario> datUsuarioCollection) {
        this.datUsuarioCollection = datUsuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrol != null ? idrol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomRol)) {
            return false;
        }
        NomRol other = (NomRol) object;
        if ((this.idrol == null && other.idrol != null) || (this.idrol != null && !this.idrol.equals(other.idrol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.NomRol[ idrol=" + idrol + " ]";
    }
    
}
