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
@Table(name = "sgc_escalada.nom_lugar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NomLugar.findAll", query = "SELECT n FROM NomLugar n"),
    @NamedQuery(name = "NomLugar.findByIdlugar", query = "SELECT n FROM NomLugar n WHERE n.idlugar = :idlugar"),
    @NamedQuery(name = "NomLugar.findByLugar", query = "SELECT n FROM NomLugar n WHERE n.lugar = :lugar")})
public class NomLugar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlugar")
    private Integer idlugar;
    @Basic(optional = false)
    @Column(name = "lugar")
    private String lugar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idlugar")
    private Collection<DatCompetencia> datCompetenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idlugar")
    private Collection<HisResultado> hisResultadoCollection;
    @JoinColumn(name = "idparroquia", referencedColumnName = "idparroquia")
    @ManyToOne(optional = false)
    private NomParroquia idparroquia;

    public NomLugar() {
    }

    public NomLugar(Integer idlugar) {
        this.idlugar = idlugar;
    }

    public NomLugar(Integer idlugar, String lugar) {
        this.idlugar = idlugar;
        this.lugar = lugar;
    }

    public Integer getIdlugar() {
        return idlugar;
    }

    public void setIdlugar(Integer idlugar) {
        this.idlugar = idlugar;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @XmlTransient
    public Collection<DatCompetencia> getDatCompetenciaCollection() {
        return datCompetenciaCollection;
    }

    public void setDatCompetenciaCollection(Collection<DatCompetencia> datCompetenciaCollection) {
        this.datCompetenciaCollection = datCompetenciaCollection;
    }

    @XmlTransient
    public Collection<HisResultado> getHisResultadoCollection() {
        return hisResultadoCollection;
    }

    public void setHisResultadoCollection(Collection<HisResultado> hisResultadoCollection) {
        this.hisResultadoCollection = hisResultadoCollection;
    }

    public NomParroquia getIdparroquia() {
        return idparroquia;
    }

    public void setIdparroquia(NomParroquia idparroquia) {
        this.idparroquia = idparroquia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlugar != null ? idlugar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomLugar)) {
            return false;
        }
        NomLugar other = (NomLugar) object;
        if ((this.idlugar == null && other.idlugar != null) || (this.idlugar != null && !this.idlugar.equals(other.idlugar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.NomLugar[ idlugar=" + idlugar + " ]";
    }
    
}
