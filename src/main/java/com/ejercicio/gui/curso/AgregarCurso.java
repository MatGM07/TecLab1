package com.ejercicio.gui.curso;

import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.controlador.CursoController;
import com.ejercicio.gui.curso.PanelCurso;
import com.ejercicio.modelos.Curso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AgregarCurso extends JPanel {
    private JTextField txtNombre, txtPrograma_id;
    private JCheckBox chkActivo;
    private JButton btnGuardar, btnCancelar, btnVolver;
    private CursoController cursoController;
    private PanelCurso panelCurso;

    public AgregarCurso(CursoController cursoController, PanelCurso panelCurso) {
        this.cursoController = cursoController;
        this.panelCurso = panelCurso;

        setLayout(new GridLayout(8, 2, 5, 5));

        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Activo:"));
        chkActivo = new JCheckBox();
        add(chkActivo);

        add(new JLabel("ID Programa:"));
        txtPrograma_id = new JTextField();
        add(txtPrograma_id);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarCurso());
        add(btnGuardar);

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelCurso.mostrarVistaPrincipal());
        add(btnVolver);
    }

    private void guardarCurso() {
        try {
            String nombre = String.valueOf(txtNombre.getText());
            boolean activo = chkActivo.isSelected();
            Integer programa_id = Integer.valueOf(txtPrograma_id.getText());

            cursoController.agregar(nombre,activo,programa_id);

            JOptionPane.showMessageDialog(this, "Curso registrado correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar curso: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtPrograma_id.setText("");
        chkActivo.setSelected(false);
    }
}
