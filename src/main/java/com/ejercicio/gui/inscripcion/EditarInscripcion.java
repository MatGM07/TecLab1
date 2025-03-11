package com.ejercicio.gui.inscripcion;
import com.ejercicio.controlador.InscripcionController;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;


public class EditarInscripcion extends JPanel {
    private JTextField txtAño;
    private JComboBox<String> cmbSemestre;
    private JButton btnGuardar, btnCancelar;
    private InscripcionController inscripcionController;
    private PanelInscripcion panelInscripcion;

    public EditarInscripcion(Integer estudiante_id, Integer curso_id, InscripcionController inscripcionController, PanelInscripcion panelInscripcion) {
        this.inscripcionController = inscripcionController;
        this.panelInscripcion = panelInscripcion;

        List<String> datosInscripcion = inscripcionController.obtenerDatosPorId(estudiante_id,curso_id);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createFieldPanel("Estudiante ID:", new JTextField(String.valueOf(estudiante_id), 20), false));
        add(createFieldPanel("Curso ID:", new JTextField(String.valueOf(curso_id), 20), false));

        txtAño = new JTextField(String.valueOf(datosInscripcion.get(0)), 20);
        add(createFieldPanel("Año:", txtAño, true));

        cmbSemestre = new JComboBox<>(new String[]{"1", "2"});
        cmbSemestre.setSelectedItem(datosInscripcion.get(1));
        add(createFieldPanel("Semestre:", cmbSemestre, true));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios(estudiante_id,curso_id));
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

    private void guardarCambios(Integer estudiante_id, Integer curso_id) {
        try {
            int año = Integer.parseInt(txtAño.getText());
            int semestre = Integer.parseInt((String) cmbSemestre.getSelectedItem());

            inscripcionController.actualizar(estudiante_id,curso_id,año,semestre);



            JOptionPane.showMessageDialog(this, "Inscripción actualizada correctamente");
            panelInscripcion.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la inscripción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
