package org.kalum.core.db;

import org.kalum.core.utils.KalumException;
import sun.security.x509.CertificatePolicyMap;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class ConexionPU {
    private final String PERSISTENCE_UNIT_NAME = "KalumPU";
    private EntityManager entityManager;
    private static ConexionPU instancia;

    public ConexionPU() {
        try {
            entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                    .createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConexionPU getInstancia() {
        if (instancia == null) {
            instancia = new ConexionPU();
        }
        return instancia;
    }

    public List<?> findAll(String query) throws KalumException {
        List<Object> resultado = null;
        try {
            resultado = entityManager.createNamedQuery(query).getResultList();
        } catch (Exception e) {
            throw new KalumException(50302);
        }
        return resultado;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void agregar(Object elemento) throws KalumException {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(elemento);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            throw new KalumException(50303);
        }
    }

    public void modificar(Object elemento) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(elemento);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.entityManager.getTransaction().rollback();
        }
    }

    public void eliminar(Object elemento) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(elemento);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.entityManager.getTransaction().rollback();
        }
    }

}
