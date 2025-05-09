package Model.DAO;

import Model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/**
 * Clase `UsuarioDAO` que gestiona las operaciones relacionadas con los usuarios en la base de datos.
 * Utiliza JPA para interactuar con la base de datos.
 */
public class UsuarioDAO {

    private final EntityManager em; // Administrador de entidades para gestionar la conexión con la base de datos
    private final EntityTransaction t; // Transacción para gestionar las operaciones en la base de datos

    /**
     * Constructor de la clase `UsuarioDAO`.
     *
     * @param em El administrador de entidades.
     * @param t La transacción para las operaciones.
    */
    public UsuarioDAO(EntityManager em, EntityTransaction t) {
        this.em = em;
        this.t = t;
    }

    /**
     * Busca un usuario en la base de datos por su nombre de usuario.
     *
     * @param usuario El objeto `Usuario` que se utilizará para almacenar el resultado de la búsqueda.
     * @return El objeto `Usuario` encontrado en la base de datos.
     * @throws Exception Si ocurre un error durante la búsqueda o si el usuario no se encuentra.
     */
    public Usuario buscar (Usuario usuario) throws Exception{
        try
        {
            t.begin();
            Usuario user = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                    .setParameter("username", usuario.getUsername())
                    .getResultStream().findFirst().orElse(null);
            // Si no encuentra el usuario. p es null
            if (usuario == null) //TODO Estoy hay que mirarlo
                throw new Exception();
            t.commit();
            return user;
        }
        catch (Exception e) {
            t.rollback();
            throw new Exception(e.getClass() + "\n" + "Error al buscar el usuario ");
        }
    }
}
