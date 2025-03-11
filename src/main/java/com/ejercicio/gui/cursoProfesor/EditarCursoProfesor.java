package com.ejercicio.gui.cursoProfesor;

import com.ejercicio.DAOServicios.CursoProfesorService;
import com.ejercicio.controlador.CursoProfesorController;
import com.ejercicio.gui.cursoProfesor.PanelCursoProfesor;
import com.ejercicio.gui.cursoProfesor.PanelCursoProfesor;
import com.ejercicio.modelos.CursoProfesor;
import com.ejercicio.modelos.CursosProfesores;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class EditarCursoProfesor extends JPanel {
    private JTextField txtAño;
    private JComboBox<String> cmbSemestre;
    private JButton btnGuardar, btnCancelar;
    private CursoProfesorController cursoProfesorController;
    private PanelCursoProfesor panelCursoProfesor;

    public EditarCursoProfesor(Integer profesor_id, Integer curso_id, CursoProfesorController cursoProfesorController, PanelCursoProfesor panelCursoProfesor) {
        this.cursoProfesorController = cursoProfesorController;
        this.panelCursoProfesor = panelCursoProfesor;

        List<String> datosCursoProfesor = cursoProfesorController.obtenerDatosPorId(profesor_id,curso_id);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createFieldPanel("Profesor ID:", new JTextField(String.valueOf(profesor_id), 20), false));
        add(createFieldPanel("Curso ID:", new JTextField(String.valueOf(curso_id), 20), false));

        txtAño = new JTextField(String.valueOf(datosCursoProfesor.get(0)), 20);
        add(createFieldPanel("Año:", txtAño, true));

        cmbSemestre = new JComboBox<>(new String[]{"1", "2"});
        cmbSemestre.setSelectedItem(datosCursoProfesor.get(1));
        add(createFieldPanel("Semestre:", cmbSemestre, true));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios(profesor_id,curso_id));
        buttonPanel.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> panelCursoProfesor.mostrarVistaPrincipal());
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

    private void guardarCambios(Integer profesor_id, Integer curso_id) {
        try {
            int año = Integer.parseInt(txtAño.getText());
            int semestre = Integer.parseInt((String) cmbSemestre.getSelectedItem());

            cursoProfesorController.actualizar(profesor_id,curso_id,año,semestre);

            JOptionPane.showMessageDialog(this, "Inscripción actualizada correctamente");
            panelCursoProfesor.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la inscripción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}