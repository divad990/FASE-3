package Model.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import javax.swing.*;
import java.util.List;

public class CompeticionDAO {

    private final EntityManager em;
    private final EntityTransaction t;

    public CompeticionDAO(EntityManager em, EntityTransaction t) {
        this.em = em;
        this.t = t;
    }
            public List<String> obtenerCompeticiones() {
        try {
            t.begin();
            List<String> competiciones = List.of(em.createQuery("SELECT c.id,c.nombre,c.estado FROM Competicion c", String.class).getResultList().toArray(new String[0]));
            t.commit();
            return competiciones;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener equipos de la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
