package com.ejercicio.gui.inscripcion;

import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.InscripcionService;
import com.ejercicio.controlador.InscripcionController;
import com.ejercicio.gui.programa.PanelPrograma;
import com.ejercicio.modelos.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AgregarInscripcion extends JPanel {
    private JTextField txtEstudianteID, txtCursoID, txtAño;
    private JComboBox<String> cmbSemestre;
    private JButton btnGuardar, btnVolver;
    private InscripcionController inscripcionController;
    private PanelInscripcion panelInscripcion;

    public AgregarInscripcion(InscripcionController inscripcionController, PanelInscripcion panelInscripcion) {
        this.inscripcionController = inscripcionController;
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
        cmbSemestre = new JComboBox<>(new String[]{"1", "2"}); // ComboBox con solo 1 y 2
        add(cmbSemestre);

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
            int semestre = Integer.parseInt((String) cmbSemestre.getSelectedItem());

            Boolean Existen = inscripcionController.existen(estudianteId, cursoId);

            if (!Existen) {
                JOptionPane.showMessageDialog(this, "El curso o el estudiante no existen", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (inscripcionController.obtenerPorId(estudianteId, cursoId) != null) {
                JOptionPane.showMessageDialog(this, "El estudiante ya está inscrito en este curso.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            inscripcionController.agregar(estudianteId,cursoId,año,semestre);


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
        cmbSemestre.setSelectedIndex(0);
    }
}
