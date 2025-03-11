package com.ejercicio.gui.persona;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.controlador.PersonaController;
import com.ejercicio.modelos.InscripcionesPersonas;
import com.ejercicio.modelos.Persona;

public class EditarPersona extends JPanel {
    private JTextField txtNombre, txtApellidos, txtEmail;
    private JButton btnGuardar, btnCancelar;
    private PersonaController personaController;
    private PanelPersona panelPersona;

    public EditarPersona(Integer id, PersonaController personaController, PanelPersona panelPersona) {
        this.personaController = personaController;
        this.panelPersona = panelPersona;

        List<String> datosPersona = personaController.obtenerDatosPorId(id);

        setLayout(new GridLayout(5, 2, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("ID:"));
        JTextField txtID = new JTextField(String.valueOf(id));
        txtID.setEditable(false);
        add(txtID);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(datosPersona.get(0));
        add(txtNombre);

        add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField(datosPersona.get(1));
        add(txtApellidos);

        add(new JLabel("Email:"));
        txtEmail = new JTextField(datosPersona.get(2));
        add(txtEmail);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios(id));
        add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> panelPersona.mostrarVistaPrincipal());
        add(btnCancelar);
    }

    private void guardarCambios(Integer id) {
        try {
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String email = txtEmail.getText();

            personaController.actualizar(id,nombre,apellidos,email);



            JOptionPane.showMessageDialog(this, "Persona actualizada correctamente");
            panelPersona.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la persona: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}