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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Axton Urbina
 */
@Entity
@Table(name = "ComisionTarjetas")
@NamedQueries({
    @NamedQuery(name = "ComisionTarjetas.findAll", query = "SELECT c FROM ComisionTarjetas c"),
    @NamedQuery(name = "ComisionTarjetas.findByTipoTarjeta", query = "SELECT c FROM ComisionTarjetas c WHERE c.tipoTarjeta = :tipoTarjeta"),
    @NamedQuery(name = "ComisionTarjetas.findByComision", query = "SELECT c FROM ComisionTarjetas c WHERE c.comision = :comision")})
public class ComisionTarjetas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tipoTarjeta")
    private String tipoTarjeta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "comision")
    private BigDecimal comision;

    public ComisionTarjetas() {
    }

    public ComisionTarjetas(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoTarjeta != null ? tipoTarjeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComisionTarjetas)) {
            return false;
        }
        ComisionTarjetas other = (ComisionTarjetas) object;
        if ((this.tipoTarjeta == null && other.tipoTarjeta != null) || (this.tipoTarjeta != null && !this.tipoTarjeta.equals(other.tipoTarjeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipoTarjeta;
    }
    
}
