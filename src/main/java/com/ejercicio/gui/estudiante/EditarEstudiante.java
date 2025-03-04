package com.ejercicio.gui.estudiante;

import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.gui.estudiante.PanelEstudiante;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Persona;
import com.ejercicio.modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditarEstudiante extends JPanel {
    private JTextField txtNombre, txtCodigo, txtApellidos, txtEmail, txtProgramaId, txtPromedio;
    private JCheckBox chkActivo;
    private JButton btnGuardar, btnCancelar;
    private EstudianteService estudianteService;
    private ProgramaService programaService;
    private PanelEstudiante panelEstudiante;
    private Estudiante estudiante;

    public EditarEstudiante(Estudiante estudiante, EstudianteService estudianteService, ProgramaService programaService, PanelEstudiante panelEstudiante) {
        this.estudiante = estudiante;
        this.estudianteService = estudianteService;
        this.programaService = programaService;
        this.panelEstudiante = panelEstudiante;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createFieldPanel("ID:", new JTextField(String.valueOf(estudiante.getID()), 20), false));
        txtNombre = new JTextField(String.valueOf(estudiante.getNombre()), 20);
        add(createFieldPanel("Nombres:", txtNombre, true));
        txtApellidos = new JTextField(String.valueOf(estudiante.getApellidos()), 20);
        add(createFieldPanel("Apellidos:", txtApellidos, true));
        txtEmail = new JTextField(String.valueOf(estudiante.getEmail()), 20);
        add(createFieldPanel("Email:", txtEmail, true));
        txtCodigo = new JTextField(String.valueOf(estudiante.getCodigo()), 20);
        add(createFieldPanel("Código:", txtCodigo, true));
        chkActivo = new JCheckBox("Activo", estudiante.getActivo());
        add(createCheckBoxPanel("Activo:", chkActivo));
        txtProgramaId = new JTextField(String.valueOf(estudiante.getPrograma().getID()), 20);
        add(createFieldPanel("ID Programa:", txtProgramaId, true));
        txtPromedio = new JTextField(String.valueOf(estudiante.getPromedio()), 20);
        add(createFieldPanel("Promedio:", txtPromedio, true));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
        buttonPanel.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> panelEstudiante.mostrarVistaPrincipal());
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

    private JPanel createCheckBoxPanel(String label, JCheckBox checkBox) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(label));
        panel.add(checkBox);
        return panel;
    }

    private void guardarCambios() {
        try {
            estudiante.setNombre(txtNombre.getText());
            estudiante.setApellidos(txtApellidos.getText());
            estudiante.setEmail(txtEmail.getText());
            estudiante.setCodigo(Double.parseDouble(txtCodigo.getText()));
            estudiante.setActivo(chkActivo.isSelected());
            Programa programa = programaService.obtenerPorId(Integer.parseInt(txtProgramaId.getText()));
            estudiante.setPrograma(programa);
            estudiante.setPromedio(Double.parseDouble(txtPromedio.getText()));

            estudianteService.actualizarEstudiante(estudiante);
            JOptionPane.showMessageDialog(this, "Estudiante actualizado correctamente");
            panelEstudiante.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
