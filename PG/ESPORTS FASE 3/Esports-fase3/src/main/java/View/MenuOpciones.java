package View;

import Controller.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase `MenuOpciones` que representa la ventana principal del menú de opciones de la aplicación.
 * Proporciona botones para acceder a diferentes funcionalidades de la aplicación.
 */
public class MenuOpciones extends JFrame {

    private JPanel contentPane; // Panel principal de contenido
    private JButton generarCalendarioDeCompeticionButton; // Botón para generar el calendario de competición
    private JButton cerrarEtapaDeInscripcionButton; // Botón para cerrar la etapa de inscripción
    private JButton gestionDeJugadoresButton; // Botón para gestionar jugadores
    private JButton gestionDeEquiposButton; // Botón para gestionar equipos
    private JButton introducirResultadosButton; // Botón para introducir resultados
    private JButton verInformesButton; // Botón para ver informes
    private JLabel tfAdmin;
    private JLabel tfUser;

    /**
     * Constructor de la clase `MenuOpciones`.
     * Inicializa la ventana y configura los botones con sus respectivos `ActionListeners`.
     *
     * @param vistaController El controlador de la vista que gestiona las acciones de los botones.
     */
    public MenuOpciones(VistaController vistaController) {
        // Controlador de la vista para gestionar las acciones

        setContentPane(contentPane); // Establece el panel principal de contenido
        setTitle("Menú de Opciones"); // Establece el título de la ventana
        setSize(500, 400); // Establece el tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Configura la operación de cierre de la ventana

        if (!vistaController.esAdmin()) {
            gestionDeEquiposButton.setVisible(false);
            gestionDeJugadoresButton.setVisible(false);
            introducirResultadosButton.setVisible(false);
            generarCalendarioDeCompeticionButton.setVisible(false);
            cerrarEtapaDeInscripcionButton.setVisible(false);
            verInformesButton.setVisible(true);
            tfAdmin.setVisible(false);
        } else {
            tfUser.setVisible(false);
        }

        // ActionListeners para cada botón (excepto OK y Cancel)
        generarCalendarioDeCompeticionButton.addActionListener(e -> {
            // Acción para generar el calendario de competición
            // Comprobar restricciones
            vistaController.comprobarRestricciones();
            // Generar calendario
        });

        cerrarEtapaDeInscripcionButton.addActionListener(e -> {
            // Acción para cerrar la etapa de inscripción
        });

        gestionDeJugadoresButton.addActionListener(e -> {
            dispose(); // Cierra la ventana actual
            vistaController.iniciarGestionJugadores(); // Inicia la gestión de jugadores
        });

        gestionDeEquiposButton.addActionListener(e -> {
            dispose();
            vistaController.iniciarGestionEquipos();
        });

        introducirResultadosButton.addActionListener(e -> {
            // Acción para introducir resultados
        });
        cerrarEtapaDeInscripcionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarEtapaInscripcion();
            }
        });

        verInformesButton.addActionListener(e -> {
            // Acción para ver informes
        });
    }

    public void cerrarEtapaInscripcion(){
    }
}