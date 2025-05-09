package View;

import Controller.VistaController;
import Exceptions.CampoVacioException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase `JugadoresGestion` que representa una ventana para la gestión de jugadores.
 * Permite crear, modificar, eliminar y mostrar jugadores mediante una interfaz gráfica.
 */
public class JugadoresGestion extends JFrame {
    // Componentes de la interfaz gráfica
    private JComboBox cbOpciones; // ComboBox para seleccionar la acción a realizar
    private JTextField tfDni; // Campo de texto para el DNI del jugador
    private JTextField tfNombre; // Campo de texto para el nombre del jugador
    private JTextField tfNickname; // Campo de texto para el nickname del jugador
    private JTextField tfNacionalidad; // Campo de texto para la nacionalidad del jugador
    private JTextField tfApellido; // Campo de texto para el apellido del jugador
    private JTextField tfFechaNacimiento; // Campo de texto para la fecha de nacimiento del jugador
    private JTextField tfSueldo; // Campo de texto para el sueldo del jugador
    private JButton bAceptar; // Botón para aceptar la acción seleccionada
    private JButton bCancelar; // Botón para cancelar la acción
    private JPanel pPrincipal; // Panel principal de la ventana
    private JComboBox cmRol; // ComboBox para seleccionar el rol del jugador
    private JLabel a1, a2, a3, a4, a5, a7, a8; // Etiquetas de advertencia para errores de validación
    private JLabel e1, e2, e3, e4, e5, e6, e7, e8; // Etiquetas de error para campos vacíos
    private JLabel jlDni; // Etiqueta para el campo DNI
    private JPanel pDatos; // Panel para los datos del jugador
    private JPanel pDni; // Panel para el campo DNI
    private JComboBox cbEquipo; // ComboBox para seleccionar el equipo del jugador
    private String nif; // Variable para almacenar el NIF del jugador

    private VistaController vistaController; // Controlador para manejar la lógica de la vista

    private JLabel[] labels = {a1, a2, a3, a4, a5, a7, a8, e1, e2, e3, e4, e5, e6, e7, e8}; // Array de etiquetas de error
    private boolean camposCompletos = false; // Indicador de sí los campos están completos

    /**
     * Constructor de la clase `JugadoresGestion`.
     * Inicializa los componentes de la interfaz y configura los eventos.
     *
     * @param vistaController Controlador de la vista para manejar la lógica de negocio.
     */
    public JugadoresGestion(VistaController vistaController) {
        this.vistaController = vistaController;

        // Ocultar todas las etiquetas de error al inicio
        for (JLabel label : labels) {
            label.setVisible(false);
        }
        bAceptar.setEnabled(false);
        pDatos.setVisible(false);
        pDni.setVisible(false);

        // Rellenar el ComboBox de equipos
        rellenarEquipos();

        // Configuración de la ventana
        setContentPane(pPrincipal);
        setSize(600, 600);
        setTitle("Gestión de Jugadores");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // Configuración de eventos para los botones
        bAceptar.addActionListener(e -> onOK());

        bCancelar.addActionListener(e -> onCancel());

        // Configuración de eventos para cerrar la ventana con la tecla ESC
        pPrincipal.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Configuración de eventos para el ComboBox de opciones
        cbOpciones.addActionListener(e -> {
            if (cbOpciones.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar una opción");
            } else if (cbOpciones.getSelectedItem().equals("Crear Jugador")) {
                pDatos.setVisible(true);
                pDni.setVisible(true);
            } else if (cbOpciones.getSelectedItem().equals("Modificar Jugador") || cbOpciones.getSelectedItem().equals("Eliminar Jugador") || cbOpciones.getSelectedItem().equals("Mostrar Jugador")) {
                pDatos.setVisible(false);
                pDni.setVisible(true);
            }
        });

        // Configuración de eventos para los campos de texto (validación y errores)
        configurarEventosCamposTexto();
    }

