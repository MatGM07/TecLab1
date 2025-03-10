package com.ejercicio.gui.estudiante;


import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.controlador.EstudianteController;
import com.ejercicio.gui.estudiante.PanelEstudiante;
import com.ejercicio.modelos.Estudiante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarEstudiante extends JPanel {
    private EstudianteController estudianteController;
    private PanelEstudiante panelEstudiante;
    private JTable tablaEstudiantes;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarEstudiante(EstudianteController estudianteController, PanelEstudiante panelEstudiante) {
        this.estudianteController = estudianteController;
        this.panelEstudiante = panelEstudiante;

        setLayout(new BorderLayout());

        setBorder(new EmptyBorder(20, 20, 20, 20));

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellidos", "Email", "Codigo", "Activo", "Promedio", "ID Programa"}, 0);
        tablaEstudiantes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);

        add(scrollPane, BorderLayout.CENTER);


        JPanel panelBotones = new JPanel();
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelEstudiante.mostrarVistaPrincipal());
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);


        cargarDatos();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<List<String>> estudiantes = estudianteController.obtenerTodosLosEstudiantes();

        for (List<String> e : estudiantes) {
            modeloTabla.addRow(new Object[]{e.get(0), e.get(1), e.get(2), e.get(3), e.get(4), e.get(5), e.get(6), e.get(7)});
        }
    }
}
