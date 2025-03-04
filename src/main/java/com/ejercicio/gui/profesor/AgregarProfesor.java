package com.ejercicio.gui.profesor;

import com.ejercicio.DAOServicios.ProfesorService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.gui.profesor.PanelProfesor;
import com.ejercicio.modelos.Profesor;
import com.ejercicio.modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AgregarProfesor extends JPanel {
    private JTextField txtNombre, txtApellidos, txtEmail, txtTipoContrato;
    private JButton btnGuardar, btnCancelar, btnVolver;
    private ProfesorService profesorService;
    private PanelProfesor panelProfesor;

    public AgregarProfesor(ProfesorService profesorService, PanelProfesor panelProfesor) {
        this.profesorService = profesorService;
        this.panelProfesor = panelProfesor;

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

        add(new JLabel("Tipo Contrato:"));
        txtTipoContrato = new JTextField();
        add(txtTipoContrato);


        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarProfesor());
        add(btnGuardar);

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelProfesor.mostrarVistaPrincipal());
        add(btnVolver);
    }

    private void guardarProfesor() {
        try {

            String nombres = String.valueOf(txtNombre.getText());
            String apellidos = String.valueOf(txtApellidos.getText());
            String email = String.valueOf(txtEmail.getText());
            String tipoContrato = String.valueOf(txtTipoContrato.getText());
            Profesor profesor = new Profesor(null,nombres,apellidos,email,tipoContrato);
            profesorService.registrarProfesor(profesor);
            JOptionPane.showMessageDialog(this, "Profesor registrado correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar profesor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEmail.setText("");
        txtTipoContrato.setText("");
    }
}
