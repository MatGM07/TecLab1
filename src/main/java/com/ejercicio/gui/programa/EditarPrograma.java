package com.ejercicio.gui.programa;

import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.gui.programa.PanelPrograma;
import com.ejercicio.modelos.Facultad;
import com.ejercicio.modelos.Programa;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;

public class EditarPrograma extends JPanel {
    private JTextField txtNombre, txtDuracion, txtFacultad_id;
    private JButton btnGuardar, btnCancelar;
    private JDateChooser dateChooserRegistro;
    private ProgramaService programaService;
    private FacultadService facultadService;
    private PanelPrograma panelPrograma;
    private Programa programa;

    public EditarPrograma(Programa programa, ProgramaService programaService, FacultadService facultadService, PanelPrograma panelPrograma) {
        this.programa = programa;
        this.programaService = programaService;
        this.facultadService = facultadService;
        this.panelPrograma = panelPrograma;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createFieldPanel("ID:", new JTextField(String.valueOf(programa.getID()), 20), false));
        txtNombre = new JTextField(programa.getNombre(), 20);
        add(createFieldPanel("Nombre:", txtNombre, true));
        txtDuracion = new JTextField(String.valueOf(programa.getDuracion()), 20);
        add(createFieldPanel("Duración:", txtDuracion, true));

        dateChooserRegistro = new JDateChooser();
        dateChooserRegistro.setDate(programa.getRegistro());
        dateChooserRegistro.setDateFormatString("yyyy-MM-dd");
        add(createFieldPanel("Fecha Registro:", dateChooserRegistro, true));

        txtFacultad_id = new JTextField(String.valueOf(programa.getFacultad().getID()), 20);
        add(createFieldPanel("ID Facultad:", txtFacultad_id, true));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
        buttonPanel.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> panelPrograma.mostrarVistaPrincipal());
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
            programa.setNombre(txtNombre.getText());
            programa.setDuracion(Double.parseDouble(txtDuracion.getText()));

            java.util.Date fechaUtil = dateChooserRegistro.getDate();
            if (fechaUtil == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha válida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            programa.setRegistro(new java.sql.Date(fechaUtil.getTime()));
            Facultad facultad = facultadService.obtenerPorId(Integer.parseInt(txtFacultad_id.getText()));
            programa.setFacultad(facultad);

            programaService.actualizarPrograma(programa);
            JOptionPane.showMessageDialog(this, "Programa actualizado correctamente");
            panelPrograma.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el programa: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
