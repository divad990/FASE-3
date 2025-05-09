package Controller;

import Model.DAO.JugadorDAO;
import Model.Equipo;
import Model.Jugador;
import Utils.BaseDatos;

import java.math.BigDecimal;
import java.util.List;

/**
 * Clase `JugadorController` que actúa como controlador para gestionar las operaciones relacionadas con los jugadores.
 * Se comunica con el DAO para realizar operaciones CRUD sobre los jugadores.
 */
public class JugadorController {

    private final JugadorDAO jugadorDAO; // DAO para interactuar con la base de datos de jugadores

    /**
     * Constructor de la clase `JugadorController`.
     * Inicializa el DAO de jugadores con la conexión a la base de datos.
     */
    public JugadorController() {
        this.jugadorDAO = new JugadorDAO(BaseDatos.getEm(), BaseDatos.getT());
    }

    /**
     * Válida si un DNI ya existe en el sistema.
     *
     * @param dni El DNI a validar.
     * @return `true` si el DNI ya existe, `false` en caso contrario.
     */
    public boolean validarDni(String dni) {
        return jugadorDAO.validarDni(dni);
    }

    /**
     * Válida si un nickname ya está en uso.
     *
     * @param nombre El nickname a validar.
     * @return `true` si el nickname ya está en uso, `false` en caso contrario.
     */
    public boolean validarNickname(String nombre) {
        return jugadorDAO.validarNickname(nombre);
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
        jugadorDAO.crearJugador(dni, nombre, apellido, nickname, nacionalidad, rol, fechaNacimiento, sueldo, equipo);
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
        jugadorDAO.modificarJugador(dni, nombre, apellido, nickname, nacionalidad, rol, fechaNacimiento, sueldo, equipo);
    }

    /**
     * Elimina un jugador del sistema.
     *
     * @param dni El DNI del jugador a eliminar.
     */
    public void eliminarJugador(String dni) {
        jugadorDAO.eliminarJugador(dni);
    }

    /**
     * Obtiene un jugador por su NIF.
     *
     * @param nif El NIF del jugador.
     * @return El jugador correspondiente.
     */
    public Jugador obtenerJugador(String nif) {
        return jugadorDAO.obtenerJugador(nif);
    }

    public List<Jugador> obtenerJugadoresEquipo(Equipo equipo) {
        return jugadorDAO.obtenerJugadoresEquipo(equipo);
    }
}