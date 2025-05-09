package Model.DAO;

import Model.Jornada;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import javax.swing.*;
import java.time.LocalDate;

public class JornadaDAO {

    private final EntityManager em; // Administrador de entidades para gestionar la conexión con la base de datos
    private final EntityTransaction t; // Transacción para gestionar las operaciones en la base de datos

    /**
     * Constructor de la clase `JornadaDAO`.
     *
     * @param em El administrador de entidades.
     * @param t La transacción para las operaciones.
     */
    public JornadaDAO(EntityManager em, EntityTransaction t) {
        this.em = em;
        this.t = t;
    }


    public void crearJornada(LocalDate fecha, short numeroJornada) {

        t.begin();
        Jornada j = new Jornada();
        j.setFecha(fecha);
        j.setId(null);
        j.setNumeroJornada(numeroJornada);
        em.persist(j);
        t.commit();

    }

    public Jornada obtenerJornadaPorFecha(LocalDate fecha) {
        try {
        t.begin();
        Jornada jornada = em.createQuery("SELECT j FROM Jornada j WHERE j.fecha = :fecha", Jornada.class)
                .setParameter("fecha", fecha)
                .getSingleResult();
        t.commit();
        return jornada;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la jornada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            t.rollback();
            return null;
        }
    }
}
