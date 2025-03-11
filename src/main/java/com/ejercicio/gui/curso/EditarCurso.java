package com.ejercicio.gui.curso;

import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.controlador.CursoController;
import com.ejercicio.gui.curso.PanelCurso;
import com.ejercicio.modelos.Programa;
import com.ejercicio.modelos.Curso;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class EditarCurso extends JPanel {
    private JTextField txtNombre, txtPrograma_id;
    private JButton btnGuardar, btnCancelar;
    private JCheckBox chkActivo;
    private CursoController cursoController;
    private ProgramaService programaService;
    private PanelCurso panelCurso;

    public EditarCurso(Integer id, CursoController cursoController, PanelCurso panelCurso) {
        this.cursoController = cursoController;
        this.panelCurso = panelCurso;

        List<String> datosCurso = cursoController.obtenerDatosPorId(id);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createFieldPanel("ID:", new JTextField(String.valueOf(id), 20), false));
        txtNombre = new JTextField(datosCurso.get(0), 20);
        add(createFieldPanel("Nombre:", txtNombre, true));
        chkActivo = new JCheckBox("Activo", Boolean.parseBoolean(datosCurso.get(1)));
        add(createCheckBoxPanel("Activo:", chkActivo));
        txtPrograma_id = new JTextField(String.valueOf(datosCurso.get(2)), 20);
        add(createFieldPanel("ID Programa:", txtPrograma_id, true));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios(id));
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

    private void guardarCambios(Integer id) {
        try {
            String nombre = txtNombre.getText();
            Boolean activo = chkActivo.isSelected();
            Integer programa_id = Integer.valueOf(txtPrograma_id.getText());
            cursoController.actualizar(id,nombre,activo,programa_id);
            JOptionPane.showMessageDialog(this, "Curso actualizado correctamente");
            panelCurso.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el curso: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
