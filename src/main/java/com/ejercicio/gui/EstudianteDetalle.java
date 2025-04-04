package com.ejercicio.gui;

import com.ejercicio.ConexionController;
import com.ejercicio.controlador.EstudianteController;
import com.ejercicio.gui.estudiante.EditarEstudiante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;

public class EstudianteDetalle extends JFrame {
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JTabbedPane tabbedPane;
    private EstudianteController estudianteController;
    private JPanel panelEdicionEstudiante; // Panel para mostrar el formulario de edición

    public EstudianteDetalle() {
        Connection connection = ConexionController.obtenerConexion();
        this.estudianteController = new EstudianteController(connection);

        setTitle("Estudiante - Detalle");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Contenedor principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        txtCodigo = new JTextField(10);
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarEstudiante());
        searchPanel.add(new JLabel("Código:"));
        searchPanel.add(txtCodigo);
        searchPanel.add(btnBuscar);
        mainPanel.add(searchPanel, BorderLayout.NORTH); // Agregar arriba

        // Panel de edición (inicialmente vacío)
        panelEdicionEstudiante = new JPanel(new BorderLayout());
        panelEdicionEstudiante.setBorder(new EmptyBorder(5, 10, 5, 10));
        mainPanel.add(panelEdicionEstudiante, BorderLayout.CENTER); // Ubicado al centro

        // Pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.add("Historial Cursos", new JPanel());
        tabbedPane.add("Inscribir Curso", new JPanel());
        tabbedPane.add("Cursos", new JPanel());
        tabbedPane.add("Docentes", new JPanel());
        mainPanel.add(tabbedPane, BorderLayout.SOUTH); // Agregar pestañas abajo
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
