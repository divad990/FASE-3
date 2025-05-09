package Controller;

import Model.*;
import Model.DAO.CompeticionDAO;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase `ModeloController` que actúa como controlador principal para gestionar la lógica de negocio.
 * Coordina las operaciones entre los controladores de usuario, jugador y equipo.
 */
public class ModeloController {

    private Usuario usuario; // Usuario actualmente autenticado
    private final UsuarioController usuarioController;
    private VistaController vistaController;
    private final JugadorController jugadorController;
    private final EquipoController equipoController;
    private final JornadaController jornadaController;
    private final EnfrentamientoController enfrentamientoController;
    private final PartidoController partidoController;
    private final CompeticionController competicionController;

    /**
     * Constructor de la clase `ModeloController`.
     * Inicializa los controladores de usuario, jugador y equipo.
     */
    public ModeloController() {
        this.competicionController = new CompeticionController();
        this.usuarioController = new UsuarioController();
        this.equipoController = new EquipoController();
        this.jugadorController = new JugadorController();
        this.jornadaController = new JornadaController(this);
        this.enfrentamientoController = new EnfrentamientoController(this);
        this.partidoController = new PartidoController(this);
    }

    /**
     * Obtiene el controlador de vistas.
     *
     * @return El controlador de vistas.
     */
    public VistaController getVistaController() {
        return vistaController;
    }

    /**
     * Establece el controlador de vistas.
     *
     * @param vistaController El controlador de vistas a establecer.
     */
    public void setVistaController(VistaController vistaController) {
        this.vistaController = vistaController;
    }

    // CRUD JUGADORES

    /**
     * Válida si un DNI ya existe en el sistema.
     *
     * @param dni El DNI a validar.
     * @return `true` si el DNI ya existe, `false` en caso contrario.
     */
    public boolean validarDni(String dni) {
        return jugadorController.validarDni(dni);
    }

