package com.ejercicio.gui.estudiante;

import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.controlador.EstudianteController;
import com.ejercicio.controlador.ProgramaController;
import com.ejercicio.gui.estudiante.PanelEstudiante;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Persona;
import com.ejercicio.modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class EditarEstudiante extends JPanel {
    private JTextField txtNombre, txtCodigo, txtApellidos, txtEmail, txtProgramaId, txtPromedio;
    private JCheckBox chkActivo;
    private JButton btnGuardar, btnCancelar;
    private EstudianteController estudianteController;
    private PanelEstudiante panelEstudiante;

    public EditarEstudiante(Integer id, EstudianteController estudianteController, PanelEstudiante panelEstudiante) {
        List<String> datosEstudiantes = estudianteController.obtenerDatosPorId(id);
        this.estudianteController = estudianteController;
        this.panelEstudiante = panelEstudiante;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createFieldPanel("ID:", new JTextField(String.valueOf(id), 20), false));
        txtNombre = new JTextField(datosEstudiantes.get(0), 20);
        add(createFieldPanel("Nombres:", txtNombre, true));
        txtApellidos = new JTextField(datosEstudiantes.get(1), 20);
        add(createFieldPanel("Apellidos:", txtApellidos, true));
        txtEmail = new JTextField(datosEstudiantes.get(2), 20);
        add(createFieldPanel("Email:", txtEmail, true));
        txtCodigo = new JTextField(datosEstudiantes.get(3), 20);
        add(createFieldPanel("Código:", txtCodigo, true));
        chkActivo = new JCheckBox("Activo", Boolean.parseBoolean(datosEstudiantes.get(4)));
        add(createCheckBoxPanel("Activo:", chkActivo));
        txtProgramaId = new JTextField(datosEstudiantes.get(5), 20);
        add(createFieldPanel("ID Programa:", txtProgramaId, true));
        txtPromedio = new JTextField(datosEstudiantes.get(6), 20);
        add(createFieldPanel("Promedio:", txtPromedio, true));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios(id));
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

    private void guardarCambios(Integer id) {
        try {
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String email = txtEmail.getText();
            Double codigo = Double.valueOf(txtCodigo.getText());
            Boolean activo = chkActivo.isSelected();
            Integer programa_id = Integer.valueOf(txtProgramaId.getText());
            Double promedio = Double.valueOf(txtPromedio.getText());

            estudianteController.actualizar(id,nombre,apellidos,email,codigo,programa_id,activo,promedio);

            JOptionPane.showMessageDialog(this, "Estudiante actualizado correctamente");
            panelEstudiante.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
