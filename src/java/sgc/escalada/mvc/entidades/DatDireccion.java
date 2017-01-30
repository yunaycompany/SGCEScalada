/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sgc.escalada.mvc.entidades;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yosbel
 */
@Entity
@Table(name = "sgc_escalada.dat_direccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatDireccion.findAll", query = "SELECT d FROM DatDireccion d"),
    @NamedQuery(name = "DatDireccion.findByIdparroquia", query = "SELECT d FROM DatDireccion d WHERE d.datDireccionPK.idparroquia = :idparroquia"),
    @NamedQuery(name = "DatDireccion.findByIddeportista", query = "SELECT d FROM DatDireccion d WHERE d.datDireccionPK.iddeportista = :iddeportista")})
public class DatDireccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatDireccionPK datDireccionPK;
    @JoinColumn(name = "idparroquia", referencedColumnName = "idparroquia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private NomParroquia nomParroquia;
    @JoinColumn(name = "iddeportista", referencedColumnName = "iddeportista", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DatDeportista datDeportista;

    public DatDireccion() {
    }

    public DatDireccion(DatDireccionPK datDireccionPK) {
        this.datDireccionPK = datDireccionPK;
    }

    public DatDireccion(int idparroquia, int iddeportista) {
        this.datDireccionPK = new DatDireccionPK(idparroquia, iddeportista);
    }

    public DatDireccionPK getDatDireccionPK() {
        return datDireccionPK;
    }

    public void setDatDireccionPK(DatDireccionPK datDireccionPK) {
        this.datDireccionPK = datDireccionPK;
    }

    public NomParroquia getNomParroquia() {
        return nomParroquia;
    }

    public void setNomParroquia(NomParroquia nomParroquia) {
        this.nomParroquia = nomParroquia;
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
        hash += (datDireccionPK != null ? datDireccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatDireccion)) {
            return false;
        }
        DatDireccion other = (DatDireccion) object;
        if ((this.datDireccionPK == null && other.datDireccionPK != null) || (this.datDireccionPK != null && !this.datDireccionPK.equals(other.datDireccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatDireccion[ datDireccionPK=" + datDireccionPK + " ]";
    }
    
}
