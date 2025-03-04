package com.ejercicio.gui.programa;

import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.gui.programa.PanelPrograma;
import com.ejercicio.modelos.Programa;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;

public class AgregarPrograma extends JPanel{
    private JTextField txtNombre, txtDuracion, txtFecha, txtFacultad_ID;
    private JButton btnGuardar, btnCancelar, btnVolver;
    private ProgramaService programaService;
    private JDateChooser dateChooser;
    private PanelPrograma panelPrograma;

    public AgregarPrograma(ProgramaService programaService, PanelPrograma panelPrograma) {
        this.programaService = programaService;
        this.panelPrograma = panelPrograma;

        setLayout(new GridLayout(8, 2, 5, 5));

        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Duracion:"));
        txtDuracion = new JTextField();
        add(txtDuracion);

        add(new JLabel("Fecha Registro:"));
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        add(dateChooser);

        add(new JLabel("ID Facultad:"));
        txtFacultad_ID = new JTextField();
        add(txtFacultad_ID);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarPrograma());
        add(btnGuardar);

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelPrograma.mostrarVistaPrincipal());
        add(btnVolver);
    }

    private void guardarPrograma() {
        try {
            String nombre = txtNombre.getText();
            Double duracion = Double.valueOf(txtDuracion.getText());
            java.util.Date fechaUtil = dateChooser.getDate();
            if (fechaUtil == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha válida", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            java.sql.Date registro = new java.sql.Date(fechaUtil.getTime());
            Integer facultad_id = Integer.valueOf(txtFacultad_ID.getText());

            Programa programa = new Programa(null,nombre,duracion,registro,facultad_id);
            programaService.registrarPrograma(programa);
            JOptionPane.showMessageDialog(this, "Programa registrado correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar programa: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDuracion.setText("");
        dateChooser.setDate(null);
        txtFacultad_ID.setText("");
    }
}
