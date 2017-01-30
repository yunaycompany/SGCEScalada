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
@Table(name = "sgc_escalada.nom_sexo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NomSexo.findAll", query = "SELECT n FROM NomSexo n"),
    @NamedQuery(name = "NomSexo.findByIdsexo", query = "SELECT n FROM NomSexo n WHERE n.idsexo = :idsexo"),
    @NamedQuery(name = "NomSexo.findBySexo", query = "SELECT n FROM NomSexo n WHERE n.sexo = :sexo")})
public class NomSexo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsexo")
    private Integer idsexo;
    @Basic(optional = false)
    @Column(name = "sexo")
    private String sexo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsexo")
    private Collection<DatDeportista> datDeportistaCollection;

    public NomSexo() {
    }

    public NomSexo(Integer idsexo) {
        this.idsexo = idsexo;
    }

    public NomSexo(Integer idsexo, String sexo) {
        this.idsexo = idsexo;
        this.sexo = sexo;
    }

    public Integer getIdsexo() {
        return idsexo;
    }

    public void setIdsexo(Integer idsexo) {
        this.idsexo = idsexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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
        hash += (idsexo != null ? idsexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomSexo)) {
            return false;
        }
        NomSexo other = (NomSexo) object;
        if ((this.idsexo == null && other.idsexo != null) || (this.idsexo != null && !this.idsexo.equals(other.idsexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.NomSexo[ idsexo=" + idsexo + " ]";
    }
    
}
