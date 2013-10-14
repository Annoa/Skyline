package com.skyline.model.utils;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Abstract implementation of a database access object
 * 
 * T is type for items in container K is type of id (primary key)
 *
 * @author anno
 */
public abstract class AbstractDAO<T, K> implements IDAO<T, K> {

    private EntityManagerFactory emf;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    private final Class<T> clazz;

    public AbstractDAO(Class<T> clazz, String puName) {
        this.clazz = clazz;
        emf = Persistence.createEntityManagerFactory(puName);
    }

    @Override
    public void add(T t) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Add={" + ex.getMessage() + "}");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void remove(K id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            T t = em.getReference(clazz, id);
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Remove={" + ex.getMessage() + "}");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public T update(T t) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Update={" + ex.getMessage() + "}");
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return t;
    }

    @Override
    public T find(K id) {
        EntityManager em = emf.createEntityManager();
        T t = em.find(clazz, id);
        return t;
    }

    @Override
    public List<T> getRange(int first, int nItems) {
        return get(false, nItems, first);
    }

    private List<T> get(boolean all, int maxResults, int firstResult) {
        EntityManager em = emf.createEntityManager();
        List<T> found = new ArrayList<T>();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clazz));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            found.addAll(q.getResultList());
        } catch (Exception ex) {
            System.out.println("Get={" + ex.getMessage() + "}");
        } finally {
            em.close();
        }
        return found;
    }

    @Override
    public int getCount() {
        EntityManager em = emf.createEntityManager();
        int count = -1;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<T> rt = cq.from(clazz);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            count = ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            System.out.println("Count={" + ex.getMessage() + "}");
        } finally {
            em.close();
        }
        return count;
    }
}
