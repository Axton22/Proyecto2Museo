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
@Table(name = "tematica")
@NamedQueries({
    @NamedQuery(name = "Tematica.findAll", query = "SELECT t FROM Tematica t"),
    @NamedQuery(name = "Tematica.findById", query = "SELECT t FROM Tematica t WHERE t.id = :id"),
    @NamedQuery(name = "Tematica.findByNombreTematica", query = "SELECT t FROM Tematica t WHERE t.nombreTematica = :nombreTematica"),
    @NamedQuery(name = "Tematica.findByEpocaTematica", query = "SELECT t FROM Tematica t WHERE t.epocaTematica = :epocaTematica")})
public class Tematica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombreTematica")
    private String nombreTematica;
    @Lob
    @Column(name = "caracteristicas")
    private String caracteristicas;
    @Column(name = "epocaTematica")
    private String epocaTematica;
    @JoinColumn(name = "idSala", referencedColumnName = "id")
    @ManyToOne
    private Sala idSala;

    public Tematica() {
    }

    public Tematica(Integer id) {
        this.id = id;
    }

    public Tematica(Integer id, String nombreTematica) {
        this.id = id;
        this.nombreTematica = nombreTematica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreTematica() {
        return nombreTematica;
    }

    public void setNombreTematica(String nombreTematica) {
        this.nombreTematica = nombreTematica;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getEpocaTematica() {
        return epocaTematica;
    }

    public void setEpocaTematica(String epocaTematica) {
        this.epocaTematica = epocaTematica;
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
        if (!(object instanceof Tematica)) {
            return false;
        }
        Tematica other = (Tematica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Tematica[ id=" + id + " ]";
    }
    
}
