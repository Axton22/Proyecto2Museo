/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Persistencia.Entrada;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
    public List<Object[]> obtenerTotalesPorTarjeta(LocalDate desde, LocalDate hasta) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT e.nombreTarjeta, SUM(e.comision) " +
                        "FROM Entrada e " +
                        "WHERE e.fechaVisita BETWEEN :desde AND :hasta " +
                        "GROUP BY e.nombreTarjeta";

        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        
        Date desdeDate = Date.from(desde.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date hastaDate = Date.from(hasta.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        query.setParameter("desde", desdeDate);
        query.setParameter("hasta", hastaDate);

        List<Object[]> resultados = query.getResultList();
        em.close();
        emf.close();

        return resultados;
    }
}
