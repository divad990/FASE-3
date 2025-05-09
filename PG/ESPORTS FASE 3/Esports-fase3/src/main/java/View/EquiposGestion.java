package View;

import Controller.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EquiposGestion extends JFrame{
    private JPanel pPrincipal;
    private JComboBox cbOpciones;
    private JPanel pDatos;
    private JTextField tfNombre;
    private JTextField tffecha;
    private JLabel a2;
    private JLabel a3;
    private JLabel a4;
    private JLabel a5;
    private JLabel a7;
    private JLabel a8;
    private JLabel e2;
    private JLabel e3;
    private JLabel e4;
    private JLabel e5;
    private JLabel e6;
    private JLabel e7;
    private JLabel e8;
    private JButton bAceptar;
    private JButton bCancelar;
    private JTextField tfId;
    private JLabel a1;
    private JLabel e1;
    private JPanel panelPrincipal;

    private static VistaController vistaController;

    private JLabel[] labels = {a2, a3, e2, e3}; // Array de etiquetas de error
    private boolean camposCompletos = false; // Indicador de sí los campos están completos

    public EquiposGestion(VistaController vistaController) {
        this.vistaController = vistaController;

        setContentPane(pPrincipal);
        setSize(600,600);
        setTitle("Gestion de Equipos");

        for (JLabel label : labels) {
            label.setVisible(false);
        }
        bAceptar.setEnabled(false);
        pDatos.setVisible(false);


        cbOpciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbOpciones.getSelectedItem() == null){
                    pDatos.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Seleccione un opcion");
                }else if (cbOpciones.getSelectedItem() == "Crear Equipo"|| cbOpciones.getSelectedItem() == "Modificar Equipo") {
                        pDatos.setVisible(true);
                        tffecha.setEnabled(true);
                        bAceptar.setEnabled(true);

                }else if (cbOpciones.getSelectedItem()== "Eliminar Equipo" ||cbOpciones.getSelectedItem()=="Mostrar Equipo") {
                        pDatos.setVisible(true);
                        tffecha.setEnabled(false);
                        bAceptar.setEnabled(true);
                }
                }
            }
        );
        bAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                todoOK();
            }
        });
    }
    public void todoOK(){
        try{
        if (cbOpciones.getSelectedItem() == "Crear Equipo"|| cbOpciones.getSelectedItem() == "Modificar Equipo") {
            String fechaFundacion;
            LocalDate fechaFormateada;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            if ( cbOpciones.getSelectedItem() == "Crear Equipo"){
                String nombre = tfNombre.getText();
                boolean encontrado = vistaController.buscarEquipo(nombre);
                if (!encontrado){
                    throw new Exception(nombre + " no existe");
                }
                fechaFundacion = tffecha.getText();
                fechaFormateada = LocalDate.parse(fechaFundacion, dtf);
                vistaController.insertarEquipo(nombre,fechaFormateada);
            }else{
                String nombre = tfNombre.getText();
                boolean encontrado = vistaController.buscarEquipo(nombre);
                if (!encontrado){
                    throw new Exception(nombre + " no existe");
                }
                fechaFundacion = tffecha.getText();
                fechaFormateada = LocalDate.parse(fechaFundacion, dtf);
                vistaController.modificarEquipo(nombre,fechaFormateada);
            }
        }
        if (cbOpciones.getSelectedItem()== "Eliminar Equipo" ||cbOpciones.getSelectedItem()=="Mostrar Equipo") {
            if ( cbOpciones.getSelectedItem() == "Eliminar Equipo"){
                String nombre = tfNombre.getText();
                boolean encontrado = vistaController.buscarEquipo(nombre);
                if (!encontrado){
                    throw new Exception(nombre + " no existe");
                }
                vistaController.eliminarEquipo(nombre);
            }else{
                String nombre = tfNombre.getText();
                boolean encontrado = vistaController.buscarEquipo(nombre);
                if (!encontrado){
                    throw new Exception(nombre + " no existe");
                }
                vistaController.mostrarEquipo(nombre);
            }
        }
    }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
