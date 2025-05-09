package View;

import Controller.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase `VentanaLogIn` que representa una ventana de diálogo para el inicio de sesión.
 * Permite a los usuarios ingresar su nombre de usuario y contraseña para acceder a la aplicación.
 */
public class VentanaLogIn extends JDialog {

    private VistaController vistaController; // Controlador de la vista para gestionar las acciones
    private JPanel panelPricipal; // Panel principal de la ventana
    private JButton imagenLogIn; // Botón relacionado con la imagen de inicio de sesión (sin funcionalidad definida)
    private JButton logInButton; // Botón para realizar el inicio de sesión
    private JPanel panelUsuarioContrasenia; // Panel que contiene los campos de usuario y contraseña
    private JPanel panerArriba; // Panel superior de la ventana
    private JPanel panelAbajo; // Panel inferior de la ventana
    private JPasswordField passwordLogIn;
    private JTextField usuarioField;
    private JPasswordField passwordField1;
    private JTextField contaseniaField;

    /**
     * Constructor de la clase `VentanaLogIn`.
     * Inicializa la ventana de inicio de sesión y configura el botón de inicio de sesión con su `ActionListener`.
     *
     * @param vistaController El controlador de la vista que gestiona las acciones de la ventana.
     */
    public VentanaLogIn(VistaController vistaController) {
        this.vistaController = vistaController;
        setContentPane(panelPricipal); // Establece el panel principal de contenido
        setModal(true); // Configura la ventana como modal
        setSize(400, 400); // Establece el tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Configura el `ActionListener` para el botón de inicio de sesión
        logInButton.addActionListener(e -> {
            todoCorrecto(); // Llama al método para validar las credenciales
        });
    }

    /**
     * Método privado `todoCorrecto` que valida las credenciales ingresadas por el usuario.
     * Verifica si el nombre de usuario y la contraseña son correctos y, en caso contrario, muestra un mensaje de error.
     */
    private void todoCorrecto() {
        try {
            String username = usuarioField.getText(); // Obtiene el nombre de usuario ingresado
            boolean encontrado = vistaController.buscarUsuario(username); // Verifica si el usuario existe
            if (!encontrado) {
                throw new Exception("Usuario no encontrado"); // Lanza una excepción si el usuario no existe
            }

            char[] passwordChars = passwordField1.getPassword();
            String password = new String(passwordChars);
            boolean encontradoContrasenia = vistaController.buscarUsuarioContrasenia(password);
            if(!encontradoContrasenia){
                throw new Exception("Contraseña no encontradada,");
            }
            boolean esAdmin = vistaController.buscarUsuario(username);
            vistaController.iniciarMenuOpciones();
            dispose();
        }catch(Exception e){
            // Muestra un mensaje de error y limpia los campos de texto
            JOptionPane.showMessageDialog(null, "error, contraseña o usuario incorrecto");
            usuarioField.requestFocus();
            usuarioField.setText("");
            passwordField1.setText("");
        }
    }
}