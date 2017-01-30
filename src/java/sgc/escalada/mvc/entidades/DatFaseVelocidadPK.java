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
public class DatFaseVelocidadPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idcompetencia")
    private int idcompetencia;
    @Basic(optional = false)
    @Column(name = "iddeportista")
    private int iddeportista;
    @Basic(optional = false)
    @Column(name = "idcategoria")
    private int idcategoria;
    @Basic(optional = false)
    @Column(name = "idetapa")
    private int idetapa;

    public DatFaseVelocidadPK() {
    }

    public DatFaseVelocidadPK(int idcompetencia, int iddeportista, int idcategoria, int idetapa) {
        this.idcompetencia = idcompetencia;
        this.iddeportista = iddeportista;
        this.idcategoria = idcategoria;
        this.idetapa = idetapa;
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

    public int getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(int idetapa) {
        this.idetapa = idetapa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcompetencia;
        hash += (int) iddeportista;
        hash += (int) idcategoria;
        hash += (int) idetapa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatFaseVelocidadPK)) {
            return false;
        }
        DatFaseVelocidadPK other = (DatFaseVelocidadPK) object;
        if (this.idcompetencia != other.idcompetencia) {
            return false;
        }
        if (this.iddeportista != other.iddeportista) {
            return false;
        }
        if (this.idcategoria != other.idcategoria) {
            return false;
        }
        if (this.idetapa != other.idetapa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatFaseVelocidadPK[ idcompetencia=" + idcompetencia + ", iddeportista=" + iddeportista + ", idcategoria=" + idcategoria + ", idetapa=" + idetapa + " ]";
    }
    
}
