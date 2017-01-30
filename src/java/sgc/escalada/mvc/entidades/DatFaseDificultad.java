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
@Table(name = "sgc_escalada.dat_fasedificultad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatFaseDificultad.findAll", query = "SELECT d FROM DatFaseDificultad d"),
    @NamedQuery(name = "DatFaseDificultad.findByIdcompetencia", query = "SELECT d FROM DatFaseDificultad d WHERE d.datFaseDificultadPK.idcompetencia = :idcompetencia"),
    @NamedQuery(name = "DatFaseDificultad.findByIddeportista", query = "SELECT d FROM DatFaseDificultad d WHERE d.datFaseDificultadPK.iddeportista = :iddeportista"),
    @NamedQuery(name = "DatFaseDificultad.findByIdcategoria", query = "SELECT d FROM DatFaseDificultad d WHERE d.datFaseDificultadPK.idcategoria = :idcategoria"),
    @NamedQuery(name = "DatFaseDificultad.findByPresa", query = "SELECT d FROM DatFaseDificultad d WHERE d.presa = :presa"),
    @NamedQuery(name = "DatFaseDificultad.findByAgarre", query = "SELECT d FROM DatFaseDificultad d WHERE d.agarre = :agarre"),
    @NamedQuery(name = "DatFaseDificultad.findByPuntos", query = "SELECT d FROM DatFaseDificultad d WHERE d.puntos = :puntos"),
    @NamedQuery(name = "DatFaseDificultad.findByFinalizada", query = "SELECT d FROM DatFaseDificultad d WHERE d.finalizada = :finalizada")})
public class DatFaseDificultad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatFaseDificultadPK datFaseDificultadPK;
    @Column(name = "presa")
    private Integer presa;
    @Column(name = "agarre")
    private String agarre;
    @Basic(optional = false)
    @Column(name = "puntos")
    private double puntos;
    @Basic(optional = false)
    @Column(name = "finalizada")
    private boolean finalizada;
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private NomCategoria nomCategoria;
    @JoinColumn(name = "iddeportista", referencedColumnName = "iddeportista", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DatDeportista datDeportista;
    @JoinColumn(name = "idcompetencia", referencedColumnName = "idcompetencia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DatCompetencia datCompetencia;

    public DatFaseDificultad() {
    }

    public DatFaseDificultad(DatFaseDificultadPK datFaseDificultadPK) {
        this.datFaseDificultadPK = datFaseDificultadPK;
    }

    public DatFaseDificultad(DatFaseDificultadPK datFaseDificultadPK, double puntos, boolean finalizada) {
        this.datFaseDificultadPK = datFaseDificultadPK;
        this.puntos = puntos;
        this.finalizada = finalizada;
    }

    public DatFaseDificultad(int idcompetencia, int iddeportista, int idcategoria) {
        this.datFaseDificultadPK = new DatFaseDificultadPK(idcompetencia, iddeportista, idcategoria);
    }

    public DatFaseDificultadPK getDatFaseDificultadPK() {
        return datFaseDificultadPK;
    }

    public void setDatFaseDificultadPK(DatFaseDificultadPK datFaseDificultadPK) {
        this.datFaseDificultadPK = datFaseDificultadPK;
    }

    public Integer getPresa() {
        return presa;
    }

    public void setPresa(Integer presa) {
        this.presa = presa;
    }

    public String getAgarre() {
        return agarre;
    }

    public void setAgarre(String agarre) {
        this.agarre = agarre;
    }

    public double getPuntos() {
        return puntos;
    }

    public void setPuntos(double puntos) {
        this.puntos = puntos;
    }

    public boolean getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
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
        hash += (datFaseDificultadPK != null ? datFaseDificultadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatFaseDificultad)) {
            return false;
        }
        DatFaseDificultad other = (DatFaseDificultad) object;
        if ((this.datFaseDificultadPK == null && other.datFaseDificultadPK != null) || (this.datFaseDificultadPK != null && !this.datFaseDificultadPK.equals(other.datFaseDificultadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatFaseDificultad[ datFaseDificultadPK=" + datFaseDificultadPK + " ]";
    }
    
}
