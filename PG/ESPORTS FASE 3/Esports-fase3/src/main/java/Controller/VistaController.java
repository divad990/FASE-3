package Controller;

import Model.Jugador;
import View.*;
import Exceptions.CampoVacioException;
import Utils.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Clase `VistaController` que actúa como controlador principal para gestionar la lógica de la aplicación.
 * Maneja la interacción entre las vistas y el modelo.
 */
public class VistaController {

    private final ModeloController modeloController;
    private VentanaInicio vInicio; // Vista de inicio

    /**
     * Constructor de la clase `VistaController`.
     * Inicializa el controlador del modelo.
     */
    public VistaController() {
        this.modeloController = new ModeloController();
    }

    /**
     * Valida un dato utilizando una expresión regular.
     *
     * @param dato El dato a validar.
     * @param expresionRegular La expresión regular para la validación.
     * @return `true` si el dato es válido, `false` en caso contrario.
     * @throws CampoVacioException Si el campo está vacío.
     */
    public boolean validarDato(String dato, String expresionRegular) throws CampoVacioException {
        return Utilidades.validarDato(dato, expresionRegular);
    }

    // CRUD JUGADORES

    /**
     * Inicia la gestión de jugadores mostrando la ventana correspondiente.
     */
    public void iniciarGestionJugadores() {
        JugadoresGestion gestionJugadores = new JugadoresGestion(this);
        gestionJugadores.setVisible(true); //Hace visible la ventana
        gestionJugadores.setLocationRelativeTo(null); //Centra la ventana
    }
    /**
     * Inicia la gestión de jugadores mostrando la ventana correspondiente.
     */
    public void iniciarGestionEquipos() {
        EquiposGestion equiposGestion = new EquiposGestion(this);
        equiposGestion.setVisible(true); //Hace visible la ventana
        equiposGestion.setLocationRelativeTo(null); //Centra la ventana
    }

    /**
     * Válida si un DNI ya existe en el sistema.
     *
     * @param dni El DNI a validar.
     * @return `true` si el DNI ya existe, `false` en caso contrario.
     */
    public boolean validarDni(String dni) {
        return modeloController.validarDni(dni);
    }

    /**
     * Válida si un nickname ya está en uso.
     *
     * @param nickname El nickname a validar.
     * @return `true` si el nickname ya está en uso, `false` en caso contrario.
     */
    public boolean validarNickname(String nickname) {
        return modeloController.validarNickname(nickname);
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
     * @param nacimiento La fecha de nacimiento del jugador.
     * @param sueldo El sueldo del jugador.
     * @param equipo El equipo al que pertenece el jugador.
     */
    public void crearJugador(String dni, String nombre, String apellido, String nickname, String nacionalidad, String rol, String nacimiento, String sueldo, String equipo) {
        BigDecimal sueldoInt = BigDecimal.valueOf(Integer.parseInt(sueldo));
        Date fechaNacimiento = fechaOracle(nacimiento);
        modeloController.crearJugador(dni, nombre, apellido, nickname, nacionalidad, rol, fechaNacimiento, sueldoInt, equipo);
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
     * @param nacimiento La fecha de nacimiento del jugador.
     * @param sueldo El sueldo del jugador.
     * @param equipo El equipo al que pertenece el jugador.
     */
    public void modificarJugador(String dni, String nombre, String apellido, String nickname, String nacionalidad, String rol, String nacimiento, String sueldo, String equipo) {
        BigDecimal sueldoInt = null;
        Date fechaNacimiento = null;
        if (!Objects.equals(sueldo, "")) {
            sueldoInt = BigDecimal.valueOf(Integer.parseInt(sueldo));
        }
        if (!Objects.equals(nacimiento, "")) {
            fechaNacimiento = fechaOracle(nacimiento);
        }
        modeloController.modificarJugador(dni, nombre, apellido, nickname, nacionalidad, rol, fechaNacimiento, sueldoInt, equipo);
    }

    /**
     * Elimina un jugador del sistema.
     *
     * @param dni El DNI del jugador a eliminar.
     */
    public void eliminarJugador(String dni) {
        modeloController.eliminarJugador(dni);
    }

    /**
     * Obtiene un jugador por su NIF.
     *
     * @param nif El NIF del jugador.
     * @return El jugador correspondiente.
     */
    public Jugador obtenerJugador(String nif) {
        return modeloController.obtenerJugador(nif);
    }

    /**
     * Obtiene una lista de equipos disponibles.
     *
     * @return Una lista de nombres de equipos.
     */
    public List<String> obtenerEquipos() {
        return modeloController.obtenerEquipos();
    }

    // INICIAR VISTAS

    /**
     * Inicia el menú de opciones mostrando la ventana correspondiente.
     */
    public void iniciarMenuOpciones() {
        MenuOpciones menuOpciones = new MenuOpciones(this);
        menuOpciones.setVisible(true); // Hace visible la ventana
        menuOpciones.setLocationRelativeTo(null); // Centra la ventana
    }

    /**
     * Muestra la vista de inicio de sesión.
     */
    public void mostrarAccesoLogIn() {
        // Vista de inicio de sesión
        VentanaLogIn vLogin = new VentanaLogIn(this);
        vLogin.setVisible(true);
        vInicio.dispose();
    }

    /**
     * Inicia la vista principal de la aplicación.
     */
    public void iniciarPrincipal() {
        vInicio = new VentanaInicio(this);
        vInicio.setVisible(true);
    }

    /**
     * Inicia la vista para mostrar un jugador específico.
     *
     * @param nif El NIF del jugador a mostrar.
     */
    public void iniciarJugador(String nif) {
        MostrarJugador jugador = new MostrarJugador(this, nif);
        jugador.setVisible(true);
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
        return modeloController.buscarUsuario(username);
    }

    /**
     * Busca un equipo por su nombre de equipo.
     */
    public boolean buscarEquipo(String nombre) throws Exception {
        return modeloController.buscarEquipo(nombre);
    }

    public void eliminarEquipo(String nombre) throws Exception {
        modeloController.eliminarEquipo(nombre);
    }

    /**
     * Busca un usuario por su contraseña.
     *
     * @param password La contraseña del usuario.
     * @return `true` si la contraseña es válida, `false` en caso contrario.
     */
    public boolean buscarUsuarioContrasenia(String password){
        return modeloController.buscarUsuarioContrasenia(password);
    }

    public boolean esAdmin() {
        return modeloController.esAdmin();
    }

    // GENERAR JORNADAS
    public void comprobarRestricciones() {
        modeloController.comprobarRestricciones();
    }

    // FUNCIONES COMPLEMENTARIAS

    /**
     * Convierte una fecha en formato `String` a un objeto `Date` compatible con Oracle.
     *
     * @param fecha La fecha en formato `dd/MM/yyyy`.
     * @return La fecha convertida a un objeto `Date`.
     */
    public Date fechaOracle(String fecha) {
        String[] fechaSplit = fecha.split("/");
        int dia = Integer.parseInt(fechaSplit[0]);
        int mes = Integer.parseInt(fechaSplit[1]);
        int anio = Integer.parseInt(fechaSplit[2]);

        LocalDate fechaLocalDate = LocalDate.of(anio, mes, dia);
        return Date.valueOf(fechaLocalDate);
    }
    public void insertarEquipo(String nombre,LocalDate fecha){
        modeloController.insertarEquipo(nombre,fecha);
    }
    public void mostrarEquipo(String nombre) {
        modeloController.mostrarEquipo(nombre);
    }
    public void modificarEquipo(String nombre,LocalDate fecha){
        modeloController.modificarEquipo(nombre,fecha);

    }
}