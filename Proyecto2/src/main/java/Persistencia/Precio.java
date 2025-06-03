/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author Axton Urbina
 */
@Entity
@Table(name = "precio")
@NamedQueries({
    @NamedQuery(name = "Precio.findAll", query = "SELECT p FROM Precio p"),
    @NamedQuery(name = "Precio.findById", query = "SELECT p FROM Precio p WHERE p.id = :id"),
    @NamedQuery(name = "Precio.findByPrecioLunesSabado", query = "SELECT p FROM Precio p WHERE p.precioLunesSabado = :precioLunesSabado"),
    @NamedQuery(name = "Precio.findByPrecioDomingo", query = "SELECT p FROM Precio p WHERE p.precioDomingo = :precioDomingo")})
public class Precio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioLunesSabado")
    private BigDecimal precioLunesSabado;
    @Column(name = "precioDomingo")
    private BigDecimal precioDomingo;
    @JoinColumn(name = "idSala", referencedColumnName = "id")
    @ManyToOne
    private Sala idSala;

    public Precio() {
    }

    public Precio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrecioLunesSabado() {
        return precioLunesSabado;
    }

    public void setPrecioLunesSabado(BigDecimal precioLunesSabado) {
        this.precioLunesSabado = precioLunesSabado;
    }

    public BigDecimal getPrecioDomingo() {
        return precioDomingo;
    }

    public void setPrecioDomingo(BigDecimal precioDomingo) {
        this.precioDomingo = precioDomingo;
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
        if (!(object instanceof Precio)) {
            return false;
        }
        Precio other = (Precio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Precio[ id=" + id + " ]";
    }
    
}
