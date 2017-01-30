/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sgc.escalada.mvc.entidades;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "sgc_escalada.his_resultado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HisResultado.findAll", query = "SELECT h FROM HisResultado h"),
    @NamedQuery(name = "HisResultado.findByIdresultado", query = "SELECT h FROM HisResultado h WHERE h.hisResultadoPK.idresultado = :idresultado"),
    @NamedQuery(name = "HisResultado.findByIddeportista", query = "SELECT h FROM HisResultado h WHERE h.hisResultadoPK.iddeportista = :iddeportista"),
    @NamedQuery(name = "HisResultado.findByIdcompetencia", query = "SELECT h FROM HisResultado h WHERE h.hisResultadoPK.idcompetencia = :idcompetencia"),
    @NamedQuery(name = "HisResultado.findByPosicion", query = "SELECT h FROM HisResultado h WHERE h.posicion = :posicion")})
public class HisResultado implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HisResultadoPK hisResultadoPK;
    @Column(name = "posicion")
    private Integer posicion;
    @JoinColumn(name = "idlugar", referencedColumnName = "idlugar")
    @ManyToOne(optional = false)
    private NomLugar idlugar;
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
    @ManyToOne(optional = false)
    private NomCategoria idcategoria;
    @JoinColumn(name = "iddeportista", referencedColumnName = "iddeportista", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DatDeportista datDeportista;
    @JoinColumn(name = "idcompetencia", referencedColumnName = "idcompetencia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DatCompetencia datCompetencia;

    public HisResultado() {
    }

    public HisResultado(HisResultadoPK hisResultadoPK) {
        this.hisResultadoPK = hisResultadoPK;
    }

    public HisResultado(int idresultado, int iddeportista, int idcompetencia) {
        this.hisResultadoPK = new HisResultadoPK(idresultado, iddeportista, idcompetencia);
    }

    public HisResultadoPK getHisResultadoPK() {
        return hisResultadoPK;
    }

    public void setHisResultadoPK(HisResultadoPK hisResultadoPK) {
        this.hisResultadoPK = hisResultadoPK;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public NomLugar getIdlugar() {
        return idlugar;
    }

    public void setIdlugar(NomLugar idlugar) {
        this.idlugar = idlugar;
    }

    public NomCategoria getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(NomCategoria idcategoria) {
        this.idcategoria = idcategoria;
    }

    public DatDeportista getDatDeportista() {
        return datDeportista;
    }

    public void setDatDeportista(DatDeportista datDeportista) {
        this.datDeportista = datDeportista;
    }

    public DatCompetencia getDatCompetencia() {
        return datCompetencia;
    }

    public void setDatCompetencia(DatCompetencia datCompetencia) {
        this.datCompetencia = datCompetencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hisResultadoPK != null ? hisResultadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HisResultado)) {
            return false;
        }
        HisResultado other = (HisResultado) object;
        if ((this.hisResultadoPK == null && other.hisResultadoPK != null) || (this.hisResultadoPK != null && !this.hisResultadoPK.equals(other.hisResultadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.HisResultado[ hisResultadoPK=" + hisResultadoPK + " ]";
    }
    
}
