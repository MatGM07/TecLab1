package com.ejercicio.gui.cursoProfesor;

import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.DAOServicios.ProfesorService;
import com.ejercicio.DAOServicios.CursoProfesorService;
import com.ejercicio.controlador.CursoProfesorController;
import com.ejercicio.gui.cursoProfesor.PanelCursoProfesor;
import com.ejercicio.gui.cursoProfesor.PanelCursoProfesor;
import com.ejercicio.modelos.Curso;
import com.ejercicio.modelos.CursosProfesores;
import com.ejercicio.modelos.Profesor;
import com.ejercicio.modelos.CursoProfesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AgregarCursoProfesor extends JPanel {
    private JTextField txtProfesorID, txtCursoID, txtAño;
    private JComboBox<String> cmbSemestre;
    private JButton btnGuardar, btnVolver;
    private CursoProfesorController cursoProfesorController;
    private PanelCursoProfesor panelCursoProfesor;

    public AgregarCursoProfesor(CursoProfesorController cursoProfesorController, PanelCursoProfesor panelCursoProfesor) {
        this.cursoProfesorController = cursoProfesorController;
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
        cmbSemestre = new JComboBox<>(new String[]{"1", "2"});
        add(cmbSemestre);

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
            int semestre = Integer.parseInt((String) cmbSemestre.getSelectedItem());

            Boolean Existen = cursoProfesorController.existen(profesorId, cursoId);

            if (!Existen) {
                JOptionPane.showMessageDialog(this, "El curso o el profesor no existen", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cursoProfesorController.obtenerPorId(profesorId, cursoId) != null) {
                JOptionPane.showMessageDialog(this, "El profesor ya está inscrito en este curso.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            cursoProfesorController.agregar(profesorId,cursoId,año,semestre);

            JOptionPane.showMessageDialog(this, "Inscripción registrada correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar inscripción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtProfesorID.setText("");
        txtCursoID.setText("");
        txtAño.setText("");
        cmbSemestre.setSelectedIndex(0);
    }
}

