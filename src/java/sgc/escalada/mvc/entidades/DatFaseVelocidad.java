/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sgc.escalada.mvc.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "sgc_escalada.dat_fasevelocidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatFaseVelocidad.findAll", query = "SELECT d FROM DatFaseVelocidad d"),
    @NamedQuery(name = "DatFaseVelocidad.findByIdcompetencia", query = "SELECT d FROM DatFaseVelocidad d WHERE d.datFaseVelocidadPK.idcompetencia = :idcompetencia"),
    @NamedQuery(name = "DatFaseVelocidad.findByIddeportista", query = "SELECT d FROM DatFaseVelocidad d WHERE d.datFaseVelocidadPK.iddeportista = :iddeportista"),
    @NamedQuery(name = "DatFaseVelocidad.findByIdcategoria", query = "SELECT d FROM DatFaseVelocidad d WHERE d.datFaseVelocidadPK.idcategoria = :idcategoria"),
    @NamedQuery(name = "DatFaseVelocidad.findByIdetapa", query = "SELECT d FROM DatFaseVelocidad d WHERE d.datFaseVelocidadPK.idetapa = :idetapa"),
    @NamedQuery(name = "DatFaseVelocidad.findByTiempo1", query = "SELECT d FROM DatFaseVelocidad d WHERE d.tiempo1 = :tiempo1"),
    @NamedQuery(name = "DatFaseVelocidad.findByTiempo2", query = "SELECT d FROM DatFaseVelocidad d WHERE d.tiempo2 = :tiempo2"),
    @NamedQuery(name = "DatFaseVelocidad.findByTiempofinal", query = "SELECT d FROM DatFaseVelocidad d WHERE d.tiempofinal = :tiempofinal"),
    @NamedQuery(name = "DatFaseVelocidad.findByFinalizada", query = "SELECT d FROM DatFaseVelocidad d WHERE d.finalizada = :finalizada")})
public class DatFaseVelocidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatFaseVelocidadPK datFaseVelocidadPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tiempo1")
    private Float tiempo1;
    @Column(name = "tiempo2")
    private Float tiempo2;
    @Column(name = "tiempofinal")
    private Float tiempofinal;
    @Basic(optional = false)
    @Column(name = "finalizada")
    private boolean finalizada;
    @JoinColumn(name = "idetapa", referencedColumnName = "idetapa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private NomEtapa nomEtapa;
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private NomCategoria nomCategoria;
    @JoinColumn(name = "iddeportista", referencedColumnName = "iddeportista", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DatDeportista datDeportista;
    @JoinColumn(name = "idcompetencia", referencedColumnName = "idcompetencia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DatCompetencia datCompetencia;

    public DatFaseVelocidad() {
    }

    public DatFaseVelocidad(DatFaseVelocidadPK datFaseVelocidadPK) {
        this.datFaseVelocidadPK = datFaseVelocidadPK;
    }

    public DatFaseVelocidad(DatFaseVelocidadPK datFaseVelocidadPK, boolean finalizada) {
        this.datFaseVelocidadPK = datFaseVelocidadPK;
        this.finalizada = finalizada;
    }

    public DatFaseVelocidad(int idcompetencia, int iddeportista, int idcategoria, int idetapa) {
        this.datFaseVelocidadPK = new DatFaseVelocidadPK(idcompetencia, iddeportista, idcategoria, idetapa);
    }

    public DatFaseVelocidadPK getDatFaseVelocidadPK() {
        return datFaseVelocidadPK;
    }

    public void setDatFaseVelocidadPK(DatFaseVelocidadPK datFaseVelocidadPK) {
        this.datFaseVelocidadPK = datFaseVelocidadPK;
    }

    public Float getTiempo1() {
        return tiempo1;
    }

    public void setTiempo1(Float tiempo1) {
        this.tiempo1 = tiempo1;
    }

    public Float getTiempo2() {
        return tiempo2;
    }

    public void setTiempo2(Float tiempo2) {
        this.tiempo2 = tiempo2;
    }

    public Float getTiempofinal() {
        return tiempofinal;
    }

    public void setTiempofinal(Float tiempofinal) {
        this.tiempofinal = tiempofinal;
    }

    public boolean getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public NomEtapa getNomEtapa() {
        return nomEtapa;
    }

    public void setNomEtapa(NomEtapa nomEtapa) {
        this.nomEtapa = nomEtapa;
    }

    public NomCategoria getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(NomCategoria nomCategoria) {
        this.nomCategoria = nomCategoria;
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
        hash += (datFaseVelocidadPK != null ? datFaseVelocidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatFaseVelocidad)) {
            return false;
        }
        DatFaseVelocidad other = (DatFaseVelocidad) object;
        if ((this.datFaseVelocidadPK == null && other.datFaseVelocidadPK != null) || (this.datFaseVelocidadPK != null && !this.datFaseVelocidadPK.equals(other.datFaseVelocidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatFaseVelocidad[ datFaseVelocidadPK=" + datFaseVelocidadPK + " ]";
    }
    
}
