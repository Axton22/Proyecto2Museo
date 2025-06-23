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
@Table(name = "entrada")
@NamedQueries({
    @NamedQuery(name = "Entrada.findAll", query = "SELECT e FROM Entrada e"),
    @NamedQuery(name = "Entrada.findById", query = "SELECT e FROM Entrada e WHERE e.id = :id"),
    @NamedQuery(name = "Entrada.findByNombreVisitante", query = "SELECT e FROM Entrada e WHERE e.nombreVisitante = :nombreVisitante"),
    @NamedQuery(name = "Entrada.findByFechaVisita", query = "SELECT e FROM Entrada e WHERE e.fechaVisita = :fechaVisita"),
    @NamedQuery(name = "Entrada.findByPrecio", query = "SELECT e FROM Entrada e WHERE e.precio = :precio"),
    @NamedQuery(name = "Entrada.findByComision", query = "SELECT e FROM Entrada e WHERE e.comision = :comision"),
    @NamedQuery(name = "Entrada.findByIva", query = "SELECT e FROM Entrada e WHERE e.iva = :iva"),
    @NamedQuery(name = "Entrada.findByTotalPagar", query = "SELECT e FROM Entrada e WHERE e.totalPagar = :totalPagar"),
    @NamedQuery(name = "Entrada.findByAutorizada", query = "SELECT e FROM Entrada e WHERE e.autorizada = :autorizada")})
public class Entrada implements Serializable {

    @Column(name = "nombreTarjeta")
    private String nombreTarjeta;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "comision")
    private BigDecimal comision;
    @Column(name = "IVA")
    private BigDecimal iva;
    @Column(name = "totalPagar")
    private BigDecimal totalPagar;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombreVisitante")
    private String nombreVisitante;
    @Column(name = "fechaVisita")
    @Temporal(TemporalType.DATE)
    private Date fechaVisita;
    @Column(name = "autorizada")
    private Boolean autorizada;
    @JoinColumn(name = "idSala", referencedColumnName = "id")
    @ManyToOne
    private Sala idSala;

    public Entrada() {
    }

    public Entrada(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreVisitante() {
        return nombreVisitante;
    }

    public void setNombreVisitante(String nombreVisitante) {
        this.nombreVisitante = nombreVisitante;
    }

    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }


    public Boolean getAutorizada() {
        return autorizada;
    }

    public void setAutorizada(Boolean autorizada) {
        this.autorizada = autorizada;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
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
        if (!(object instanceof Entrada)) {
            return false;
        }
        Entrada other = (Entrada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entrada[ id=" + id + " ]";
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(BigDecimal totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }
    
}