    /**
     * Válida si un nickname ya está en uso.
     *
     * @param nickname El nickname a validar.
     * @return `true` si el nickname ya está en uso, `false` en caso contrario.
     */
    public boolean validarNickname(String nickname) {
        return jugadorController.validarNickname(nickname);
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
     * @param equipo El nombre del equipo al que pertenece el jugador.
     */
    public void crearJugador(String dni, String nombre, String apellido, String nickname, String nacionalidad, String rol, java.sql.Date fechaNacimiento, BigDecimal sueldo, String equipo) {
        Equipo equipoE = new Equipo();
        if (!equipo.isEmpty()) {
            equipoE = equipoController.obtenerEquipo(equipo);
        }
        jugadorController.crearJugador(dni, nombre, apellido, nickname, nacionalidad, rol, fechaNacimiento, sueldo, equipoE);
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
     * @param equipo El nombre del equipo al que pertenece el jugador.
     */
    public void modificarJugador(String dni, String nombre, String apellido, String nickname, String nacionalidad, String rol, java.sql.Date fechaNacimiento, BigDecimal sueldo, String equipo) {
        Equipo equipoE = new Equipo();
        if (!equipo.isEmpty()) {
            equipoE = equipoController.obtenerEquipo(equipo);
        }
        jugadorController.modificarJugador(dni, nombre, apellido, nickname, nacionalidad, rol, fechaNacimiento, sueldo, equipoE);
    }

    /**
     * Elimina un jugador del sistema.
     *
     * @param dni El DNI del jugador a eliminar.
     */
    public void eliminarJugador(String dni) {
        jugadorController.eliminarJugador(dni);
    }

    /**
     * Obtiene una lista de nombres de equipos disponibles.
     *
     * @return Una lista de nombres de equipos.
     */
    public List<String> obtenerEquipos() {
        return equipoController.obtenerEquipos();
    }

    /**
     * Obtiene un jugador por su NIF.
     *
     * @param nif El NIF del jugador.
     * @return El jugador correspondiente.
     */
    public Jugador obtenerJugador(String nif) {
        return jugadorController.obtenerJugador(nif);
    }

    // CRUD EQUIPOS

    public void mostrarEquipo(String nombre){
        equipoController.mostrarEquipo(nombre);
    }

    public void insertarEquipo(String nombre, LocalDate fecha){
        equipoController.insertarEquipo(nombre,fecha);
    }

    public void modificarEquipo(String nombre, LocalDate fecha){
    }

    // LOGIN

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username El nombre de usuario a buscar.
     * @return `true` si el usuario existe, `false` en caso contrario.
     * @throws Exception Sí ocurre un error durante la búsqueda.
     */
    public boolean buscarUsuario(String username) throws Exception {
        boolean encontrado = false;
        usuario = usuarioController.buscarUsuario(username);
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
        } else {
            encontrado = true;
        }
        return encontrado;
    }

    public boolean buscarEquipo(String nombre) throws Exception {
        boolean encontrado = false;
        Equipo equipo = equipoController.obtenerEquipo(nombre);
        if (equipo == null) {
            JOptionPane.showMessageDialog(null, "Equipo no encontrado");
        } else {
            encontrado = true;
        }
        return encontrado;
    }

    public void eliminarEquipo(String nombre) throws Exception {
        equipoController.eliminarEquipo(nombre);
    }

    /**
     * Válida si la contraseña proporcionada coincide con la del usuario autenticado.
     *
     * @param password La contraseña a validar.
     * @return `true` si la contraseña es válida, `false` en caso contrario.
     */
    public boolean buscarUsuarioContrasenia(String password) {
        return password.equals(usuario.getPasswd());
    }

    public boolean esAdmin(){
        return usuario.getAdmin();
    }

    // GENERAR JORNADAS

    public void comprobarRestricciones() {
        // Comprobar equipos par
        if (equipoController.comprobarEquiposPar()) {
        // Comprobar equipos completos
            // Coger todos los equipos
            List<String> equipos = equipoController.obtenerEquipos();
            // Un for para comprobar si cada equipo tiene exactamente 6 jugadores
            boolean completo = true;
            for (String equipo : equipos) {
                // Obtener todos los jugadores del equipo
                List<Jugador> jugadores = jugadorController.obtenerJugadoresEquipo(equipoController.obtenerEquipo(equipo));
                if (jugadores.size() != 6) {
                    // Mostrar mensaje de error si el equipo no tiene 6 jugadores
                    JOptionPane.showMessageDialog(null, "El equipo " + equipo + " no tiene 6 jugadores. \n" +
                            "Por favor, añade jugadores para continuar.");
                    completo = false;
                    break;
                }
            }
            // Si todos los equipos tienen 6 jugadores, continuar
            if (completo) {
                JOptionPane.showMessageDialog(null, "Todos los equipos tienen 6 jugadores. \n" +
                        "Iniciando generación de jornadas...");
                jornadaController.generarJornadas(equipoController.obtenerEquiposCompleto());
            }
            //competicionController.cerrarInscripcion();
        } else {
            JOptionPane.showMessageDialog(null, "El número de equipos es impar y no se puede continuar. \n" +
                    "Por favor, añade un equipo más para continuar.");
        }

    }

    public void crearEnfrentamientos(ArrayList<Integer> enfrentamientos, ArrayList<LocalDate> fechas) {
        enfrentamientoController.crearEnfrentamientos(enfrentamientos, fechas);
    }

    public Jornada obtenerJornadaPorFecha(LocalDate fecha) {
        return jornadaController.obtenerJornadaPorFecha(fecha);
    }

    public void crearPartido(LocalDateTime fechaHora, Integer e1O, Integer e2O) {

        Equipo e1 = equipoController.obtenerEquipoPorId(e1O);
        Equipo e2 = equipoController.obtenerEquipoPorId(e2O);

        Enfrentamiento e = enfrentamientoController.obtenerEnfrentamientoPorFechaHora(fechaHora);
        partidoController.crearPartido(e1,e2,e);
    }

}