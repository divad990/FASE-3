package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "ENFRENTAMIENTO")
public class Enfrentamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "incremental_seq")
    @SequenceGenerator(name = "incremental_seq", sequenceName = "INCREMENTAL_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_JORNADA", nullable = false)
    private Model.Jornada idJornada;

    @Column(name = "FECHA_HORA", nullable = false)
    private LocalDateTime fechaHora;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Model.Jornada getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Model.Jornada idJornada) {
        this.idJornada = idJornada;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

}