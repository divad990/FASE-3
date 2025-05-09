package View;

import Controller.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase `VentanaInicio` que representa la ventana inicial de la aplicación.
 * Proporciona botones para acceder a diferentes funcionalidades, como el inicio de sesión.
 */
public class VentanaInicio extends JFrame {

    private JPanel panelInicio; // Panel principal de la ventana
    private JButton botonAcesso; // Botón para acceder a la funcionalidad principal
    private JButton botonImagen; // Botón relacionado con la imagen (sin funcionalidad definida)
    private JPanel panelImagen; // Panel para mostrar una imagen
    private JPanel panelAcesso; // Panel para gestionar el acceso
    private JButton accesologin; // Botón para iniciar sesión (sin funcionalidad definida)

    /**
     * Constructor de la clase `VentanaInicio`.
     * Inicializa la ventana y configura los botones con sus respectivos `ActionListeners`.
     *
     * @param vistaController El controlador de la vista que gestiona las acciones de los botones.
     */
    public VentanaInicio(VistaController vistaController) {
        // Controlador de la vista para gestionar las acciones
        setContentPane(panelInicio); // Establece el panel principal de contenido
        setSize(500, 500); // Establece el tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura la operación de cierre de la ventana

        // Configura el `ActionListener` para el botón `botonAcesso`
        botonAcesso.addActionListener(e -> {
            vistaController.mostrarAccesoLogIn(); // Llama al método para mostrar el acceso al inicio de sesión
        });
    }

}