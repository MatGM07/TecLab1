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

public class EditarEstudiante extends JPanel{
    private JTextField txtCodigo, txtActivo, txtPrograma_Id, txtPersona_Id;
    private JButton btnGuardar, btnCancelar;
    private EstudianteService estudianteService;
    private PersonaService personaService;
    private ProgramaService programaService;
    private PanelEstudiante panelEstudiante;
    private Estudiante estudiante;

    public EditarEstudiante(Estudiante estudiante,Persona persona, EstudianteService estudianteService, PersonaService personaService, ProgramaService programaService, PanelEstudiante panelEstudiante) {
        this.estudiante = estudiante;
        this.estudianteService = estudianteService;
        this.panelEstudiante = panelEstudiante;
        this.personaService = personaService;

        setLayout(new GridLayout(5, 2, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("ID:"));
        JTextField txtID = new JTextField(String.valueOf(estudiante.getID()));
        txtID.setEditable(false);
        add(txtID);

        add(new JLabel("codigo:"));
        txtCodigo = new JTextField(String.valueOf(estudiante.getCodigo()));
        add(txtCodigo);

        add(new JLabel("Activo:"));
        txtActivo = new JTextField(String.valueOf(estudiante.getActivo()));
        add(txtActivo);

        add(new JLabel("ID Programa:"));
        txtPrograma_Id = new JTextField(String.valueOf(estudiante.getPrograma().getID()));
        add(txtPrograma_Id);

        add(new JLabel("ID Persona:"));
        txtPersona_Id = new JTextField(String.valueOf(persona.getID()));
        txtPersona_Id.setEditable(false);
        add(txtPersona_Id);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
        add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> panelEstudiante.mostrarVistaPrincipal());
        add(btnCancelar);
    }

    private void guardarCambios() {
        try {
            estudiante.setCodigo(Double.valueOf(txtCodigo.getText()));
            Programa programa = programaService.obtenerPorId(Integer.parseInt(txtPrograma_Id.getText()));
            estudiante.setPrograma(programa);

            estudianteService.actualizarEstudiante(estudiante);
            JOptionPane.showMessageDialog(this, "Estudiante actualizada correctamente");
            panelEstudiante.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

