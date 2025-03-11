package com.ejercicio.gui.profesor;

import com.ejercicio.DAOServicios.ProfesorService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.controlador.ProfesorController;
import com.ejercicio.gui.profesor.PanelProfesor;
import com.ejercicio.modelos.Profesor;
import com.ejercicio.modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class EditarProfesor extends JPanel {
    private JTextField txtNombre, txtApellidos, txtEmail, txtTipoContrato;
    private JButton btnGuardar, btnCancelar;
    private ProfesorController profesorController;
    private PanelProfesor panelProfesor;
    private Profesor profesor;

    public EditarProfesor(Integer id, ProfesorController profesorController, PanelProfesor panelProfesor) {
        List<String> datosProfesor = profesorController.obtenerDatosPorId(id);

        this.profesorController = profesorController;
        this.panelProfesor = panelProfesor;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createFieldPanel("ID:", new JTextField(String.valueOf(id), 20), false));
        txtNombre = new JTextField(String.valueOf(datosProfesor.get(0)), 20);
        add(createFieldPanel("Nombres:", txtNombre, true));
        txtApellidos = new JTextField(String.valueOf(datosProfesor.get(1)), 20);
        add(createFieldPanel("Apellidos:", txtApellidos, true));
        txtEmail = new JTextField(String.valueOf(datosProfesor.get(2)), 20);
        add(createFieldPanel("Email:", txtEmail, true));
        txtTipoContrato = new JTextField(String.valueOf(datosProfesor.get(3)), 20);
        add(createFieldPanel("Tipo de Contrato:", txtTipoContrato, true));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios(id));
        buttonPanel.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> panelProfesor.mostrarVistaPrincipal());
        buttonPanel.add(btnCancelar);

        add(buttonPanel);
    }

    private JPanel createFieldPanel(String label, JComponent field, boolean editable) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(label));
        if (field instanceof JTextField) {
            ((JTextField) field).setEditable(editable);
        }
        panel.add(field);
        return panel;
    }


    private void guardarCambios(Integer id) {
        try {
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String email = txtEmail.getText();
            String tipoContrato = txtTipoContrato.getText();

            profesorController.actualizar(id,nombre,apellidos,email,tipoContrato);
            JOptionPane.showMessageDialog(this, "Profesor actualizado correctamente");
            panelProfesor.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el profesor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
