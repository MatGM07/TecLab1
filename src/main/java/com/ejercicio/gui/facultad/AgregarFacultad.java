package com.ejercicio.gui.facultad;

import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.gui.facultad.PanelFacultad;
import com.ejercicio.modelos.Facultad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AgregarFacultad extends JPanel {
    private JTextField txtNombre, txtDecano_id;
    private JButton btnGuardar, btnCancelar, btnVolver;
    private FacultadService facultadService;
    private PanelFacultad panelFacultad;

    public AgregarFacultad(FacultadService facultadService, PanelFacultad panelFacultad) {
        this.facultadService = facultadService;
        this.panelFacultad = panelFacultad;

        setLayout(new GridLayout(8, 2, 5, 5));

        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("ID de Decano:"));
        txtDecano_id = new JTextField();
        add(txtDecano_id);


        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarFacultad());
        add(btnGuardar);

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelFacultad.mostrarVistaPrincipal());
        add(btnVolver);
    }

    private void guardarFacultad() {
        try {
            String nombre = txtNombre.getText();
            Integer decano_id = Integer.valueOf(txtDecano_id.getText());

            Facultad facultad = new Facultad(null, nombre, decano_id);
            facultadService.registrarFacultad(facultad);
            JOptionPane.showMessageDialog(this, "Facultad registrada correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar facultad: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDecano_id.setText("");
    }
}
