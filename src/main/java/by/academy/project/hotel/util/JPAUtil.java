package by.academy.project.hotel.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private EntityManager entityManager;
    private static JPAUtil instance;

    public static JPAUtil getInstance() {
        if (instance == null) {
            instance = new JPAUtil();
        }
        return instance;
    }

    private JPAUtil() {
    }

    public EntityManager getEntityManager() {
        if (entityManager == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyHotelPersistence");
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    public void deleteEntityManager() {
        if (entityManager != null) {
            entityManager = null;
        }
    }

}

