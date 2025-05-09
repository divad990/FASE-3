package Controller;

import Model.DAO.EquipoDAO;
import Model.DAO.JornadaDAO;
import Model.Equipo;
import Model.Jornada;
import Utils.BaseDatos;

import javax.swing.*;
import java.sql.SQLOutput;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Controlador para la generación de jornadas de enfrentamientos entre equipos.
 */
public class JornadaController {

    private final JornadaDAO jornadaDAO;
    private final ModeloController modeloController;
    public JornadaController(ModeloController modeloController) {
        this.modeloController = modeloController;
        this.jornadaDAO = new JornadaDAO(BaseDatos.getEm(), BaseDatos.getT());
    }

    /**
     * Genera las jornadas de enfrentamientos entre los equipos proporcionados.
     *
     * @param equiposO Lista de equipos inicial. Se espera que sea mutable y contenga
     *                 instancias de la clase {@link Equipo}.
     * @throws NoSuchElementException Si se intenta acceder o eliminar elementos de una lista vacía.
     */

    public void generarJornadas(List<Equipo> equiposO) {
        StringBuilder sb = new StringBuilder(); // StringBuilder para almacenar visualmente enfrenatmientos
        ArrayList<Integer> enfrentamientos = new ArrayList<>(); // ArrayList para almacenar los enfrentameintos con id
        LinkedList<Equipo> equipos; // LinkedList para almacenar los equipos de la jornada actual
        ArrayList<LocalDate> fechas = new ArrayList<>(); // ArrayList para almacenar las fechas de cada jornada
        LocalDate fechaInicial = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)); // Fecha inicial para la jornada

        // Generar todos los enfrentamientos necesarios
        boolean correcto = false;
        int n1 = equiposO.size() - 1;
        // Repetir el proceso tantas veces como jornadas hay
        do {
            equipos = new LinkedList<>(equiposO);
            fechas.add(fechaInicial);
            // Repetir el proceso tantas veces como enfrentamientos hay
            sb.append("Jornada ").append(n1).append("\n");
            do {
                Equipo e1 = equipos.getFirst();
                sb.append("Enfrentamiento 1: " + e1.getNombre() + " vs " + equipos.getLast().getNombre());
                enfrentamientos.add(e1.getId());
                enfrentamientos.add(equipos.getLast().getId());
                sb.append("\n");
                equipos.removeFirst();
                equipos.removeLast();

                int enfrentamiento = 1;

                while (!equipos.isEmpty()) {
                    enfrentamiento++;
                    sb.append("Enfrentamiento ").append(enfrentamiento).append(": ").append(equipos.getFirst().getNombre()).append(" vs ").append(equipos.getLast().getNombre());
                    enfrentamientos.add(equipos.getFirst().getId());
                    enfrentamientos.add(equipos.getLast().getId());
                    sb.append("\n");
                    equipos.removeFirst();
                    equipos.removeLast();
                }
                correcto = true;
            } while (!correcto);
            Equipo ultimo = equiposO.removeLast();
            equiposO.addFirst(ultimo);
            fechaInicial = fechaInicial.plusWeeks(1);
            n1--;
        } while (n1 != 0);

        // Crear un mensaje para mostrar los enfrentamientos y las fechas
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Enfrentamientos por jornada:\n\n");
        for (int i = 0; i < fechas.size(); i++) {
            mensaje.append("Jornada ").append(i + 1).append(" - Fecha: ").append(fechas.get(i)).append("\n");
        }
        mensaje.append("\n").append(sb.toString()); // Añadir los enfrentamientos

        // Mostrar el mensaje en un JOptionPane
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Visualización de Jornadas", JOptionPane.INFORMATION_MESSAGE);

        // Meter datos en base de datos

        crearJornada(fechas);
        modeloController.crearEnfrentamientos(enfrentamientos, fechas);
    }

    /**
     * Crea una jornada en la base de datos.
     */
    public void crearJornada(ArrayList<LocalDate> fechas) {
        int num = 0;
        for (LocalDate fecha : fechas) {
            try {
                num++;
                short nums = (short) num;
                jornadaDAO.crearJornada(fecha, nums);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al crear la jornada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * Obtiene una jornada por su fecha.
     *
     * @param fecha La fecha de la jornada a buscar.
     * @return La jornada correspondiente a la fecha proporcionada.
     */
    public Jornada obtenerJornadaPorFecha(LocalDate fecha) {
        return jornadaDAO.obtenerJornadaPorFecha(fecha);
    }
}
