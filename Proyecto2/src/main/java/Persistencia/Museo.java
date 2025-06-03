/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Axton Urbina
 */
@Entity
@Table(name = "museo")
@NamedQueries({
    @NamedQuery(name = "Museo.findAll", query = "SELECT m FROM Museo m"),
    @NamedQuery(name = "Museo.findById", query = "SELECT m FROM Museo m WHERE m.id = :id"),
    @NamedQuery(name = "Museo.findByNombre", query = "SELECT m FROM Museo m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Museo.findByTipo", query = "SELECT m FROM Museo m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "Museo.findByUbicacion", query = "SELECT m FROM Museo m WHERE m.ubicacion = :ubicacion"),
    @NamedQuery(name = "Museo.findByFechaFundacion", query = "SELECT m FROM Museo m WHERE m.fechaFundacion = :fechaFundacion"),
    @NamedQuery(name = "Museo.findByNombreDirector", query = "SELECT m FROM Museo m WHERE m.nombreDirector = :nombreDirector"),
    @NamedQuery(name = "Museo.findBySitioWeb", query = "SELECT m FROM Museo m WHERE m.sitioWeb = :sitioWeb")})
public class Museo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "fechaFundacion")
    @Temporal(TemporalType.DATE)
    private Date fechaFundacion;
    @Column(name = "nombreDirector")
    private String nombreDirector;
    @Column(name = "sitioWeb")
    private String sitioWeb;
    @OneToMany(mappedBy = "idMuseo")
    private Collection<Sala> salaCollection;

    public Museo() {
    }

    public Museo(Integer id) {
        this.id = id;
    }

    public Museo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(Date fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public Collection<Sala> getSalaCollection() {
        return salaCollection;
    }

    public void setSalaCollection(Collection<Sala> salaCollection) {
        this.salaCollection = salaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Museo)) {
            return false;
        }
        Museo other = (Museo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Museo[ id=" + id + " ]";
    }
    
}
