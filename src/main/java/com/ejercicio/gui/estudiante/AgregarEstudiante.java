package com.ejercicio.gui.estudiante;

import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.gui.estudiante.PanelEstudiante;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Persona;
import com.ejercicio.modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AgregarEstudiante extends JPanel {
    private JTextField txtNombre, txtCodigo, txtApellidos, txtEmail, txtPrograma_id, txtPromedio;
    private JCheckBox chkActivo;
    private JButton btnGuardar, btnCancelar, btnVolver;
    private EstudianteService estudianteService;
    private PersonaService personaService;
    private PanelEstudiante panelEstudiante;
    private ProgramaService programaService;

    public AgregarEstudiante(EstudianteService estudianteService, ProgramaService programaService, PanelEstudiante panelEstudiante) {
        this.estudianteService = estudianteService;
        this.programaService = programaService;
        this.panelEstudiante = panelEstudiante;

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

        add(new JLabel("Codigo:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("ID de Programa:"));
        txtPrograma_id = new JTextField();
        add(txtPrograma_id);

        add(new JLabel("Promedio:"));
        txtPromedio = new JTextField();
        add(txtPromedio);

        add(new JLabel("Activo:"));
        chkActivo = new JCheckBox();
        add(chkActivo);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarEstudiante());
        add(btnGuardar);

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelEstudiante.mostrarVistaPrincipal());
        add(btnVolver);
    }

    private void guardarEstudiante() {
        try {

            String nombres = String.valueOf(txtNombre.getText());
            String apellidos = String.valueOf(txtApellidos.getText());
            String email = String.valueOf(txtEmail.getText());
            Double codigo = Double.valueOf(txtCodigo.getText());
            Integer programa_id = Integer.valueOf(txtPrograma_id.getText());
            Programa programa = programaService.obtenerPorId(programa_id);
            boolean activo = chkActivo.isSelected();
            Double promedio = Double.valueOf(txtPromedio.getText());

            Estudiante estudiante = new Estudiante(null,nombres,apellidos,email,codigo,programa,activo,promedio);

            estudianteService.registrarEstudiante(estudiante);
            JOptionPane.showMessageDialog(this, "Estudiante registrada correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtPrograma_id.setText("");
        txtPromedio.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEmail.setText("");
        chkActivo.setSelected(false);
    }
}
