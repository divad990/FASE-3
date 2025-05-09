package Model.DAO;

import Model.Equipo;
import Model.Jugador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase `EquipoDAO` que gestiona las operaciones relacionadas con los equipos en la base de datos.
 * Utiliza JPA para interactuar con la base de datos.
 */
public class EquipoDAO {

    private final EntityManager em; // Administrador de entidades para gestionar la conexión con la base de datos
    private final EntityTransaction t; // Transacción para gestionar las operaciones en la base de datos

    /**
     * Constructor de la clase `EquipoDAO`.
     *
     * @param em El administrador de entidades.
     * @param t La transacción para las operaciones.
     */
    public EquipoDAO(EntityManager em, EntityTransaction t) {
        this.em = em;
        this.t = t;
    }

    /**
     * Obtiene una lista de nombres de todos los equipos disponibles en la base de datos.
     *
     * @return Una lista de nombres de equipos o `null` si ocurre un error.
     */
    public List<String> obtenerEquipos() {
        try {
            t.begin();
            List<String> equipos = List.of(em.createQuery("SELECT e.nombre FROM Equipo e", String.class).getResultList().toArray(new String[0]));
            t.commit();
            return equipos;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener equipos de la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List<Equipo> obtenerEquiposCompleto() {
        try {
            t.begin();
            List<Equipo> equipos = em.createQuery("SELECT e FROM Equipo e", Equipo.class).getResultList();
            t.commit();
            return equipos;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener equipos de la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Obtiene un equipo específico de la base de datos por su nombre.
     *
     * @param nombre El nombre del equipo a buscar.
     * @return Un objeto `Equipo` que representa el equipo encontrado o `null` si ocurre un error.
     */
    public Equipo obtenerEquipo(String nombre) {
        try {
            t.begin();
            Equipo equipo = em.createQuery("SELECT e FROM Equipo e WHERE e.nombre = :nombre", Equipo.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
            t.commit();
            return equipo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el equipo de la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Equipo obtenerEquipoPorId(Integer e1O) {
        t.begin();
        Equipo equipo = em.createQuery("SELECT e FROM Equipo e WHERE e.id = :id", Equipo.class)
                .setParameter("id", e1O)
                .getSingleResult();
        t.commit();
        return equipo;
    }

    public void crearEquipo(String nombre, LocalDate fechaFundacion) {
        t.begin();
        Equipo e = new Equipo();
        e.setNombre(nombre);
        e.setFechaFundacion(fechaFundacion);
        em.persist(e);
        t.commit();
    }

    public void eliminarEquipo(String nombre) {
        t.begin();
        Equipo e = em.find(Equipo.class, nombre);
        em.remove(e);
        t.commit();
    }
  
    public void modificarEquipo(String nombre, LocalDate fecha) {
        t.begin();
        Equipo e = em.find(Equipo.class, nombre);
        e.setFechaFundacion(fecha);
        em.merge(e);
        t.commit();

    }
}