    /**
     * Método que se ejecuta al presionar el botón "Aceptar".
     * Realiza la acción seleccionada en el ComboBox.
     */
    private void onOK() {
        if (cbOpciones.getSelectedItem().equals("Crear Jugador")) {
            if (vistaController.validarDni(tfDni.getText())) {
                JOptionPane.showMessageDialog(this, "Ya existe un Jugador con ese DNI", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (vistaController.validarNickname(tfNickname.getText())) {
                JOptionPane.showMessageDialog(this, "El nickname introducido ya está en uso", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                vistaController.crearJugador(tfDni.getText(), tfNombre.getText(), tfApellido.getText(), tfNickname.getText(), tfNacionalidad.getText(), cmRol.getSelectedItem().toString(), tfFechaNacimiento.getText(), tfSueldo.getText(), cbEquipo.getSelectedItem().toString());
                JOptionPane.showMessageDialog(this, "Jugador con nif " + tfDni.getText() + " creado correctamente", "Jugador Creado", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (cbOpciones.getSelectedItem().equals("Modificar Jugador")) {
            if (vistaController.validarNickname(tfNickname.getText())) {
                JOptionPane.showMessageDialog(this, "El nickname introducido ya está en uso", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                vistaController.modificarJugador(tfDni.getText(), tfNombre.getText(), tfApellido.getText(), tfNickname.getText(), tfNacionalidad.getText(), cmRol.getSelectedItem().toString(), tfFechaNacimiento.getText(), tfSueldo.getText(), cbEquipo.getSelectedItem().toString());
                JOptionPane.showMessageDialog(this, "Jugador con nif " + tfDni.getText() + " modificado correctamente", "Jugador Modificado", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (cbOpciones.getSelectedItem().equals("Eliminar Jugador")) {
            vistaController.eliminarJugador(tfDni.getText());
            JOptionPane.showMessageDialog(this, "Jugador con nif " + tfDni.getText() + " eliminado correctamente", "Jugador Eliminado", JOptionPane.INFORMATION_MESSAGE);
        } else if (cbOpciones.getSelectedItem().equals("Mostrar Jugador")) {
            vistaController.iniciarJugador(tfDni.getText());
        }
        dispose();
        vistaController.iniciarMenuOpciones();
    }

    /**
     * Método que se ejecuta al presionar el botón "Cancelar".
     * Cierra la ventana y regresa al menú principal.
     */
    private void onCancel() {
        dispose();
        vistaController.iniciarMenuOpciones();
    }

    /**
     * Método para validar los campos de texto y habilitar/deshabilitar el botón "Aceptar".
     */
    private void validarCampos() {
        if (cbOpciones.getSelectedItem().equals("Crear Jugador")) {
            boolean todosCompletos = !tfDni.getText().trim().isEmpty() &&
                    !tfNombre.getText().trim().isEmpty() &&
                    !tfApellido.getText().trim().isEmpty() &&
                    !tfNickname.getText().trim().isEmpty() &&
                    !tfNacionalidad.getText().trim().isEmpty() &&
                    !tfFechaNacimiento.getText().trim().isEmpty() &&
                    !tfSueldo.getText().trim().isEmpty() &&
                    !cmRol.getSelectedItem().toString().isEmpty();

            boolean hayErrores = false;
            for (JLabel label : labels) {
                if (label.isVisible()) {
                    hayErrores = true;
                    break;
                }
            }
            bAceptar.setEnabled(todosCompletos && !hayErrores);
        } else if (cbOpciones.getSelectedItem().equals("Eliminar Jugador") || cbOpciones.getSelectedItem().equals("Mostrar Jugador")) {
            boolean todosCompletos = !tfDni.getText().trim().isEmpty();
            boolean hayErrores = false;
            for (JLabel label : labels) {
                if (label.isVisible()) {
                    hayErrores = true;
                    break;
                }
            }
            bAceptar.setEnabled(todosCompletos && !hayErrores);
        } else if (cbOpciones.getSelectedItem().equals("Modificar Jugador")) {
            boolean todosCompletos = !tfDni.getText().trim().isEmpty();
            boolean hayErrores = false;
            for (JLabel label : labels) {
                if (label.isVisible()) {
                    hayErrores = true;
                    break;
                }
            }
            bAceptar.setEnabled(todosCompletos && !hayErrores);
        }
    }

    /**
     * Método para rellenar el ComboBox de equipos con los datos obtenidos del controlador.
     */
    private void rellenarEquipos() {
        try {
            cbEquipo.addItem("");
            for (String equipo : vistaController.obtenerEquipos()) {
                cbEquipo.addItem(equipo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Configura los eventos de validación y errores para los campos de texto.
     */
    private void configurarEventosCamposTexto() {
        tfDni.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (!vistaController.validarDato(tfDni.getText(), "[0-9]{8}[A-Za-z]")) {
                        a1.setVisible(true);
                    }
                } catch (CampoVacioException ex) {
                    e1.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                validarCampos();
                if (!cbOpciones.getSelectedItem().toString().equals("Crear Jugador")) {
                    if (vistaController.validarDni(tfDni.getText())) {
                        if (cbOpciones.getSelectedItem().toString().equals("Modificar Jugador")) {
                            pDatos.setVisible(true);
                        } else {
                            tfDni.setForeground(Color.GREEN);
                        }
                    } else {
                        tfDni.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, "No existe un jugador con ese DNI", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        tfNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (!vistaController.validarDato(tfNombre.getText(), "^[a-zA-Z]{1,25}$")){
                        a2.setVisible(true);
                    }
                } catch (CampoVacioException ex) {
                    e2.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                validarCampos();
            }
        });
        tfApellido.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (!vistaController.validarDato(tfApellido.getText(), "^[a-zA-Z]{1,50}$")){
                        a3.setVisible(true);
                    }
                } catch (CampoVacioException ex) {
                    e3.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                validarCampos();
            }
        });
        tfNickname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (!vistaController.validarDato(tfNickname.getText(), "^[a-zA-Z0-9._]{1,50}$")){
                        a4.setVisible(true);
                    }
                } catch (CampoVacioException ex) {
                    e4.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                validarCampos();
            }
        });
        tfNacionalidad.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (!vistaController.validarDato(tfNacionalidad.getText(), "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\\\s]{1,50}$")){
                        a5.setVisible(true);
                    }
                } catch (CampoVacioException ex) {
                    e5.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                validarCampos();
            }
        });
        tfFechaNacimiento.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (!vistaController.validarDato(tfFechaNacimiento.getText(), "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")){
                        a7.setVisible(true);
                    }
                } catch (CampoVacioException ex) {
                    e7.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                validarCampos();
            }
        });
        tfSueldo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (!vistaController.validarDato(tfSueldo.getText(), "^\\d{1,6}(\\,\\d{1,2})?$")){
                        a8.setVisible(true);
                    }
                } catch (CampoVacioException ex) {
                    e8.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                validarCampos();
            }
        });
        tfDni.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                a1.setVisible(false);
                e1.setVisible(false);
            }
        });
        tfNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                a2.setVisible(false);
                e2.setVisible(false);
            }
        });
        tfApellido.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                a3.setVisible(false);
                e3.setVisible(false);
            }
        });
        tfNickname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                a4.setVisible(false);
                e4.setVisible(false);
            }
        });
        tfNacionalidad.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                a5.setVisible(false);
                e5.setVisible(false);
            }
        });
        tfFechaNacimiento.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                a7.setVisible(false);
                e7.setVisible(false);
            }
        });
        tfSueldo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                a8.setVisible(false);
                e8.setVisible(false);
            }
        });
    }
}