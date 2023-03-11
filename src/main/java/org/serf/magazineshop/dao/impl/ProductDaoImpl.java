package org.serf.magazineshop.dao.impl;

import org.serf.magazineshop.dao.ProductDAO;
import org.serf.magazineshop.domain.Product;
import org.serf.magazineshop.shared.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProductDaoImpl implements ProductDAO {

    private EntityManager em = FactoryManager.getEntityManager();

    @Override
    public Product create(Product product) {
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public Product read(Integer id) {
        Product product = null;
        try {
            product = em.find(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public Product update(Product product) {

        try {
            // TODO
        } catch (Exception e) {
        }

        return product;
    }

    @Override
    public void delete(Integer id) {
        try {
            // TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> readAll() {
        Query query = null;
        try {
            query = em.createQuery("SELECT e FROM Product e");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }
}

