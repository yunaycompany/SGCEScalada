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
@Table(name = "sgc_escalada.dat_allround")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatAllround.findAll", query = "SELECT d FROM DatAllround d"),
    @NamedQuery(name = "DatAllround.findByIddeportista", query = "SELECT d FROM DatAllround d WHERE d.datAllroundPK.iddeportista = :iddeportista"),
    @NamedQuery(name = "DatAllround.findByIdcompetencia", query = "SELECT d FROM DatAllround d WHERE d.datAllroundPK.idcompetencia = :idcompetencia"),
    @NamedQuery(name = "DatAllround.findByRankingv", query = "SELECT d FROM DatAllround d WHERE d.rankingv = :rankingv"),
    @NamedQuery(name = "DatAllround.findByPuntosv", query = "SELECT d FROM DatAllround d WHERE d.puntosv = :puntosv"),
    @NamedQuery(name = "DatAllround.findByRankingd", query = "SELECT d FROM DatAllround d WHERE d.rankingd = :rankingd"),
    @NamedQuery(name = "DatAllround.findByPuntosd", query = "SELECT d FROM DatAllround d WHERE d.puntosd = :puntosd"),
    @NamedQuery(name = "DatAllround.findByRankingb", query = "SELECT d FROM DatAllround d WHERE d.rankingb = :rankingb"),
    @NamedQuery(name = "DatAllround.findByPuntosb", query = "SELECT d FROM DatAllround d WHERE d.puntosb = :puntosb"),
    @NamedQuery(name = "DatAllround.findByTotalpuntos", query = "SELECT d FROM DatAllround d WHERE d.totalpuntos = :totalpuntos"),
    @NamedQuery(name = "DatAllround.findByRanking", query = "SELECT d FROM DatAllround d WHERE d.ranking = :ranking"),
    @NamedQuery(name = "DatAllround.findByPuntos", query = "SELECT d FROM DatAllround d WHERE d.puntos = :puntos")})
public class DatAllround implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatAllroundPK datAllroundPK;
    @Column(name = "rankingv")
    private Integer rankingv;
    @Column(name = "puntosv")
    private Integer puntosv;
    @Column(name = "rankingd")
    private Integer rankingd;
    @Column(name = "puntosd")
    private Integer puntosd;
    @Column(name = "rankingb")
    private Integer rankingb;
    @Column(name = "puntosb")
    private Integer puntosb;
    @Column(name = "totalpuntos")
    private Integer totalpuntos;
    @Column(name = "ranking")
    private Integer ranking;
    @Column(name = "puntos")
    private Integer puntos;
    @JoinColumn(name = "idprovincia", referencedColumnName = "idprovincia")
    @ManyToOne(optional = false)
    private NomProvincia idprovincia;
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
    @ManyToOne(optional = false)
    private NomCategoria idcategoria;
    @JoinColumn(name = "iddeportista", referencedColumnName = "iddeportista", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DatDeportista datDeportista;
    @JoinColumn(name = "idcompetencia", referencedColumnName = "idcompetencia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DatCompetencia datCompetencia;

    public DatAllround() {
    }

    public DatAllround(DatAllroundPK datAllroundPK) {
        this.datAllroundPK = datAllroundPK;
    }

    public DatAllround(int iddeportista, int idcompetencia) {
        this.datAllroundPK = new DatAllroundPK(iddeportista, idcompetencia);
    }

    public DatAllroundPK getDatAllroundPK() {
        return datAllroundPK;
    }

    public void setDatAllroundPK(DatAllroundPK datAllroundPK) {
        this.datAllroundPK = datAllroundPK;
    }

    public Integer getRankingv() {
        return rankingv;
    }

    public void setRankingv(Integer rankingv) {
        this.rankingv = rankingv;
    }

    public Integer getPuntosv() {
        return puntosv;
    }

    public void setPuntosv(Integer puntosv) {
        this.puntosv = puntosv;
    }

    public Integer getRankingd() {
        return rankingd;
    }

    public void setRankingd(Integer rankingd) {
        this.rankingd = rankingd;
    }

    public Integer getPuntosd() {
        return puntosd;
    }

    public void setPuntosd(Integer puntosd) {
        this.puntosd = puntosd;
    }

    public Integer getRankingb() {
        return rankingb;
    }

    public void setRankingb(Integer rankingb) {
        this.rankingb = rankingb;
    }

    public Integer getPuntosb() {
        return puntosb;
    }

    public void setPuntosb(Integer puntosb) {
        this.puntosb = puntosb;
    }

    public Integer getTotalpuntos() {
        return totalpuntos;
    }

    public void setTotalpuntos(Integer totalpuntos) {
        this.totalpuntos = totalpuntos;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public NomProvincia getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(NomProvincia idprovincia) {
        this.idprovincia = idprovincia;
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
        hash += (datAllroundPK != null ? datAllroundPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatAllround)) {
            return false;
        }
        DatAllround other = (DatAllround) object;
        if ((this.datAllroundPK == null && other.datAllroundPK != null) || (this.datAllroundPK != null && !this.datAllroundPK.equals(other.datAllroundPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatAllround[ datAllroundPK=" + datAllroundPK + " ]";
    }
    
}
