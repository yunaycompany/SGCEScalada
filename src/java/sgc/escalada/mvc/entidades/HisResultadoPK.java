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
public class HisResultadoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idresultado")
    private int idresultado;
    @Basic(optional = false)
    @Column(name = "iddeportista")
    private int iddeportista;
    @Basic(optional = false)
    @Column(name = "idcompetencia")
    private int idcompetencia;

    public HisResultadoPK() {
    }

    public HisResultadoPK(int idresultado, int iddeportista, int idcompetencia) {
        this.idresultado = idresultado;
        this.iddeportista = iddeportista;
        this.idcompetencia = idcompetencia;
    }

    public int getIdresultado() {
        return idresultado;
    }

    public void setIdresultado(int idresultado) {
        this.idresultado = idresultado;
    }

    public int getIddeportista() {
        return iddeportista;
    }

    public void setIddeportista(int iddeportista) {
        this.iddeportista = iddeportista;
    }

    public int getIdcompetencia() {
        return idcompetencia;
    }

    public void setIdcompetencia(int idcompetencia) {
        this.idcompetencia = idcompetencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idresultado;
        hash += (int) iddeportista;
        hash += (int) idcompetencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HisResultadoPK)) {
            return false;
        }
        HisResultadoPK other = (HisResultadoPK) object;
        if (this.idresultado != other.idresultado) {
            return false;
        }
        if (this.iddeportista != other.iddeportista) {
            return false;
        }
        if (this.idcompetencia != other.idcompetencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.HisResultadoPK[ idresultado=" + idresultado + ", iddeportista=" + iddeportista + ", idcompetencia=" + idcompetencia + " ]";
    }
    
}
