package com.ejercicio.gui.persona;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.modelos.InscripcionesPersonas;
import com.ejercicio.modelos.Persona;

public class EditarPersona extends JPanel {
    private JTextField txtNombre, txtApellidos, txtEmail;
    private JButton btnGuardar, btnCancelar;
    private PersonaService personaService;
    private PanelPersona panelPersona;
    private Persona persona;

    public EditarPersona(Persona persona, PersonaService personaService, PanelPersona panelPersona) {
        this.persona = persona;
        this.personaService = personaService;
        this.panelPersona = panelPersona;

        setLayout(new GridLayout(5, 2, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("ID:"));
        JTextField txtID = new JTextField(String.valueOf(persona.getID()));
        txtID.setEditable(false);
        add(txtID);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(persona.getNombre());
        add(txtNombre);

        add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField(persona.getApellidos());
        add(txtApellidos);

        add(new JLabel("Email:"));
        txtEmail = new JTextField(persona.getEmail());
        add(txtEmail);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
        add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> panelPersona.mostrarVistaPrincipal());
        add(btnCancelar);
    }

    private void guardarCambios() {
        try {
            persona.setNombre(txtNombre.getText());
            persona.setApellidos(txtApellidos.getText());
            persona.setEmail(txtEmail.getText());

            personaService.actualizarPersona(persona);
            InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
            inscripcionesPersonas.cargarDatos();
            inscripcionesPersonas.actualizar(persona);
            inscripcionesPersonas.guardarInformacion();

            JOptionPane.showMessageDialog(this, "Persona actualizada correctamente");
            panelPersona.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la persona: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}