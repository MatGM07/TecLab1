package com.ejercicio.gui.persona;

import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.modelos.Persona;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AgregarPersona extends JPanel {
    private JTextField txtNombre, txtApellidos, txtEmail;
    private JButton btnGuardar, btnCancelar, btnVolver;
    private PersonaService personaService;
    private PanelPersona panelPersona;

    public AgregarPersona(PersonaService personaService, PanelPersona panelPersona) {
        this.personaService = personaService;
        this.panelPersona = panelPersona;

        setLayout(new GridLayout(8, 2, 5, 5));

        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        add(txtApellidos);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarPersona());
        add(btnGuardar);

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelPersona.mostrarVistaPrincipal());
        add(btnVolver);
    }

    private void guardarPersona() {
        try {
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String email = txtEmail.getText();

            Persona persona = new Persona(null, nombre, apellidos, email);
            personaService.registrarPersona(persona);
            JOptionPane.showMessageDialog(this, "Persona registrada correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar persona: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEmail.setText("");
    }
}