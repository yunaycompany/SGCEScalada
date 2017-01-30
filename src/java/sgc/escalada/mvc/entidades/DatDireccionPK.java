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
 * @author Ariam
 */
@Embeddable
public class DatDireccionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idparroquia")
    private int idparroquia;
    @Basic(optional = false)
    @Column(name = "iddeportista")
    private int iddeportista;

    public DatDireccionPK() {
    }

    public DatDireccionPK(int idparroquia, int iddeportista) {
        this.idparroquia = idparroquia;
        this.iddeportista = iddeportista;
    }

    public int getIdparroquia() {
        return idparroquia;
    }

    public void setIdparroquia(int idparroquia) {
        this.idparroquia = idparroquia;
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
        hash += (int) idparroquia;
        hash += (int) iddeportista;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatDireccionPK)) {
            return false;
        }
        DatDireccionPK other = (DatDireccionPK) object;
        if (this.idparroquia != other.idparroquia) {
            return false;
        }
        if (this.iddeportista != other.iddeportista) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatDireccionPK[ idparroquia=" + idparroquia + ", iddeportista=" + iddeportista + " ]";
    }
    
}
