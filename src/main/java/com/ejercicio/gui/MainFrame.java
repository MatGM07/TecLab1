package com.ejercicio.gui;
import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.FacultadService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private int tabCount = 0;

    public MainFrame() {
        setTitle("Gestión Universitaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        JPanel panelInicio = new JPanel();
        panelInicio.setLayout(new GridLayout(2, 2));

        JButton btnFacultad = new JButton("Nueva Facultad CRUD");
        JButton btnEstudiante = new JButton("Nuevo Estudiante CRUD");

        btnFacultad.addActionListener(e -> agregarNuevaPestaña(new PanelFacultad(this), "Facultad " + (++tabCount)));
        btnEstudiante.addActionListener(e -> agregarNuevaPestaña(new PanelEstudiante(this), "Estudiante " + (++tabCount)));

        panelInicio.add(btnFacultad);
        panelInicio.add(btnEstudiante);

        tabbedPane.add("Inicio", panelInicio);

        setVisible(true);
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