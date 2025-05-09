package Controller;

import Model.DAO.EquipoDAO;
import Model.Equipo;
import Utils.BaseDatos;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase `EquipoController` que actúa como controlador para gestionar las operaciones relacionadas con los equipos.
 * Utiliza la clase `EquipoDAO` para interactuar con la base de datos.
 */
public class EquipoController {

    private final EquipoDAO equipoDAO; // Objeto DAO para realizar operaciones con los equipos

    /**
     * Constructor de la clase `EquipoController`.
     * Inicializa el objeto `EquipoDAO` utilizando el administrador de entidades y la transacción de la base de datos.
     */
    public EquipoController() {
        this.equipoDAO = new EquipoDAO(BaseDatos.getEm(), BaseDatos.getT());
    }

    /**
     * Obtiene una lista de nombres de todos los equipos disponibles.
     *
     * @return Una lista de nombres de equipos.
     */
    public List<String> obtenerEquipos() {
        return equipoDAO.obtenerEquipos();
    }

    public List<Equipo> obtenerEquiposCompleto() {
        return equipoDAO.obtenerEquiposCompleto();
    }

    /**
     * Obtiene un equipo específico por su nombre.
     *
     * @param equipo El nombre del equipo a buscar.
     * @return Un objeto `Equipo` que representa el equipo encontrado.
     */
    public Equipo obtenerEquipo(String equipo) {
        return equipoDAO.obtenerEquipo(equipo);
    }


    public boolean comprobarEquiposPar(){
        List<String> equipos = equipoDAO.obtenerEquipos();
        if (equipos.isEmpty()) {
            return false;
        } else return equipos.size() % 2 == 0;
    }

    public Equipo obtenerEquipoPorId(Integer e1O) {
        return equipoDAO.obtenerEquipoPorId(e1O);
    }

    public void eliminarEquipo(String nombre) {
        equipoDAO.eliminarEquipo(nombre);
    }

    public void mostrarEquipo(String nombre) {
       Equipo equipo = equipoDAO.obtenerEquipo(nombre);

        JOptionPane.showMessageDialog(null, "NOMBRE : " +equipo.getNombre()+"\n"+ "FECHA-FUNDACION : "+equipo.getFechaFundacion());
    }

    public void insertarEquipo(String nombre, LocalDate fecha) {
        equipoDAO.crearEquipo(nombre, fecha);
    }

    public void modificarEquipo(String nombre, LocalDate fecha) {
        equipoDAO.modificarEquipo(nombre,fecha);

    }
}