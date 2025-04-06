package com.ejercicio.gui;

import com.ejercicio.ConexionController;
import com.ejercicio.controlador.CursoController;
import com.ejercicio.controlador.EstudianteController;
import com.ejercicio.controlador.InscripcionController;
import com.ejercicio.controlador.ProfesorController;
import com.ejercicio.gui.curso.ListarCurso;
import com.ejercicio.gui.estudiante.EditarEstudiante;
import com.ejercicio.gui.inscripcion.AgregarInscripcion;
import com.ejercicio.gui.inscripcion.ListarInscripcion;
import com.ejercicio.gui.profesor.ListarProfesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;

public class EstudianteDetalle extends JFrame {
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JTabbedPane tabbedPane;
    private EstudianteController estudianteController;
    private ProfesorController profesorController;
    private InscripcionController inscripcionController;
    private CursoController cursoController;
    private JPanel panelEdicionEstudiante; // Panel para mostrar el formulario de edición

    public EstudianteDetalle() {
        Connection connection = ConexionController.obtenerConexion();
        this.estudianteController = new EstudianteController(connection);
        this.profesorController = new ProfesorController(connection);
        this.cursoController = new CursoController(connection);
        this.inscripcionController = new InscripcionController(connection);

        setTitle("Estudiante - Detalle");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        txtCodigo = new JTextField(10);
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarEstudiante());
        searchPanel.add(new JLabel("Id:"));
        searchPanel.add(txtCodigo);
        searchPanel.add(btnBuscar);
        mainPanel.add(searchPanel, BorderLayout.NORTH);


        panelEdicionEstudiante = new JPanel(new BorderLayout());
        panelEdicionEstudiante.setBorder(new EmptyBorder(5, 10, 5, 10));

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Historial Cursos", new ListarInscripcion(inscripcionController, null));
        tabbedPane.add("Inscribir Curso", new AgregarInscripcion(inscripcionController, null));
        tabbedPane.add("Cursos", new ListarCurso(cursoController, null));
        tabbedPane.add("Docentes", new ListarProfesor(profesorController, null));


        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(panelEdicionEstudiante, BorderLayout.NORTH);
        centerPanel.add(tabbedPane, BorderLayout.CENTER);


        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private void buscarEstudiante() {
        try {
            int id = Integer.parseInt(txtCodigo.getText().trim());
            JPanel edicion = new EditarEstudiante(id, estudianteController, null);

            panelEdicionEstudiante.removeAll();
            panelEdicionEstudiante.add(edicion, BorderLayout.CENTER);
            panelEdicionEstudiante.revalidate();
            panelEdicionEstudiante.repaint();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un código válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
