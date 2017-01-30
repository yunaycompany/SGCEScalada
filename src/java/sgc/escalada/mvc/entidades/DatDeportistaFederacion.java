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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author Yosbel
 */
@Entity
@Table(name = "sgc_escalada.dat_deportistafederacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatDeportistaFederacion.findAll", query = "SELECT d FROM DatDeportistaFederacion d"),
    @NamedQuery(name = "DatDeportistaFederacion.findByIdfederacion", query = "SELECT d FROM DatDeportistaFederacion d WHERE d.datDeportistaFederacionPK.idfederacion = :idfederacion"),
    @NamedQuery(name = "DatDeportistaFederacion.findByIddeportista", query = "SELECT d FROM DatDeportistaFederacion d WHERE d.datDeportistaFederacionPK.iddeportista = :iddeportista"),
    @NamedQuery(name = "DatDeportistaFederacion.findByNumeroregistro", query = "SELECT d FROM DatDeportistaFederacion d WHERE d.numeroregistro = :numeroregistro"),
    @NamedQuery(name = "DatDeportistaFederacion.findByFechaingreso", query = "SELECT d FROM DatDeportistaFederacion d WHERE d.fechaingreso = :fechaingreso")})
public class DatDeportistaFederacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatDeportistaFederacionPK datDeportistaFederacionPK;
    @Basic(optional = false)
    @Column(name = "numeroregistro")
    private int numeroregistro;
    @Basic(optional = false)
    @Column(name = "fechaingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaingreso;
    @JoinColumn(name = "idfederacion", referencedColumnName = "idfederacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private NomFederacionProvincial nomFederacionProvincial;
    @JoinColumn(name = "iddeportista", referencedColumnName = "iddeportista", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private DatDeportista datDeportista;

    public DatDeportistaFederacion() {
    }

    public DatDeportistaFederacion(DatDeportistaFederacionPK datDeportistaFederacionPK) {
        this.datDeportistaFederacionPK = datDeportistaFederacionPK;
    }

    public DatDeportistaFederacion(DatDeportistaFederacionPK datDeportistaFederacionPK, int numeroregistro, Date fechaingreso) {
        this.datDeportistaFederacionPK = datDeportistaFederacionPK;
        this.numeroregistro = numeroregistro;
        this.fechaingreso = fechaingreso;
    }

    public DatDeportistaFederacion(int idfederacion, int iddeportista) {
        this.datDeportistaFederacionPK = new DatDeportistaFederacionPK(idfederacion, iddeportista);
    }

    public DatDeportistaFederacionPK getDatDeportistaFederacionPK() {
        return datDeportistaFederacionPK;
    }

    public void setDatDeportistaFederacionPK(DatDeportistaFederacionPK datDeportistaFederacionPK) {
        this.datDeportistaFederacionPK = datDeportistaFederacionPK;
    }

    public int getNumeroregistro() {
        return numeroregistro;
    }

    public void setNumeroregistro(int numeroregistro) {
        this.numeroregistro = numeroregistro;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public NomFederacionProvincial getNomFederacionProvincial() {
        return nomFederacionProvincial;
    }

    public void setNomFederacionProvincial(NomFederacionProvincial nomFederacionProvincial) {
        this.nomFederacionProvincial = nomFederacionProvincial;
    }

    public DatDeportista getDatDeportista() {
        return datDeportista;
    }

    public void setDatDeportista(DatDeportista datDeportista) {
        this.datDeportista = datDeportista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datDeportistaFederacionPK != null ? datDeportistaFederacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatDeportistaFederacion)) {
            return false;
        }
        DatDeportistaFederacion other = (DatDeportistaFederacion) object;
        if ((this.datDeportistaFederacionPK == null && other.datDeportistaFederacionPK != null) || (this.datDeportistaFederacionPK != null && !this.datDeportistaFederacionPK.equals(other.datDeportistaFederacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatDeportistaFederacion[ datDeportistaFederacionPK=" + datDeportistaFederacionPK + " ]";
    }
    
}
