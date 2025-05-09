package Model.DAO;

import Model.Enfrentamiento;
import Model.Jornada;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;

public class EnfrentamientoDAO {

    private final EntityManager em;
    private final EntityTransaction t;

    public EnfrentamientoDAO(EntityManager em, EntityTransaction t) {
        this.em = em;
        this.t = t;
    }

    public void crearEnfrentamiento(Jornada jornada, LocalDateTime fecha_hora) {

        t.begin();
        Enfrentamiento enfrentamiento = new Enfrentamiento();
        enfrentamiento.setId(null);
        enfrentamiento.setIdJornada(jornada);
        enfrentamiento.setFechaHora(fecha_hora);
        em.persist(enfrentamiento);
        t.commit();

    }

    public Enfrentamiento obtenerEnfrentamientoPorFechaHora(LocalDateTime fecha_hora) {
        t.begin();
        Enfrentamiento enfrentamiento = em.createQuery("SELECT e FROM Enfrentamiento e WHERE e.fechaHora = :fecha_hora", Enfrentamiento.class)
                .setParameter("fecha_hora", fecha_hora)
                .getSingleResult();
        t.commit();
        return enfrentamiento;
    }

}
