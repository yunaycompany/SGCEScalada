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
public class DatFaseDificultadPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idcompetencia")
    private int idcompetencia;
    @Basic(optional = false)
    @Column(name = "iddeportista")
    private int iddeportista;
    @Basic(optional = false)
    @Column(name = "idcategoria")
    private int idcategoria;

    public DatFaseDificultadPK() {
    }

    public DatFaseDificultadPK(int idcompetencia, int iddeportista, int idcategoria) {
        this.idcompetencia = idcompetencia;
        this.iddeportista = iddeportista;
        this.idcategoria = idcategoria;
    }

    public int getIdcompetencia() {
        return idcompetencia;
    }

    public void setIdcompetencia(int idcompetencia) {
        this.idcompetencia = idcompetencia;
    }

    public int getIddeportista() {
        return iddeportista;
    }

    public void setIddeportista(int iddeportista) {
        this.iddeportista = iddeportista;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcompetencia;
        hash += (int) iddeportista;
        hash += (int) idcategoria;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatFaseDificultadPK)) {
            return false;
        }
        DatFaseDificultadPK other = (DatFaseDificultadPK) object;
        if (this.idcompetencia != other.idcompetencia) {
            return false;
        }
        if (this.iddeportista != other.iddeportista) {
            return false;
        }
        if (this.idcategoria != other.idcategoria) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatFaseDificultadPK[ idcompetencia=" + idcompetencia + ", iddeportista=" + iddeportista + ", idcategoria=" + idcategoria + " ]";
    }
    
}
