package trab_jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CadastrosJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public CadastrosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cadastros cad) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em.persist(cad);

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Cadastros cad) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em.merge(cad);

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Cadastros findCadastros(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cadastros.class, id);
        } finally {
            em.close();
        }
    }
    
    public void destroy(int id) {
        EntityManager em = null;
        EntityTransaction tx = null;
    
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Cadastros cad = em.find(Cadastros.class, id);
            if (cad != null) {
                em.remove(cad);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
        public List<Cadastros> findCadastrosEntities() {
        return findCadastrosEntities(true, -1, -1);
    }

    public List<Cadastros> findCadastrosEntities(int maxResults, int firstResult) {
        return findCadastrosEntities(false, maxResults, firstResult);
    }

    private List<Cadastros> findCadastrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Cadastros> cq = em.getCriteriaBuilder().createQuery(Cadastros.class);
            cq.select(cq.from(Cadastros.class));
            TypedQuery<Cadastros> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    // Contar número total de registros
    public int getCadastrosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<Cadastros> rt = cq.from(Cadastros.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            TypedQuery<Long> q = em.createQuery(cq);
            return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

}
