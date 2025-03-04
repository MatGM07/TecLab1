package com.ejercicio.gui.facultad;

import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.modelos.Facultad;
import com.ejercicio.modelos.Persona;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditarFacultad extends JPanel {
    private JTextField txtNombre, txtDecano_id;
    private JButton btnGuardar, btnCancelar;
    private FacultadService facultadService;
    private PersonaService personaService;
    private PanelFacultad panelFacultad;
    private Facultad facultad;

    public EditarFacultad(Facultad facultad, FacultadService facultadService, PersonaService personaService, PanelFacultad panelFacultad) {
        this.facultad = facultad;
        this.facultadService = facultadService;
        this.panelFacultad = panelFacultad;
        this.personaService = personaService;

        setLayout(new GridLayout(5, 2, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("ID:"));
        JTextField txtID = new JTextField(String.valueOf(facultad.getID()));
        txtID.setEditable(false);
        add(txtID);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(facultad.getNombre());
        add(txtNombre);

        add(new JLabel("ID Decano:"));
        txtDecano_id = new JTextField(String.valueOf(facultad.getDecano().getID()));
        add(txtDecano_id);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
        add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> panelFacultad.mostrarVistaPrincipal());
        add(btnCancelar);
    }

    private void guardarCambios() {
        try {
            facultad.setNombre(txtNombre.getText());
            Persona decano = personaService.obtenerPorId(Integer.parseInt(txtDecano_id.getText()));
            facultad.setDecano(decano);
            facultadService.actualizarFacultad(facultad);
            JOptionPane.showMessageDialog(this, "Facultad actualizada correctamente");
            panelFacultad.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la facultad: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
