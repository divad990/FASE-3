package Model.DAO;

import Model.Enfrentamiento;
import Model.Equipo;
import Model.Partido;
import Model.PartidoId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PartidoDAO {

    private final EntityManager em;
    private final EntityTransaction t;

    public PartidoDAO(EntityManager em, EntityTransaction t) {
        this.em = em;
        this.t = t;
    }

    public void crearPartido(Equipo equipo, Enfrentamiento e) {
        t.begin();
        Partido partido = new Partido();

        // Asegúrate de que los objetos relacionados estén gestionados
        equipo = em.find(Equipo.class, equipo.getId());
        e = em.find(Enfrentamiento.class, e.getId());

        // Configura el identificador compuesto
        PartidoId partidoId = new PartidoId();
        partidoId.setIdEquipo(equipo.getId());
        partidoId.setIdEnfrentamiento(e.getId());
        partido.setId(partidoId);

        // Asigna las relaciones
        partido.setIdEquipo(equipo);
        partido.setIdEnfrentamiento(e);

        // Asigna un valor al campo resultado
        partido.setResultado("N/A");

        em.persist(partido);
        t.commit();
    }

}
