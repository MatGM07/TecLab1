package com.ejercicio.gui.curso;

import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.gui.curso.PanelCurso;
import com.ejercicio.modelos.Programa;
import com.ejercicio.modelos.Curso;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditarCurso extends JPanel {
    private JTextField txtNombre, txtPrograma_id;
    private JButton btnGuardar, btnCancelar;
    private JCheckBox chkActivo;
    private CursoService cursoService;
    private ProgramaService programaService;
    private PanelCurso panelCurso;
    private Curso curso;

    public EditarCurso(Curso curso, CursoService cursoService, ProgramaService programaService, PanelCurso panelCurso) {
        this.curso = curso;
        this.cursoService = cursoService;
        this.programaService = programaService;
        this.panelCurso = panelCurso;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createFieldPanel("ID:", new JTextField(String.valueOf(curso.getID()), 20), false));
        txtNombre = new JTextField(curso.getNombre(), 20);
        add(createFieldPanel("Nombre:", txtNombre, true));
        chkActivo = new JCheckBox("Activo", curso.getActivo());
        add(createCheckBoxPanel("Activo:", chkActivo));
        txtPrograma_id = new JTextField(String.valueOf(curso.getPrograma().getID()), 20);
        add(createFieldPanel("ID Programa:", txtPrograma_id, true));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
        buttonPanel.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> panelCurso.mostrarVistaPrincipal());
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
            curso.setNombre(txtNombre.getText());
            curso.setActivo(chkActivo.isSelected());
            Programa programa = programaService.obtenerPorId(Integer.parseInt(txtPrograma_id.getText()));
            curso.setPrograma(programa);

            cursoService.actualizarCurso(curso);
            JOptionPane.showMessageDialog(this, "Curso actualizado correctamente");
            panelCurso.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el curso: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
