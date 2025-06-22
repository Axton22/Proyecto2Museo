/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.Serializable;
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

/**
 *
 * @author Axton Urbina
 */
@Entity
@Table(name = "valoracionsala")
@NamedQueries({
    @NamedQuery(name = "Valoracionsala.findAll", query = "SELECT v FROM Valoracionsala v"),
    @NamedQuery(name = "Valoracionsala.findById", query = "SELECT v FROM Valoracionsala v WHERE v.id = :id"),
    @NamedQuery(name = "Valoracionsala.findByValoracion", query = "SELECT v FROM Valoracionsala v WHERE v.valoracion = :valoracion")})
public class Valoracionsala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idSala", referencedColumnName = "id")
    @ManyToOne
    private Sala idSala;
    @Column(name = "valoracion")
    private Integer valoracion;
    @Lob
    @Column(name = "observacion")
    private String observacion;

    public Valoracionsala() {
    }

    public Valoracionsala(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    
    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
        if (!(object instanceof Valoracionsala)) {
            return false;
        }
        Valoracionsala other = (Valoracionsala) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Valoracionsala[ id=" + id + " ]";
    }
    
}
