package com.ejercicio.gui.facultad;

import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.controlador.FacultadController;
import com.ejercicio.modelos.Facultad;
import com.ejercicio.modelos.Persona;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class EditarFacultad extends JPanel {
    private JTextField txtNombre, txtDecano_id;
    private JButton btnGuardar, btnCancelar;
    private FacultadController facultadController;
    private PanelFacultad panelFacultad;

    public EditarFacultad(Integer id, FacultadController facultadController,  PanelFacultad panelFacultad) {
        this.panelFacultad = panelFacultad;
        this.facultadController = facultadController;

        List<String> datosFacultad = facultadController.obtenerDatosPorId(id);

        setLayout(new GridLayout(5, 2, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("ID:"));
        JTextField txtID = new JTextField(String.valueOf(id));
        txtID.setEditable(false);
        add(txtID);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(datosFacultad.get(0));
        add(txtNombre);

        add(new JLabel("ID Decano:"));
        txtDecano_id = new JTextField(String.valueOf(datosFacultad.get(1)));
        add(txtDecano_id);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios(id));
        add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> panelFacultad.mostrarVistaPrincipal());
        add(btnCancelar);
    }

    private void guardarCambios(Integer id) {
        try {
            String nombre = txtNombre.getText();
            Integer decano_id = Integer.valueOf(txtDecano_id.getText());

            facultadController.actualizar(id,nombre,decano_id);
            JOptionPane.showMessageDialog(this, "Facultad actualizada correctamente");
            panelFacultad.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la facultad: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
