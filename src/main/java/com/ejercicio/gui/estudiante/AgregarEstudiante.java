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
    private JTextField txtNombre, txtPrograma_id, txtPersona_id, txtPromedio;
    private JCheckBox chkActivo;
    private JButton btnGuardar, btnCancelar, btnVolver;
    private EstudianteService estudianteService;
    private PersonaService personaService;
    private PanelEstudiante panelEstudiante;
    private ProgramaService programaService;

    public AgregarEstudiante(EstudianteService estudianteService, PersonaService personaService, ProgramaService programaService, PanelEstudiante panelEstudiante) {
        this.estudianteService = estudianteService;
        this.personaService = personaService;
        this.programaService = programaService;
        this.panelEstudiante = panelEstudiante;

        setLayout(new GridLayout(8, 2, 5, 5));

        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Codigo:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("ID de Programa:"));
        txtPrograma_id = new JTextField();
        add(txtPrograma_id);

        add(new JLabel("ID de Persona:"));
        txtPersona_id = new JTextField();
        add(txtPersona_id);

        add(new JLabel("Promedio:"));
        txtPromedio = new JTextField();
        add(txtPromedio);


        add(new JLabel("Activo:"));
        chkActivo = new JCheckBox(); // CheckBox sin texto
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
            Persona persona = personaService.obtenerPorId(Integer.parseInt(txtPersona_id.getText()));

            Estudiante estudiante = (Estudiante) persona;

            Double codigo = Double.valueOf(txtNombre.getText());
            estudiante.setCodigo(codigo);

            Integer programa_id = Integer.valueOf(txtPrograma_id.getText());
            Programa programa = programaService.obtenerPorId(programa_id);
            estudiante.setPrograma(programa);

            boolean activo = chkActivo.isSelected();
            estudiante.setActivo(activo);

            Double promedio = Double.valueOf(txtPromedio.getText());
            estudiante.setPromedio(promedio);

            estudianteService.registrarEstudiante(estudiante);
            JOptionPane.showMessageDialog(this, "Estudiante registrada correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtPrograma_id.setText("");
        txtPromedio.setText("");
        txtPersona_id.setText("");
        chkActivo.setSelected(false);
    }
}
