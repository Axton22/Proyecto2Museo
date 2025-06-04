/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Persistencia.Coleccion;
import Persistencia.Sala;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Axton Urbina
 */
public class ColeccionJpaController implements Serializable {
    private EntityManagerFactory emf = null;
    
    public ColeccionJpaController () {
        this.emf = Persistence.createEntityManagerFactory("MuseoBD");
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    private Collection<Coleccion> findColeccionEntities(boolean all, int maxResults, int firstResults) {
        EntityManager em = this.getEmf().createEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Coleccion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResults);
            }
            
            return (Collection<Coleccion>)q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Collection<Coleccion> findColeccionEntities() {
        return findColeccionEntities(false, 0, 0);
    }
    
    public Collection<Coleccion> findColeccionEntities(int maxResults, int firstResults) {
        return findColeccionEntities(false, maxResults, firstResults);
    }
    
    public void create(Coleccion col) {
        EntityManager em = null;
        
        try {
            em = getEmf().createEntityManager();
            em.getTransaction().begin();
            em.persist(col);
            em.getTransaction().commit();
            
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Coleccion col) {
        EntityManager em = this.getEmf().createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.merge(col);
            em.getTransaction().commit();
            
        } finally {
            if(em != null) {
                em.close();
            }
        }
    } 
    
    public void delete(Coleccion col) {
        EntityManager em = this.getEmf().createEntityManager();
        
        try {
            em.getTransaction().begin();
            Coleccion porEliminar = em.find(Coleccion.class, col.getId());
            em.remove(porEliminar);
            em.getTransaction().commit();
            
        } finally {
            if(em != null) {
                em.close();
            }
        }
    }
}
