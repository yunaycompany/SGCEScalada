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
 * @author Ariam
 */
@Entity
@Table(name = "sgc_escalada.dat_fasebloque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatFaseBloque.findAll", query = "SELECT d FROM DatFaseBloque d"),
    @NamedQuery(name = "DatFaseBloque.findByIdcompetencia", query = "SELECT d FROM DatFaseBloque d WHERE d.datFaseBloquePK.idcompetencia = :idcompetencia"),
    @NamedQuery(name = "DatFaseBloque.findByIddeportista", query = "SELECT d FROM DatFaseBloque d WHERE d.datFaseBloquePK.iddeportista = :iddeportista"),
    @NamedQuery(name = "DatFaseBloque.findByIdcategoria", query = "SELECT d FROM DatFaseBloque d WHERE d.datFaseBloquePK.idcategoria = :idcategoria"),
    @NamedQuery(name = "DatFaseBloque.findByIdetapa", query = "SELECT d FROM DatFaseBloque d WHERE d.datFaseBloquePK.idetapa = :idetapa"),
    @NamedQuery(name = "DatFaseBloque.findByT1", query = "SELECT d FROM DatFaseBloque d WHERE d.t1 = :t1"),
    @NamedQuery(name = "DatFaseBloque.findByB1", query = "SELECT d FROM DatFaseBloque d WHERE d.b1 = :b1"),
    @NamedQuery(name = "DatFaseBloque.findByT2", query = "SELECT d FROM DatFaseBloque d WHERE d.t2 = :t2"),
    @NamedQuery(name = "DatFaseBloque.findByB2", query = "SELECT d FROM DatFaseBloque d WHERE d.b2 = :b2"),
    @NamedQuery(name = "DatFaseBloque.findByT3", query = "SELECT d FROM DatFaseBloque d WHERE d.t3 = :t3"),
    @NamedQuery(name = "DatFaseBloque.findByB3", query = "SELECT d FROM DatFaseBloque d WHERE d.b3 = :b3"),
    @NamedQuery(name = "DatFaseBloque.findByT4", query = "SELECT d FROM DatFaseBloque d WHERE d.t4 = :t4"),
    @NamedQuery(name = "DatFaseBloque.findByB4", query = "SELECT d FROM DatFaseBloque d WHERE d.b4 = :b4"),
    @NamedQuery(name = "DatFaseBloque.findByT5", query = "SELECT d FROM DatFaseBloque d WHERE d.t5 = :t5"),
    @NamedQuery(name = "DatFaseBloque.findByB5", query = "SELECT d FROM DatFaseBloque d WHERE d.b5 = :b5"),
    @NamedQuery(name = "DatFaseBloque.findByFinalizada", query = "SELECT d FROM DatFaseBloque d WHERE d.finalizada = :finalizada")})
public class DatFaseBloque implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatFaseBloquePK datFaseBloquePK;
    @Column(name = "t1")
    private Integer t1;
    @Column(name = "b1")
    private Integer b1;
    @Column(name = "t2")
    private Integer t2;
    @Column(name = "b2")
    private Integer b2;
    @Column(name = "t3")
    private Integer t3;
    @Column(name = "b3")
    private Integer b3;
    @Column(name = "t4")
    private Integer t4;
    @Column(name = "b4")
    private Integer b4;
    @Column(name = "t5")
    private Integer t5;
    @Column(name = "b5")
    private Integer b5;
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

    public DatFaseBloque() {
    }

    public DatFaseBloque(DatFaseBloquePK datFaseBloquePK) {
        this.datFaseBloquePK = datFaseBloquePK;
    }

    public DatFaseBloque(DatFaseBloquePK datFaseBloquePK, boolean finalizada) {
        this.datFaseBloquePK = datFaseBloquePK;
        this.finalizada = finalizada;
    }

    public DatFaseBloque(int idcompetencia, int iddeportista, int idcategoria, int idetapa) {
        this.datFaseBloquePK = new DatFaseBloquePK(idcompetencia, iddeportista, idcategoria, idetapa);
    }

    public DatFaseBloquePK getDatFaseBloquePK() {
        return datFaseBloquePK;
    }

    public void setDatFaseBloquePK(DatFaseBloquePK datFaseBloquePK) {
        this.datFaseBloquePK = datFaseBloquePK;
    }

    public Integer getT1() {
        return t1;
    }

    public void setT1(Integer t1) {
        this.t1 = t1;
    }

    public Integer getB1() {
        return b1;
    }

    public void setB1(Integer b1) {
        this.b1 = b1;
    }

    public Integer getT2() {
        return t2;
    }

    public void setT2(Integer t2) {
        this.t2 = t2;
    }

    public Integer getB2() {
        return b2;
    }

    public void setB2(Integer b2) {
        this.b2 = b2;
    }

    public Integer getT3() {
        return t3;
    }

    public void setT3(Integer t3) {
        this.t3 = t3;
    }

    public Integer getB3() {
        return b3;
    }

    public void setB3(Integer b3) {
        this.b3 = b3;
    }

    public Integer getT4() {
        return t4;
    }

    public void setT4(Integer t4) {
        this.t4 = t4;
    }

    public Integer getB4() {
        return b4;
    }

    public void setB4(Integer b4) {
        this.b4 = b4;
    }

    public Integer getT5() {
        return t5;
    }

    public void setT5(Integer t5) {
        this.t5 = t5;
    }

    public Integer getB5() {
        return b5;
    }

    public void setB5(Integer b5) {
        this.b5 = b5;
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
        hash += (datFaseBloquePK != null ? datFaseBloquePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatFaseBloque)) {
            return false;
        }
        DatFaseBloque other = (DatFaseBloque) object;
        if ((this.datFaseBloquePK == null && other.datFaseBloquePK != null) || (this.datFaseBloquePK != null && !this.datFaseBloquePK.equals(other.datFaseBloquePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatFaseBloque[ datFaseBloquePK=" + datFaseBloquePK + " ]";
    }
    
}
