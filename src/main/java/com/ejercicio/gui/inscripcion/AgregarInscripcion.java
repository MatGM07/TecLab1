package com.ejercicio.gui.inscripcion;

import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.InscripcionService;
import com.ejercicio.gui.programa.PanelPrograma;
import com.ejercicio.modelos.Curso;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Inscripción;
import com.ejercicio.modelos.Programa;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AgregarInscripcion extends JPanel {
    private JTextField txtEstudianteID, txtCursoID, txtAño, txtSemestre;
    private JButton btnGuardar, btnVolver;
    private InscripcionService inscripcionService;
    private PanelInscripcion panelInscripcion;
    private CursoService cursoService;
    private EstudianteService estudianteService;

    public AgregarInscripcion(InscripcionService inscripcionService, CursoService cursoService, EstudianteService estudianteService, PanelInscripcion panelInscripcion) {
        this.inscripcionService = inscripcionService;
        this.cursoService = cursoService;
        this.estudianteService = estudianteService;
        this.panelInscripcion = panelInscripcion;

        setLayout(new GridLayout(5, 2, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("ID Estudiante:"));
        txtEstudianteID = new JTextField();
        add(txtEstudianteID);

        add(new JLabel("ID Curso:"));
        txtCursoID = new JTextField();
        add(txtCursoID);

        add(new JLabel("Año:"));
        txtAño = new JTextField();
        add(txtAño);

        add(new JLabel("Semestre:"));
        txtSemestre = new JTextField();
        add(txtSemestre);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarInscripcion());
        add(btnGuardar);

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelInscripcion.mostrarVistaPrincipal());
        add(btnVolver);
    }

    private void guardarInscripcion() {
        try {
            int estudianteId = Integer.parseInt(txtEstudianteID.getText());
            int cursoId = Integer.parseInt(txtCursoID.getText());
            int año = Integer.parseInt(txtAño.getText());
            int semestre = Integer.parseInt(txtCursoID.getText());

            Curso curso = cursoService.obtenerPorId(cursoId);
            Estudiante estudiante = estudianteService.obtenerPorId(estudianteId);

            if (curso == null) {
                JOptionPane.showMessageDialog(this, "El curso con ID " + cursoId + " no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (estudiante == null) {
                JOptionPane.showMessageDialog(this, "El estudiante con ID " + estudianteId + " no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (inscripcionService.obtenerPorId(estudianteId, cursoId) != null) {
                JOptionPane.showMessageDialog(this, "El estudiante ya está inscrito en este curso.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Inscripción inscripcion = new Inscripción(curso, año, semestre, estudiante);
            inscripcionService.registrarInscripcion(inscripcion);

            JOptionPane.showMessageDialog(this, "Inscripción registrada correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar inscripción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtEstudianteID.setText("");
        txtCursoID.setText("");
        txtAño.setText("");
        txtSemestre.setText("");
    }
}
