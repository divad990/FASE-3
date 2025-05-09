package View;

import Controller.VistaController;
import Model.Jugador;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Clase `MostrarJugador` que representa una ventana para mostrar la información de un jugador.
 * Proporciona detalles como nombre, apellido, DNI, rol, nickname, nacionalidad, fecha de nacimiento, sueldo y equipo.
 */
public class MostrarJugador extends JFrame {
    private JPanel pPrincipal; // Panel principal de la ventana
    private JLabel fnNombre; // Etiqueta para mostrar el nombre del jugador
    private JLabel fnApellido; // Etiqueta para mostrar el apellido del jugador
    private JLabel fnDni; // Etiqueta para mostrar el DNI del jugador
    private JLabel fnRol; // Etiqueta para mostrar el rol del jugador
    private JLabel fnNickname; // Etiqueta para mostrar el nickname del jugador
    private JLabel fnNacionalidad; // Etiqueta para mostrar la nacionalidad del jugador
    private JLabel fnFechaNacimienito; // Etiqueta para mostrar la fecha de nacimiento del jugador
    private JLabel fnSueldo; // Etiqueta para mostrar el sueldo del jugador
    private JLabel fnEquipo; // Etiqueta para mostrar el equipo del jugador

    /**
     * Constructor de la clase `MostrarJugador`.
     * Inicializa la ventana y muestra la información del jugador obtenido a través del controlador.
     *
     * @param vistaController El controlador de la vista que gestiona las acciones.
     * @param nif El NIF del jugador cuya información se mostrará.
     */
    public MostrarJugador(VistaController vistaController, String nif) {
        // Controlador de la vista para gestionar las acciones
        // NIF del jugador a mostrar

        setContentPane(pPrincipal); // Establece el panel principal de contenido
        setSize(600, 600); // Establece el tamaño de la ventana
        setTitle("Gestión de Jugadores"); // Establece el título de la ventana
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // Configura la operación de cierre de la ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel(); // Llama al método `onCancel` al intentar cerrar la ventana
            }
        });

        // Obtiene el jugador a través del controlador y establece los valores en las etiquetas
        Jugador j = vistaController.obtenerJugador(nif);
        fnNombre.setText(j.getNombre());
        fnApellido.setText(j.getApellido());
        fnDni.setText(j.getDni());
        fnRol.setText(j.getRol());
        fnNickname.setText(j.getNickname());
        fnNacionalidad.setText(j.getNacionalidad());
        fnFechaNacimienito.setText(j.getFechaNacimiento().toString());
        fnSueldo.setText(String.valueOf(j.getSueldo()));
        String equipo = j.getIdEquipo().getNombre();
        fnEquipo.setText(equipo);
    }

    /**
     * Método que se ejecuta al intentar cerrar la ventana.
     * Libera los recursos y cierra la ventana.
     */
    public void onCancel() {
        dispose();
    }

}