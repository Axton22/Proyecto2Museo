/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Persistencia.Coleccion;
import Persistencia.Especies;
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
public class EspeciesJpaController {
    private EntityManagerFactory emf = null;
    
    public EspeciesJpaController () {
        this.emf = Persistence.createEntityManagerFactory("MuseoBD");
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    private Collection<Especies> findEspeciesEntities(boolean all, int maxResults, int firstResults) {
        EntityManager em = this.getEmf().createEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Especies.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResults);
            }
            
            return (Collection<Especies>)q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Collection<Especies> findEspeciesEntities() {
        return findEspeciesEntities(false, 0, 0);
    }
    
    public Collection<Especies> findEspeciesEntities(int maxResults, int firstResults) {
        return findEspeciesEntities(false, maxResults, firstResults);
    }
    
    public void create(Especies especies) {
        EntityManager em = null;
        
        try {
            em = getEmf().createEntityManager();
            em.getTransaction().begin();
            em.persist(especies);
            em.getTransaction().commit();
            
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Especies especies) {
        EntityManager em = this.getEmf().createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.merge(especies);
            em.getTransaction().commit();
            
        } finally {
            if(em != null) {
                em.close();
            }
        }
    } 
    
    public void delete(Especies especies) {
        EntityManager em = this.getEmf().createEntityManager();
        
        try {
            em.getTransaction().begin();
            Especies porEliminar = em.find(Especies.class, especies.getId());
            em.remove(porEliminar);
            em.getTransaction().commit();
            
        } finally {
            if(em != null) {
                em.close();
            }
        }
    }
}
