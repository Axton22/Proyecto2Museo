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
@Table(name = "sala")
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s"),
    @NamedQuery(name = "Sala.findById", query = "SELECT s FROM Sala s WHERE s.id = :id"),
    @NamedQuery(name = "Sala.findByNombreSala", query = "SELECT s FROM Sala s WHERE s.nombreSala = :nombreSala")})
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombreSala")
    private String nombreSala;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idSala")
    private Collection<Precio> precioCollection;
    @OneToMany(mappedBy = "idSala")
    private Collection<Tematica> tematicaCollection;
    @OneToMany(mappedBy = "idSala")
    private Collection<Coleccion> coleccionCollection;
    @JoinColumn(name = "idMuseo", referencedColumnName = "id")
    @ManyToOne
    private Museo idMuseo;

    public Sala() {
    }

    public Sala(Integer id) {
        this.id = id;
    }

    public Sala(Integer id, String nombreSala) {
        this.id = id;
        this.nombreSala = nombreSala;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Precio> getPrecioCollection() {
        return precioCollection;
    }

    public void setPrecioCollection(Collection<Precio> precioCollection) {
        this.precioCollection = precioCollection;
    }

    public Collection<Tematica> getTematicaCollection() {
        return tematicaCollection;
    }

    public void setTematicaCollection(Collection<Tematica> tematicaCollection) {
        this.tematicaCollection = tematicaCollection;
    }

    public Collection<Coleccion> getColeccionCollection() {
        return coleccionCollection;
    }

    public void setColeccionCollection(Collection<Coleccion> coleccionCollection) {
        this.coleccionCollection = coleccionCollection;
    }

    public Museo getIdMuseo() {
        return idMuseo;
    }

    public void setIdMuseo(Museo idMuseo) {
        this.idMuseo = idMuseo;
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
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Sala[ id=" + id + " ]";
    }
    
}
