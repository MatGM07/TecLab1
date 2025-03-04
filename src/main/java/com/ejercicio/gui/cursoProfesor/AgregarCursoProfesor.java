package com.ejercicio.gui.cursoProfesor;

import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.DAOServicios.ProfesorService;
import com.ejercicio.DAOServicios.CursoProfesorService;
import com.ejercicio.gui.cursoProfesor.PanelCursoProfesor;
import com.ejercicio.modelos.Curso;
import com.ejercicio.modelos.CursosProfesores;
import com.ejercicio.modelos.Profesor;
import com.ejercicio.modelos.CursoProfesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AgregarCursoProfesor extends JPanel {
    private JTextField txtProfesorID, txtCursoID, txtAño, txtSemestre;
    private JButton btnGuardar, btnVolver;
    private CursoProfesorService cursoProfesorService;
    private PanelCursoProfesor panelCursoProfesor;
    private CursoService cursoService;
    private ProfesorService profesorService;

    public AgregarCursoProfesor(CursoProfesorService cursoProfesorService, CursoService cursoService, ProfesorService profesorService, PanelCursoProfesor panelCursoProfesor) {
        this.cursoProfesorService = cursoProfesorService;
        this.cursoService = cursoService;
        this.profesorService = profesorService;
        this.panelCursoProfesor = panelCursoProfesor;

        setLayout(new GridLayout(5, 2, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("ID Profesor:"));
        txtProfesorID = new JTextField();
        add(txtProfesorID);

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
        btnGuardar.addActionListener(e -> guardarCursoProfesor());
        add(btnGuardar);

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelCursoProfesor.mostrarVistaPrincipal());
        add(btnVolver);
    }

    private void guardarCursoProfesor() {
        try {
            int profesorId = Integer.parseInt(txtProfesorID.getText());
            int cursoId = Integer.parseInt(txtCursoID.getText());
            int año = Integer.parseInt(txtAño.getText());
            int semestre = Integer.parseInt(txtCursoID.getText());

            Curso curso = cursoService.obtenerPorId(cursoId);
            Profesor profesor = profesorService.obtenerPorId(profesorId);

            if (curso == null) {
                JOptionPane.showMessageDialog(this, "El curso con ID " + cursoId + " no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (profesor == null) {
                JOptionPane.showMessageDialog(this, "El profesor con ID " + profesorId + " no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cursoProfesorService.obtenerPorId(profesorId, cursoId) != null) {
                JOptionPane.showMessageDialog(this, "El profesor ya está inscrito en este curso.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CursoProfesor cursoProfesor = new CursoProfesor(profesor, año, semestre, curso);
            cursoProfesorService.registrarCursoProfesor(cursoProfesor);
            CursosProfesores cursosProfesores = new CursosProfesores();
            cursosProfesores.cargarDatos();
            cursosProfesores.inscribir(cursoProfesor);
            cursosProfesores.guardarInformacion();

            JOptionPane.showMessageDialog(this, "CursoProfesor registrado correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar la relación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtProfesorID.setText("");
        txtCursoID.setText("");
        txtAño.setText("");
        txtSemestre.setText("");
    }
}

