package be.multimedi.repository;

import be.multimedi.model.Property;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class PropertyRepositoryImpl implements PropertyRepository{
    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("immoPU");

    @Override
    public void save(Property property) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(property);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Property> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Property p", Property.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }

}
