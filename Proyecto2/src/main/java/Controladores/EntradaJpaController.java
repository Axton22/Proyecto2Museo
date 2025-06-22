/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Persistencia.Entrada;
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
public class EntradaJpaController implements Serializable {
    private EntityManagerFactory emf = null;
    
    public EntradaJpaController () {
        this.emf = Persistence.createEntityManagerFactory("MuseoBD");
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    private Collection<Entrada> findEntradaEntities(boolean all, int maxResults, int firstResults) {
        EntityManager em = this.getEmf().createEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entrada.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResults);
            }
            
            return (Collection<Entrada>)q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Collection<Entrada> findEntradaEntities() {
        return findEntradaEntities(false, 0, 0);
    }
    
    public Collection<Entrada> findEntradaEntities(int maxResults, int firstResults) {
        return findEntradaEntities(false, maxResults, firstResults);
    }
    
    public void create(Entrada entrada) {
        EntityManager em = null;
        
        try {
            em = getEmf().createEntityManager();
            em.getTransaction().begin();
            em.persist(entrada);
            em.getTransaction().commit();
            
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Entrada entrada) {
        EntityManager em = this.getEmf().createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.merge(entrada);
            em.getTransaction().commit();
            
        } finally {
            if(em != null) {
                em.close();
            }
        }
    } 
    
    public void delete(Entrada entrada) {
        EntityManager em = this.getEmf().createEntityManager();
        
        try {
            em.getTransaction().begin();
            Entrada porEliminar = em.find(Entrada.class, entrada.getId());
            em.remove(porEliminar);
            em.getTransaction().commit();
            
        } finally {
            if(em != null) {
                em.close();
            }
        }
    }
}
