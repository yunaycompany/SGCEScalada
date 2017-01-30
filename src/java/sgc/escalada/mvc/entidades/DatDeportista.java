/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgc.escalada.mvc.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ariam
 */
@Entity
@Table(name = "sgc_escalada.dat_deportista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatDeportista.findAll", query = "SELECT d FROM DatDeportista d"),
    @NamedQuery(name = "DatDeportista.findByIddeportista", query = "SELECT d FROM DatDeportista d WHERE d.iddeportista = :iddeportista"),
    @NamedQuery(name = "DatDeportista.findByNombre", query = "SELECT d FROM DatDeportista d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DatDeportista.findByPapellido", query = "SELECT d FROM DatDeportista d WHERE d.papellido = :papellido"),
    @NamedQuery(name = "DatDeportista.findBySapellido", query = "SELECT d FROM DatDeportista d WHERE d.sapellido = :sapellido"),
    @NamedQuery(name = "DatDeportista.findByUrlfoto", query = "SELECT d FROM DatDeportista d WHERE d.urlfoto = :urlfoto"),
    @NamedQuery(name = "DatDeportista.findByFechanacimiento", query = "SELECT d FROM DatDeportista d WHERE d.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "DatDeportista.findByCedula", query = "SELECT d FROM DatDeportista d WHERE d.cedula = :cedula")})
public class DatDeportista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddeportista")
    private Integer iddeportista;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "papellido")
    private String papellido;
    @Basic(optional = false)
    @Column(name = "sapellido")
    private String sapellido;
    @Column(name = "urlfoto")
    private String urlfoto;
    @Basic(optional = false)
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Basic(optional = false)
    @Column(name = "cedula")
    private String cedula;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datDeportista",orphanRemoval = true)
    private Collection<DatFaseBloque> datFaseBloqueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datDeportista",orphanRemoval = true)
    private Collection<DatFaseDificultad> datFaseDificultadCollection;
    @JoinColumn(name = "idtiposangre", referencedColumnName = "idtiposangre")
    @ManyToOne(optional = false)
    private NomTipoSangre idtiposangre;
    @JoinColumn(name = "idsexo", referencedColumnName = "idsexo")
    @ManyToOne(optional = false)
    private NomSexo idsexo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datDeportista",orphanRemoval = true)
    private Collection<DatAllround> datAllroundCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datDeportista",orphanRemoval = true)
    private Collection<HisResultado> hisResultadoCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "datDeportista",orphanRemoval = true)
    private DatDeportistaFederacion datDeportistaFederacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datDeportista",orphanRemoval = true)
    private Collection<DatFaseVelocidad> datFaseVelocidadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datDeportista",orphanRemoval = true)
    private Collection<DatDireccion> datDireccionCollection;
    @JoinTable(name = "sgc_escalada.dat_direccion", joinColumns = {
        @JoinColumn(name = "iddeportista", referencedColumnName = "iddeportista")}, inverseJoinColumns = {
        @JoinColumn(name = "idparroquia", referencedColumnName = "idparroquia")})
    @ManyToMany
    private Collection<NomParroquia> nomParroquiaCollection;

    public DatDeportista() {
    }

    public DatDeportista(Integer iddeportista) {
        this.iddeportista = iddeportista;
    }

    public DatDeportista(Integer iddeportista, String nombre, String papellido, String sapellido, Date fechanacimiento, String cedula) {
        this.iddeportista = iddeportista;
        this.nombre = nombre;
        this.papellido = papellido;
        this.sapellido = sapellido;
        this.fechanacimiento = fechanacimiento;
        this.cedula = cedula;
    }

    public Integer getIddeportista() {
        return iddeportista;
    }

    public void setIddeportista(Integer iddeportista) {
        this.iddeportista = iddeportista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPapellido() {
        return papellido;
    }

    public void setPapellido(String papellido) {
        this.papellido = papellido;
    }

    public String getSapellido() {
        return sapellido;
    }

    public void setSapellido(String sapellido) {
        this.sapellido = sapellido;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getEdad() {
        long nacimiento = fechanacimiento.getTime();
        long actual = new Date().getTime();
        return (int) Math.floor((actual - nacimiento) / (1000 * 60 * 60 * 24)) / 365;
    }
    /*
     1-Infantil B Hombres 8-9
     2-Infantil B Mujeres
     3-Infantil A Hombres 10-11
     4-Infantil A Mujeres
     5-PrejuvenilHombres 12-13
     6-Prejuvenil Mujeres
     7-Juvenil B Hombres 14-15
     8-Juvenil B Mujeres
     9-Juvenil A Hombres 16-17
     10-Juvenil A Mujeres
     11-Junior Hombres 18-19
     12-Junior Mujeres
     13-Senior Hombres 20+
     14-Senior Mujeres
     15-No tiene
     */

    public int getCategoria() {
        int edad = getEdad();
        if (edad == 8 || edad == 9) {
            if (idsexo.getIdsexo() == 1) {
                return 1;
            }
            return 2;
        }
        if (edad == 10 || edad == 11) {
            if (idsexo.getIdsexo() == 1) {
                return 3;
            }
            return 4;
        }
        if (edad == 12 || edad == 13) {
            if (idsexo.getIdsexo() == 1) {
                return 5;
            }
            return 6;
        }
        if (edad == 14 || edad == 15) {
            if (idsexo.getIdsexo() == 1) {
                return 7;
            }
            return 8;
        }
        if (edad == 16 || edad == 17) {
            if (idsexo.getIdsexo() == 1) {
                return 9;
            }
            return 10;
        }
        if (edad == 18 || edad == 19) {
            if (idsexo.getIdsexo() == 1) {
                return 11;
            }
            return 12;
        }
        if (edad >= 20) {
            if (idsexo.getIdsexo() == 1) {
                return 13;
            }
            return 14;
        }

        return 15;
    }

    @XmlTransient
    public Collection<NomParroquia> getNomParroquiaCollection() {
        return nomParroquiaCollection;
    }

    public void setNomParroquiaCollection(Collection<NomParroquia> nomParroquiaCollection) {
        this.nomParroquiaCollection = nomParroquiaCollection;
    }

    @XmlTransient
    public Collection<DatFaseBloque> getDatFaseBloqueCollection() {
        return datFaseBloqueCollection;
    }

    public void setDatFaseBloqueCollection(Collection<DatFaseBloque> datFaseBloqueCollection) {
        this.datFaseBloqueCollection = datFaseBloqueCollection;
    }

    @XmlTransient
    public Collection<DatFaseDificultad> getDatFaseDificultadCollection() {
        return datFaseDificultadCollection;
    }

    public void setDatFaseDificultadCollection(Collection<DatFaseDificultad> datFaseDificultadCollection) {
        this.datFaseDificultadCollection = datFaseDificultadCollection;
    }

    public NomTipoSangre getIdtiposangre() {
        return idtiposangre;
    }

    public void setIdtiposangre(NomTipoSangre idtiposangre) {
        this.idtiposangre = idtiposangre;
    }

    public NomSexo getIdsexo() {
        return idsexo;
    }

    public void setIdsexo(NomSexo idsexo) {
        this.idsexo = idsexo;
    }

    @XmlTransient
    public Collection<DatAllround> getDatAllroundCollection() {
        return datAllroundCollection;
    }

    public void setDatAllroundCollection(Collection<DatAllround> datAllroundCollection) {
        this.datAllroundCollection = datAllroundCollection;
    }

    @XmlTransient
    public Collection<HisResultado> getHisResultadoCollection() {
        return hisResultadoCollection;
    }

    public void setHisResultadoCollection(Collection<HisResultado> hisResultadoCollection) {
        this.hisResultadoCollection = hisResultadoCollection;
    }

    public DatDeportistaFederacion getDatDeportistaFederacion() {
        return datDeportistaFederacion;
    }

    public void setDatDeportistaFederacion(DatDeportistaFederacion datDeportistaFederacion) {
        this.datDeportistaFederacion = datDeportistaFederacion;
    }

    @XmlTransient
    public Collection<DatFaseVelocidad> getDatFaseVelocidadCollection() {
        return datFaseVelocidadCollection;
    }

    public void setDatFaseVelocidadCollection(Collection<DatFaseVelocidad> datFaseVelocidadCollection) {
        this.datFaseVelocidadCollection = datFaseVelocidadCollection;
    }

    @XmlTransient
    public Collection<DatDireccion> getDatDireccionCollection() {
        return datDireccionCollection;
    }

    public void setDatDireccionCollection(Collection<DatDireccion> datDireccionCollection) {
        this.datDireccionCollection = datDireccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddeportista != null ? iddeportista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatDeportista)) {
            return false;
        }
        DatDeportista other = (DatDeportista) object;
        if ((this.iddeportista == null && other.iddeportista != null) || (this.iddeportista != null && !this.iddeportista.equals(other.iddeportista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatDeportista[ iddeportista=" + iddeportista + " ]";
    }

}
