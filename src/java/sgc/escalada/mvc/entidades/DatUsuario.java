package sgc.escalada.mvc.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "sgc_escalada.dat_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatUsuario.findAll", query = "SELECT d FROM DatUsuario d"),
    @NamedQuery(name = "DatUsuario.findByIdusuario", query = "SELECT d FROM DatUsuario d WHERE d.idusuario = :idusuario"),
    @NamedQuery(name = "DatUsuario.findByUsuario", query = "SELECT d FROM DatUsuario d WHERE d.usuario = :usuario"),
    @NamedQuery(name = "DatUsuario.findByContrasenna", query = "SELECT d FROM DatUsuario d WHERE d.contrasenna = :contrasenna")})
public class DatUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "contrasenna")
    private String contrasenna;
    @JoinColumn(name = "idrol", referencedColumnName = "idrol")
    @ManyToOne(optional = false)
    private NomRol idrol;

    public DatUsuario() {
    }

    public DatUsuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public DatUsuario(Integer idusuario, String usuario, String contrasenna) {
        this.idusuario = idusuario;
        this.usuario = usuario;
        this.contrasenna = contrasenna;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public NomRol getIdrol() {
        return idrol;
    }

    public void setIdrol(NomRol idrol) {
        this.idrol = idrol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatUsuario)) {
            return false;
        }
        DatUsuario other = (DatUsuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgc.escalada.mvc.entidades.DatUsuario[ idusuario=" + idusuario + " ]";
    }
    
}
