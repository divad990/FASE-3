package Model.DAO;

import Model.Equipo;
import Model.Jugador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Clase `JugadorDAO` que gestiona las operaciones relacionadas con los jugadores en la base de datos.
 * Utiliza JPA para interactuar con la base de datos.
 */
public class JugadorDAO {

    private final EntityManager em; // Administrador de entidades para gestionar la conexión con la base de datos
    private final EntityTransaction t; // Transacción para gestionar las operaciones en la base de datos

    /**
     * Constructor de la clase `JugadorDAO`.
     *
     * @param em El administrador de entidades.
     * @param t La transacción para las operaciones.
     */
    public JugadorDAO(EntityManager em, EntityTransaction t) {
        this.em = em;
        this.t = t;
    }

    /**
     * Valida si un DNI ya existe en la base de datos.
     *
     * @param dni El DNI a validar.
     * @return `true` si el DNI ya existe, `false` en caso contrario.
     */
    public boolean validarDni(String dni) {
        t.begin();
        Jugador j = em.find(Jugador.class, dni);
        if (j != null) {
            t.commit();
            return true;
        } else {
            t.rollback();
            return false;
        }
    }

    /**
     * Válida si un nickname ya está en uso en la base de datos.
     *
     * @param nickname El nickname a validar.
     * @return `true` si el nickname ya está en uso, `false` en caso contrario.
     */
    public boolean validarNickname(String nickname) {
        t.begin();
        Jugador j = em.createQuery("SELECT j FROM Jugador j WHERE j.nickname = :nickname", Jugador.class)
                .setParameter("nickname", nickname)
                .getResultStream()
                .findFirst()
                .orElse(null);
        if (j != null) {
            t.commit();
            return true;
        } else {
            t.rollback();
            return false;
        }
    }

    /**
     * Crea un nuevo jugador con los datos proporcionados.
     *
     * @param dni El DNI del jugador.
     * @param nombre El nombre del jugador.
     * @param apellido El apellido del jugador.
     * @param nickname El nickname del jugador.
     * @param nacionalidad La nacionalidad del jugador.
     * @param rol El rol del jugador.
     * @param fechaNacimiento La fecha de nacimiento del jugador.
     * @param sueldo El sueldo del jugador.
     * @param equipo El equipo al que pertenece el jugador.
     */
    public void crearJugador(String dni, String nombre, String apellido, String nickname, String nacionalidad, String rol, java.sql.Date fechaNacimiento, BigDecimal sueldo, Equipo equipo) {
        t.begin();
        Jugador j = new Jugador();
        j.setDni(dni);
        j.setNombre(nombre);
        j.setRol(rol);
        j.setNickname(nickname);
        j.setApellido(apellido);
        j.setNacionalidad(nacionalidad);
        j.setFechaNacimiento(fechaNacimiento.toLocalDate());
        j.setSueldo(sueldo);
        if (equipo != null) {
            j.setIdEquipo(equipo);
        }
        em.persist(j);
        t.commit();
    }

    /**
     * Modifica los datos de un jugador existente.
     *
     * @param dni El DNI del jugador.
     * @param nombre El nombre del jugador.
     * @param apellido El apellido del jugador.
     * @param nickname El nickname del jugador.
     * @param nacionalidad La nacionalidad del jugador.
     * @param rol El rol del jugador.
     * @param fechaNacimiento La fecha de nacimiento del jugador.
     * @param sueldo El sueldo del jugador.
     * @param equipo El equipo al que pertenece el jugador.
     */
    public void modificarJugador(String dni, String nombre, String apellido, String nickname, String nacionalidad, String rol, java.sql.Date fechaNacimiento, BigDecimal sueldo, Equipo equipo) {
        t.begin();
        Jugador j = em.find(Jugador.class, dni);
        if (!nombre.isEmpty()) {
            j.setNombre(nombre);
        }
        if (!apellido.isEmpty()) {
            j.setApellido(apellido);
        }
        if (!nickname.isEmpty()) {
            j.setNickname(nickname);
        }
        if (!nacionalidad.isEmpty()) {
            j.setNacionalidad(nacionalidad);
        }
        if (!rol.isEmpty()) {
            j.setRol(rol);
        }
        if (fechaNacimiento != null) {
            j.setFechaNacimiento(fechaNacimiento.toLocalDate());
        }
        if (sueldo != null) {
            j.setSueldo(sueldo);
        }
        if (equipo != null) {
            j.setIdEquipo(equipo);
        }
        em.merge(j);
        t.commit();
    }

    /**
     * Elimina un jugador de la base de datos.
     *
     * @param dni El DNI del jugador a eliminar.
     */
    public void eliminarJugador(String dni) {
        t.begin();
        Jugador j = em.find(Jugador.class, dni);
        em.remove(j);
        t.commit();
    }

    /**
     * Obtiene un jugador de la base de datos por su NIF.
     *
     * @param nif El NIF del jugador.
     * @return El jugador correspondiente o `null` si no se encuentra.
     */
    public Jugador obtenerJugador(String nif) {
        t.begin();
        Jugador j = em.find(Jugador.class, nif);
        t.commit();
        return j;
    }

    public List<Jugador> obtenerJugadoresEquipo(Equipo equipo) {
        t.begin();
        List<Jugador> jugadores = em.createQuery("SELECT j FROM Jugador j WHERE j.idEquipo = :equipo", Jugador.class)
                .setParameter("equipo", equipo)
                .getResultList();
        t.commit();
        return jugadores;
    }
}