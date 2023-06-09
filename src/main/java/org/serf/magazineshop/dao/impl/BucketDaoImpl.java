package org.serf.magazineshop.dao.impl;

import org.serf.magazineshop.dao.BucketDAO;
import org.serf.magazineshop.domain.Bucket;
import org.serf.magazineshop.shared.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class BucketDaoImpl implements BucketDAO {

    private EntityManager em = FactoryManager.getEntityManager();

    @Override
    public Bucket create(Bucket bucket) {
        try {
            em.getTransaction().begin();
            em.persist(bucket);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bucket;
    }

    @Override
    public Bucket read(Integer id) {
        Bucket bucket = null;
        try {
            bucket = em.find(Bucket.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bucket;
    }

    @Override
    public Bucket update(Bucket t) {
        throw new IllegalStateException("there is no update for bucket");
    }

    @Override
    public void delete(Integer id) {
        try {
            Bucket bucket = read(id);
            em.getTransaction().begin();
            em.remove(bucket);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bucket> readAll() {
        Query query = null;
        try {
            query = em.createQuery("SELECT e FROM Bucket e");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }
}
