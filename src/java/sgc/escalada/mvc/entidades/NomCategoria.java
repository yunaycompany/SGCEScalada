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
@Table(name = "sgc_escalada.nom_categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NomCategoria.findAll", query = "SELECT n FROM NomCategoria n"),
    @NamedQuery(name = "NomCategoria.findByIdcategoria", query = "SELECT n FROM NomCategoria n WHERE n.idcategoria = :idcategoria"),
    @NamedQuery(name = "NomCategoria.findByCategoria", query = "SELECT n FROM NomCategoria n WHERE n.categoria = :categoria")})
public class NomCategoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcategoria")
    private Integer idcategoria;
    @Basic(optional = false)
    @Column(name = "categoria")
    private String categoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nomCategoria")
    private Collection<DatFaseBloque> datFaseBloqueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nomCategoria")
    private Collection<DatFaseDificultad> datFaseDificultadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcategoria")
    private Collection<DatAllround> datAllroundCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcategoria")
    private Collection<HisResultado> hisResultadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nomCategoria")
    private Collection<DatFaseVelocidad> datFaseVelocidadCollection;

    public NomCategoria() {
    }

    public NomCategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public NomCategoria(Integer idcategoria, String categoria) {
        this.idcategoria = idcategoria;
        this.categoria = categoria;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @XmlTransient
    public Collection<DatFaseBloque> getDatFaseBloqueCollection() {
        return datFaseBloqueCollection;
    }

    public void setDatFaseBloqueCollection(Collection<DatFaseBloque> datFaseBloqueCollection) {
        this.datFaseBloqueCollection = datFaseBloqueCollection;
    }

    @XmlTransient
    public Collection<DatFaseDificultad> getDatFaseDificultadCollection() {
        return datFaseDificultadCollection;
    }

    public void setDatFaseDificultadCollection(Collection<DatFaseDificultad> datFaseDificultadCollection) {
        this.datFaseDificultadCollection = datFaseDificultadCollection;
    }

    @XmlTransient
    public Collection<DatAllround> getDatAllroundCollection() {
        return datAllroundCollection;
    }

    public void setDatAllroundCollection(Collection<DatAllround> datAllroundCollection) {
        this.datAllroundCollection = datAllroundCollection;
    }

    @XmlTransient
    public Collection<HisResultado> getHisResultadoCollection() {
        return hisResultadoCollection;
    }

    public void setHisResultadoCollection(Collection<HisResultado> hisResultadoCollection) {
        this.hisResultadoCollection = hisResultadoCollection;
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
        hash += (idcategoria != null ? idcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomCategoria)) {
            return false;
        }
        NomCategoria other = (NomCategoria) object;
        if ((this.idcategoria == null && other.idcategoria != null) || (this.idcategoria != null && !this.idcategoria.equals(other.idcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.NomCategoria[ idcategoria=" + idcategoria + " ]";
    }
    
}
