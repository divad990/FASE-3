package Controller;

import Model.DAO.EnfrentamientoDAO;
import Model.DAO.JornadaDAO;
import Model.Enfrentamiento;
import Model.Jornada;
import Utils.BaseDatos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class EnfrentamientoController {

    private final EnfrentamientoDAO enfrentamientoDAO;
    private final ModeloController modeloController;
    public EnfrentamientoController(ModeloController modeloController) {
        this.modeloController = modeloController;
        this.enfrentamientoDAO = new EnfrentamientoDAO(BaseDatos.getEm(), BaseDatos.getT());
    }

    public void crearEnfrentamientos(ArrayList<Integer> enfrentamientos, ArrayList<LocalDate> fechas){

        LocalTime horaInicio = LocalTime.of(10, 0); // Hora inicial: 10:00
        LocalTime horaFinal = LocalTime.of(22, 0); // Hora final: 22:00
        int partidosPorJornada = enfrentamientos.size() / (2 * fechas.size()); // n/2 partidos por jornada

        for (LocalDate fecha : fechas) {
            Jornada jornada = modeloController.obtenerJornadaPorFecha(fecha);
            LocalTime horaActual = horaInicio;

            for (int j = 0; j < partidosPorJornada; j++) {
                if (horaActual.isAfter(horaFinal)) {
                    throw new IllegalStateException("No hay suficiente tiempo en la franja horaria para todos los partidos.");
                }

                // Combinar fecha y hora en un LocalDateTime
                LocalDateTime fechaHora = LocalDateTime.of(fecha, horaActual);

                // Persistir el enfrentamiento
                enfrentamientoDAO.crearEnfrentamiento(jornada, fechaHora);
                modeloController.crearPartido(fechaHora,enfrentamientos.get(j * 2), enfrentamientos.get(j * 2 + 1));

                // Incrementar la hora para el siguiente partido
                horaActual = horaActual.plusHours(2);
            }
        }
    }

    public Enfrentamiento obtenerEnfrentamientoPorFechaHora(LocalDateTime fechaHora) {
        return enfrentamientoDAO.obtenerEnfrentamientoPorFechaHora(fechaHora);
    }
}
