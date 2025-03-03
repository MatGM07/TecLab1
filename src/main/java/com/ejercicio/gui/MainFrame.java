package com.ejercicio.gui;
import com.ejercicio.gui.facultad.PanelFacultad;
import com.ejercicio.gui.persona.PanelPersona;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private int tabCount = 0;

    public MainFrame() {
        setTitle("Gestión Universitaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        // Panel de inicio con nuevo diseño
        JPanel panelInicio = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre botones
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Crear botones con color verde
        JButton btnPersona = crearBoton("Personas");
        JButton btnEstudiante = crearBoton("Estudiantes");
        JButton btnProfesor = crearBoton("Profesores");
        JButton btnFacultad = crearBoton("Facultades");
        JButton btnPrograma = crearBoton("Programa");
        JButton btnCurso = crearBoton("Curso");
        JButton btnInscripcion = crearBoton("Inscripción");
        JButton btnCursoProfesor = crearBoton("Relación Curso-Profesor");

        // Agregar botones al panel
        gbc.gridy = 0; panelInicio.add(btnPersona, gbc);
        gbc.gridy = 1; panelInicio.add(btnEstudiante, gbc);
        gbc.gridy = 2; panelInicio.add(btnProfesor, gbc);
        gbc.gridy = 3; panelInicio.add(btnFacultad, gbc);
        gbc.gridy = 4; panelInicio.add(btnPrograma, gbc);
        gbc.gridy = 5; panelInicio.add(btnCurso, gbc);
        gbc.gridy = 6; panelInicio.add(btnInscripcion, gbc);
        gbc.gridy = 7; panelInicio.add(btnCursoProfesor, gbc);


        // Agregar eventos para abrir pestañas
        btnPersona.addActionListener(e -> agregarNuevaPestaña(new PanelPersona(this), "Persona " + (++tabCount)));
        btnEstudiante.addActionListener(e -> agregarNuevaPestaña(new PanelEstudiante(this), "Estudiante " + (++tabCount)));
        btnProfesor.addActionListener(e -> agregarNuevaPestaña(new PanelEstudiante(this), "Profesor " + (++tabCount)));
        btnFacultad.addActionListener(e -> agregarNuevaPestaña(new PanelFacultad(this), "Facultad " + (++tabCount)));
        btnPrograma.addActionListener(e -> agregarNuevaPestaña(new PanelEstudiante(this), "Programa " + (++tabCount)));
        btnCurso.addActionListener(e -> agregarNuevaPestaña(new PanelEstudiante(this), "Curso " + (++tabCount)));
        btnInscripcion.addActionListener(e -> agregarNuevaPestaña(new PanelEstudiante(this), "Inscripción " + (++tabCount)));
        btnCursoProfesor.addActionListener(e -> agregarNuevaPestaña(new PanelEstudiante(this), "CursoProfesor " + (++tabCount)));

        tabbedPane.add("Inicio", panelInicio);
        setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return boton;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    private void agregarNuevaPestaña(JPanel panel, String titulo) {
        tabbedPane.addTab(titulo, panel);
        int index = tabbedPane.getTabCount() - 1;
        JPanel tabHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        tabHeader.setOpaque(false);
        JLabel tabTitle = new JLabel(titulo);
        tabHeader.add(tabTitle);
        tabbedPane.setTabComponentAt(index, tabHeader);
        tabbedPane.setSelectedIndex(index);
    }
}