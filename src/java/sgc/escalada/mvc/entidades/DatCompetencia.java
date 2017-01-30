/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sgc.escalada.mvc.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ariam
 */
@Entity
@Table(name = "sgc_escalada.dat_competencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatCompetencia.findAll", query = "SELECT d FROM DatCompetencia d"),
    @NamedQuery(name = "DatCompetencia.findByIdcompetencia", query = "SELECT d FROM DatCompetencia d WHERE d.idcompetencia = :idcompetencia"),
    @NamedQuery(name = "DatCompetencia.findByFecha", query = "SELECT d FROM DatCompetencia d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "DatCompetencia.findByNombre", query = "SELECT d FROM DatCompetencia d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DatCompetencia.findByFinalizada", query = "SELECT d FROM DatCompetencia d WHERE d.finalizada = :finalizada")})
public class DatCompetencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompetencia")
    private Integer idcompetencia;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "finalizada")
    private Boolean finalizada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datCompetencia")
    private Collection<DatFaseBloque> datFaseBloqueCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idcompetencia")
    private DatGaleria datGaleria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datCompetencia")
    private Collection<DatFaseDificultad> datFaseDificultadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datCompetencia")
    private Collection<DatAllround> datAllroundCollection;
    @JoinColumn(name = "idlugar", referencedColumnName = "idlugar")
    @ManyToOne(optional = false)
    private NomLugar idlugar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datCompetencia")
    private Collection<HisResultado> hisResultadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datCompetencia")
    private Collection<DatFaseVelocidad> datFaseVelocidadCollection;

    public DatCompetencia() {
    }

    public DatCompetencia(Integer idcompetencia) {
        this.idcompetencia = idcompetencia;
    }

    public DatCompetencia(Integer idcompetencia, Date fecha) {
        this.idcompetencia = idcompetencia;
        this.fecha = fecha;
    }

    public Integer getIdcompetencia() {
        return idcompetencia;
    }

    public void setIdcompetencia(Integer idcompetencia) {
        this.idcompetencia = idcompetencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }

    @XmlTransient
    public Collection<DatFaseBloque> getDatFaseBloqueCollection() {
        return datFaseBloqueCollection;
    }

    public void setDatFaseBloqueCollection(Collection<DatFaseBloque> datFaseBloqueCollection) {
        this.datFaseBloqueCollection = datFaseBloqueCollection;
    }

    public DatGaleria getDatGaleria() {
        return datGaleria;
    }

    public void setDatGaleria(DatGaleria datGaleria) {
        this.datGaleria = datGaleria;
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

    public NomLugar getIdlugar() {
        return idlugar;
    }

    public void setIdlugar(NomLugar idlugar) {
        this.idlugar = idlugar;
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
        hash += (idcompetencia != null ? idcompetencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatCompetencia)) {
            return false;
        }
        DatCompetencia other = (DatCompetencia) object;
        if ((this.idcompetencia == null && other.idcompetencia != null) || (this.idcompetencia != null && !this.idcompetencia.equals(other.idcompetencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatCompetencia[ idcompetencia=" + idcompetencia + " ]";
    }

   public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    } 
    
}
