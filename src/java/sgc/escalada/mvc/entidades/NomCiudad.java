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

/**
 *
 * @author Yosbel
 */
@Entity
@Table(name = "sgc_escalada.nom_ciudad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NomCiudad.findAll", query = "SELECT n FROM NomCiudad n"),
    @NamedQuery(name = "NomCiudad.findByIdciudad", query = "SELECT n FROM NomCiudad n WHERE n.idciudad = :idciudad"),
    @NamedQuery(name = "NomCiudad.findByCiudad", query = "SELECT n FROM NomCiudad n WHERE n.ciudad = :ciudad")})
public class NomCiudad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idciudad")
    private Integer idciudad;
    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;
    @JoinColumn(name = "idcanton", referencedColumnName = "idcanton")
    @ManyToOne(optional = false)
    private NomCanton idcanton;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idciudad")
    private Collection<NomParroquia> nomParroquiaCollection;
  

    public NomCiudad() {
    }

    public NomCiudad(Integer idciudad) {
        this.idciudad = idciudad;
    }

    public NomCiudad(Integer idciudad, String ciudad) {
        this.idciudad = idciudad;
        this.ciudad = ciudad;
    }

    public Integer getIdciudad() {
        return idciudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Collection<NomParroquia> getNomParroquiaCollection() {
        return nomParroquiaCollection;
    }

    public void setNomParroquiaCollection(Collection<NomParroquia> nomParroquiaCollection) {
        this.nomParroquiaCollection = nomParroquiaCollection;
    }

    
    public NomCanton getIdcanton() {
        return idcanton;
    }

    public void setIdcanton(NomCanton idcanton) {
        this.idcanton = idcanton;
    }

   


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idciudad != null ? idciudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomCiudad)) {
            return false;
        }
        NomCiudad other = (NomCiudad) object;
        if ((this.idciudad == null && other.idciudad != null) || (this.idciudad != null && !this.idciudad.equals(other.idciudad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.NomCiudad[ idciudad=" + idciudad + " ]";
    }
    
}
