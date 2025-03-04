package com.ejercicio.gui.profesor;

import com.ejercicio.DAOServicios.ProfesorService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.gui.profesor.PanelProfesor;
import com.ejercicio.modelos.Profesor;
import com.ejercicio.modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditarProfesor extends JPanel {
    private JTextField txtNombre, txtApellidos, txtEmail, txtTipoContrato;
    private JButton btnGuardar, btnCancelar;
    private ProfesorService profesorService;
    private PanelProfesor panelProfesor;
    private Profesor profesor;

    public EditarProfesor(Profesor profesor, ProfesorService profesorService,  PanelProfesor panelProfesor) {
        this.profesor = profesor;
        this.profesorService = profesorService;
        this.panelProfesor = panelProfesor;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createFieldPanel("ID:", new JTextField(String.valueOf(profesor.getID()), 20), false));
        txtNombre = new JTextField(String.valueOf(profesor.getNombre()), 20);
        add(createFieldPanel("Nombres:", txtNombre, true));
        txtApellidos = new JTextField(String.valueOf(profesor.getApellidos()), 20);
        add(createFieldPanel("Apellidos:", txtApellidos, true));
        txtEmail = new JTextField(String.valueOf(profesor.getEmail()), 20);
        add(createFieldPanel("Email:", txtEmail, true));
        txtTipoContrato = new JTextField(String.valueOf(profesor.getTipoContrato()), 20);
        add(createFieldPanel("Tipo de Contrato:", txtTipoContrato, true));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
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


    private void guardarCambios() {
        try {
            profesor.setNombre(txtNombre.getText());
            profesor.setApellidos(txtApellidos.getText());
            profesor.setEmail(txtEmail.getText());
            profesor.setTipoContrato(txtTipoContrato.getText());

            profesorService.actualizarProfesor(profesor);
            JOptionPane.showMessageDialog(this, "Profesor actualizado correctamente");
            panelProfesor.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el profesor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
