/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Persistencia.Sala;
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
public class SalaJpaController {
    private EntityManagerFactory emf = null;
    
    public SalaJpaController () {
        this.emf = Persistence.createEntityManagerFactory("MuseoBD");
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    private Collection<Sala> findSalaEntities(boolean all, int maxResults, int firstResults) {
        EntityManager em = this.getEmf().createEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sala.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResults);
            }
            
            return (Collection<Sala>)q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Collection<Sala> findSalaEntities() {
        return findSalaEntities(false, 0, 0);
    }
    
    public Collection<Sala> findSalaEntities(int maxResults, int firstResults) {
        return findSalaEntities(false, maxResults, firstResults);
    }
    
    public void create(Sala sala) {
        EntityManager em = null;
        
        try {
            em = getEmf().createEntityManager();
            em.getTransaction().begin();
            em.persist(sala);
            em.getTransaction().commit();
            
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Sala sala) {
        EntityManager em = this.getEmf().createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.merge(sala);
            em.getTransaction().commit();
            
        } finally {
            if(em != null) {
                em.close();
            }
        }
    } 
    
    public void delete(Sala sala) {
        EntityManager em = this.getEmf().createEntityManager();
        
        try {
            em.getTransaction().begin();
            Sala porEliminar = em.find(Sala.class, sala.getId());
            em.remove(porEliminar);
            em.getTransaction().commit();
            
        } finally {
            if(em != null) {
                em.close();
            }
        }
    }
}
