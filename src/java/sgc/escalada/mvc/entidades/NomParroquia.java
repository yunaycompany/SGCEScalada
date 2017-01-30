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
 * @author Yosbel
 */
@Entity
@Table(name = "sgc_escalada.nom_parroquia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NomParroquia.findAll", query = "SELECT n FROM NomParroquia n"),
    @NamedQuery(name = "NomParroquia.findByIdparroquia", query = "SELECT n FROM NomParroquia n WHERE n.idparroquia = :idparroquia"),
    @NamedQuery(name = "NomParroquia.findByParroquia", query = "SELECT n FROM NomParroquia n WHERE n.parroquia = :parroquia")})
public class NomParroquia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idparroquia")
    private Integer idparroquia;
    @Basic(optional = false)
    @Column(name = "parroquia")
    private String parroquia;
    @JoinColumn(name = "idciudad", referencedColumnName = "idciudad")
    @ManyToOne(optional = false)
    private NomCiudad idciudad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idparroquia")
    private Collection<NomLugar> nomLugarCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nomParroquia")
    private Collection<DatDireccion> datDireccionCollection;

    public NomParroquia() {
    }

    public NomParroquia(Integer idparroquia) {
        this.idparroquia = idparroquia;
    }

    public NomParroquia(Integer idparroquia, String parroquia) {
        this.idparroquia = idparroquia;
        this.parroquia = parroquia;
    }

    public Integer getIdparroquia() {
        return idparroquia;
    }

    public void setIdparroquia(Integer idparroquia) {
        this.idparroquia = idparroquia;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    public NomCiudad getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(NomCiudad idciudad) {
        this.idciudad = idciudad;
    }

    @XmlTransient
    public Collection<NomLugar> getNomLugarCollection() {
        return nomLugarCollection;
    }

    public void setNomLugarCollection(Collection<NomLugar> nomLugarCollection) {
        this.nomLugarCollection = nomLugarCollection;
    }

    @XmlTransient
    public Collection<DatDireccion> getDatDireccionCollection() {
        return datDireccionCollection;
    }

    public void setDatDireccionCollection(Collection<DatDireccion> datDireccionCollection) {
        this.datDireccionCollection = datDireccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparroquia != null ? idparroquia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomParroquia)) {
            return false;
        }
        NomParroquia other = (NomParroquia) object;
        if ((this.idparroquia == null && other.idparroquia != null) || (this.idparroquia != null && !this.idparroquia.equals(other.idparroquia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.NomParroquia[ idparroquia=" + idparroquia + " ]";
    }
    
}
