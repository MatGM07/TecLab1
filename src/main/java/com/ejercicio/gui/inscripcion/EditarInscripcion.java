package com.ejercicio.gui.inscripcion;
import com.ejercicio.modelos.Inscripción;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import com.ejercicio.DAOServicios.InscripcionService;

public class EditarInscripcion extends JPanel {
    private JTextField txtAño, txtSemestre;
    private JButton btnGuardar, btnCancelar;
    private InscripcionService inscripcionService;
    private PanelInscripcion panelInscripcion;
    private Inscripción inscripcion;

    public EditarInscripcion(Inscripción inscripcion, InscripcionService inscripcionService, PanelInscripcion panelInscripcion) {
        this.inscripcion = inscripcion;
        this.inscripcionService = inscripcionService;
        this.panelInscripcion = panelInscripcion;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createFieldPanel("Estudiante ID:", new JTextField(String.valueOf(inscripcion.getEstudiante().getID()), 20), false));
        add(createFieldPanel("Estudiante Nombre:", new JTextField(inscripcion.getEstudiante().getNombre(), 20), false));
        add(createFieldPanel("Curso ID:", new JTextField(String.valueOf(inscripcion.getCurso().getID()), 20), false));
        add(createFieldPanel("Curso Nombre:", new JTextField(inscripcion.getCurso().getNombre(), 20), false));

        txtAño = new JTextField(String.valueOf(inscripcion.getAño()), 20);
        add(createFieldPanel("Año:", txtAño, true));

        txtSemestre = new JTextField(String.valueOf(inscripcion.getSemestre()), 20);
        add(createFieldPanel("Semestre:", txtSemestre, true));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
        buttonPanel.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> panelInscripcion.mostrarVistaPrincipal());
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
            int año = Integer.parseInt(txtAño.getText());
            int semestre = Integer.parseInt(txtSemestre.getText());

            inscripcion.setAño(año);
            inscripcion.setSemestre(semestre);

            inscripcionService.actualizarInscripcion(inscripcion);
            JOptionPane.showMessageDialog(this, "Inscripción actualizada correctamente");
            panelInscripcion.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la inscripción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
