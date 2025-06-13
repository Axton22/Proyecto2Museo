/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Axton Urbina
 */
@Entity
@Table(name = "coleccion")
@NamedQueries({
    @NamedQuery(name = "Coleccion.findAll", query = "SELECT c FROM Coleccion c"),
    @NamedQuery(name = "Coleccion.findById", query = "SELECT c FROM Coleccion c WHERE c.id = :id"),
    @NamedQuery(name = "Coleccion.findByNombreColeccion", query = "SELECT c FROM Coleccion c WHERE c.nombreColeccion = :nombreColeccion"),
    @NamedQuery(name = "Coleccion.findBySiglo", query = "SELECT c FROM Coleccion c WHERE c.siglo = :siglo")})
public class Coleccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombreColeccion")
    private String nombreColeccion;
    @Column(name = "siglo")
    private String siglo;
    @Lob
    @Column(name = "descripcionColeccion")
    private String descripcionColeccion;
    @JoinColumn(name = "idSala", referencedColumnName = "id")
    @ManyToOne
    private Sala idSala;
    @OneToMany(mappedBy = "idColeccion")
    private Collection<Especies> especiesCollection;

    public Coleccion() {
    }

    public Coleccion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreColeccion() {
        return nombreColeccion;
    }

    public void setNombreColeccion(String nombreColeccion) {
        this.nombreColeccion = nombreColeccion;
    }

    public String getSiglo() {
        return siglo;
    }

    public void setSiglo(String siglo) {
        this.siglo = siglo;
    }

    public String getDescripcionColeccion() {
        return descripcionColeccion;
    }

    public void setDescripcionColeccion(String descripcionColeccion) {
        this.descripcionColeccion = descripcionColeccion;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    public Collection<Especies> getEspeciesCollection() {
        return especiesCollection;
    }

    public void setEspeciesCollection(Collection<Especies> especiesCollection) {
        this.especiesCollection = especiesCollection;
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
        if (!(object instanceof Coleccion)) {
            return false;
        }
        Coleccion other = (Coleccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreColeccion;
    }
    
}
