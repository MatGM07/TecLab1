package com.ejercicio.gui;

import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.modelos.Estudiante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarEstudiante extends JPanel {
    private JTextField txtNombre, txtApellidos, txtEmail, txtCodigo, txtPromedio;
    private JCheckBox chkActivo;
    private JButton btnGuardar, btnCancelar;
    private EstudianteService estudianteService;

    public AgregarEstudiante(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
        setLayout(new GridLayout(7, 2, 5, 5));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        add(txtApellidos);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Promedio:"));
        txtPromedio = new JTextField();
        add(txtPromedio);

        add(new JLabel("Activo:"));
        chkActivo = new JCheckBox();
        add(chkActivo);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEstudiante();
            }
        });
        add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        });
        add(btnCancelar);
    }

    private void guardarEstudiante() {
        try {
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String email = txtEmail.getText();
            Double codigo = Double.parseDouble(txtCodigo.getText());
            Double promedio = Double.parseDouble(txtPromedio.getText());
            Boolean activo = chkActivo.isSelected();

            Estudiante estudiante = new Estudiante(null, nombre, apellidos, email, codigo, null, activo, promedio);
            estudianteService.registrarEstudiante(estudiante);
            JOptionPane.showMessageDialog(this, "Estudiante registrado correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEmail.setText("");
        txtCodigo.setText("");
        txtPromedio.setText("");
        chkActivo.setSelected(false);
    }
}
