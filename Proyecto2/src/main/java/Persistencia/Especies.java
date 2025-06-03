/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Axton Urbina
 */
@Entity
@Table(name = "especies")
@NamedQueries({
    @NamedQuery(name = "Especies.findAll", query = "SELECT e FROM Especies e"),
    @NamedQuery(name = "Especies.findById", query = "SELECT e FROM Especies e WHERE e.id = :id"),
    @NamedQuery(name = "Especies.findByNombreCientifico", query = "SELECT e FROM Especies e WHERE e.nombreCientifico = :nombreCientifico"),
    @NamedQuery(name = "Especies.findByNombreComun", query = "SELECT e FROM Especies e WHERE e.nombreComun = :nombreComun"),
    @NamedQuery(name = "Especies.findByFechaExtioncion", query = "SELECT e FROM Especies e WHERE e.fechaExtioncion = :fechaExtioncion"),
    @NamedQuery(name = "Especies.findByEpocaVivio", query = "SELECT e FROM Especies e WHERE e.epocaVivio = :epocaVivio"),
    @NamedQuery(name = "Especies.findByPeso", query = "SELECT e FROM Especies e WHERE e.peso = :peso"),
    @NamedQuery(name = "Especies.findByTamanio", query = "SELECT e FROM Especies e WHERE e.tamanio = :tamanio")})
public class Especies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombreCientifico")
    private String nombreCientifico;
    @Column(name = "nombreComun")
    private String nombreComun;
    @Column(name = "fechaExtioncion")
    @Temporal(TemporalType.DATE)
    private Date fechaExtioncion;
    @Column(name = "epocaVivio")
    private String epocaVivio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso")
    private BigDecimal peso;
    @Column(name = "tamanio")
    private BigDecimal tamanio;
    @Lob
    @Column(name = "caracteristicas")
    private String caracteristicas;
    @JoinColumn(name = "idColeccion", referencedColumnName = "id")
    @ManyToOne
    private Coleccion idColeccion;

    public Especies() {
    }

    public Especies(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public Date getFechaExtioncion() {
        return fechaExtioncion;
    }

    public void setFechaExtioncion(Date fechaExtioncion) {
        this.fechaExtioncion = fechaExtioncion;
    }

    public String getEpocaVivio() {
        return epocaVivio;
    }

    public void setEpocaVivio(String epocaVivio) {
        this.epocaVivio = epocaVivio;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getTamanio() {
        return tamanio;
    }

    public void setTamanio(BigDecimal tamanio) {
        this.tamanio = tamanio;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Coleccion getIdColeccion() {
        return idColeccion;
    }

    public void setIdColeccion(Coleccion idColeccion) {
        this.idColeccion = idColeccion;
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
        if (!(object instanceof Especies)) {
            return false;
        }
        Especies other = (Especies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Especies[ id=" + id + " ]";
    }
    
}
