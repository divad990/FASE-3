package Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * Clase `BaseDatos` que proporciona acceso a la configuración de la base de datos.
 * Utiliza JPA para gestionar la conexión, el administrador de entidades y las transacciones.
 */
public class BaseDatos {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default"); // Fábrica de administradores de entidades
    private static final EntityManager em = emf.createEntityManager(); // Administrador de entidades
    private static final EntityTransaction t = em.getTransaction(); // Transacción para gestionar operaciones en la base de datos

    /**
     * Obtiene el administrador de entidades.
     *
     * @return El administrador de entidades.
     */
    public static EntityManager getEm() {
        return em;
    }

    /**
     * Obtiene la transacción actual.
     *
     * @return La transacción actual.
     */
    public static EntityTransaction getT() {
        return t;
    }

}