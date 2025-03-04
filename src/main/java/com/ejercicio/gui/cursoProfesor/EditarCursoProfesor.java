package com.ejercicio.gui.cursoProfesor;

import com.ejercicio.DAOServicios.CursoProfesorService;
import com.ejercicio.gui.cursoProfesor.PanelCursoProfesor;
import com.ejercicio.modelos.CursoProfesor;
import com.ejercicio.modelos.CursosProfesores;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditarCursoProfesor extends JPanel {
    private JTextField txtAño, txtSemestre;
    private JButton btnGuardar, btnCancelar;
    private CursoProfesorService cursoProfesorService;
    private PanelCursoProfesor panelCursoProfesor;
    private CursoProfesor cursoProfesor;

    public EditarCursoProfesor(CursoProfesor cursoProfesor, CursoProfesorService cursoProfesorService, PanelCursoProfesor panelCursoProfesor) {
        this.cursoProfesor = cursoProfesor;
        this.cursoProfesorService = cursoProfesorService;
        this.panelCursoProfesor = panelCursoProfesor;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createFieldPanel("Profesor ID:", new JTextField(String.valueOf(cursoProfesor.getProfesor().getID()), 20), false));
        add(createFieldPanel("Profesor Nombre:", new JTextField(cursoProfesor.getProfesor().getNombre(), 20), false));
        add(createFieldPanel("Curso ID:", new JTextField(String.valueOf(cursoProfesor.getCurso().getID()), 20), false));
        add(createFieldPanel("Curso Nombre:", new JTextField(cursoProfesor.getCurso().getNombre(), 20), false));

        txtAño = new JTextField(String.valueOf(cursoProfesor.getAño()), 20);
        add(createFieldPanel("Año:", txtAño, true));

        txtSemestre = new JTextField(String.valueOf(cursoProfesor.getSemestre()), 20);
        add(createFieldPanel("Semestre:", txtSemestre, true));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
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

    private void guardarCambios() {
        try {
            int año = Integer.parseInt(txtAño.getText());
            int semestre = Integer.parseInt(txtSemestre.getText());

            cursoProfesor.setAño(año);
            cursoProfesor.setSemestre(semestre);

            cursoProfesorService.actualizarCursoProfesor(cursoProfesor);
            CursosProfesores cursosProfesores = new CursosProfesores();
            cursosProfesores.cargarDatos();
            cursosProfesores.actualizar(cursoProfesor);

            JOptionPane.showMessageDialog(this, "CursoProfesor actualizada correctamente");
            panelCursoProfesor.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la relación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

