/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgc.escalada.mvc.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ariam
 */
@Entity
@Table(name = "sgc_escalada.dat_galeria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatGaleria.findAll", query = "SELECT d FROM DatGaleria d"),
    @NamedQuery(name = "DatGaleria.findByIdmedia", query = "SELECT d FROM DatGaleria d WHERE d.idmedia = :idmedia"),
    @NamedQuery(name = "DatGaleria.findByUrl", query = "SELECT d FROM DatGaleria d WHERE d.url = :url")})
public class DatGaleria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmedia")
    private Integer idmedia;
    @Basic(optional = false)
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "idtipomedia", referencedColumnName = "idtipomedia")
    @ManyToOne(optional = false)
    private NomTipoMedia idtipomedia;
    @JoinColumn(name = "idcompetencia", referencedColumnName = "idcompetencia")
    @OneToOne(optional = false)
    private DatCompetencia idcompetencia;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public DatGaleria() {
    }

    public DatGaleria(Integer idmedia) {
        this.idmedia = idmedia;
    }

    public DatGaleria(Integer idmedia, String url, String nombre, Date fecha) {
        this.idmedia = idmedia;
        this.url = url;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public Integer getIdmedia() {
        return idmedia;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public void setIdmedia(Integer idmedia) {
        this.idmedia = idmedia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public NomTipoMedia getIdtipomedia() {
        return idtipomedia;
    }

    public void setIdtipomedia(NomTipoMedia idtipomedia) {
        this.idtipomedia = idtipomedia;
    }

    public DatCompetencia getIdcompetencia() {
        return idcompetencia;
    }

    public void setIdcompetencia(DatCompetencia idcompetencia) {
        this.idcompetencia = idcompetencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedia != null ? idmedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatGaleria)) {
            return false;
        }
        DatGaleria other = (DatGaleria) object;
        if ((this.idmedia == null && other.idmedia != null) || (this.idmedia != null && !this.idmedia.equals(other.idmedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatGaleria[ idmedia=" + idmedia + " ]";
    }

}
