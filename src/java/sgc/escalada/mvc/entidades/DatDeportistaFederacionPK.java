/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sgc.escalada.mvc.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Yosbel
 */
@Embeddable
public class DatDeportistaFederacionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idfederacion")
    private int idfederacion;
    @Basic(optional = false)
    @Column(name = "iddeportista")
    private int iddeportista;

    public DatDeportistaFederacionPK() {
    }

    public DatDeportistaFederacionPK(int idfederacion, int iddeportista) {
        this.idfederacion = idfederacion;
        this.iddeportista = iddeportista;
    }

    public int getIdfederacion() {
        return idfederacion;
    }

    public void setIdfederacion(int idfederacion) {
        this.idfederacion = idfederacion;
    }

    public int getIddeportista() {
        return iddeportista;
    }

    public void setIddeportista(int iddeportista) {
        this.iddeportista = iddeportista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idfederacion;
        hash += (int) iddeportista;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatDeportistaFederacionPK)) {
            return false;
        }
        DatDeportistaFederacionPK other = (DatDeportistaFederacionPK) object;
        if (this.idfederacion != other.idfederacion) {
            return false;
        }
        if (this.iddeportista != other.iddeportista) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatDeportistaFederacionPK[ idfederacion=" + idfederacion + ", iddeportista=" + iddeportista + " ]";
    }
    
}
