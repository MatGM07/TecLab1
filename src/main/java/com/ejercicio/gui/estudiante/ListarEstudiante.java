package com.ejercicio.gui.estudiante;


import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.gui.estudiante.PanelEstudiante;
import com.ejercicio.modelos.Estudiante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarEstudiante extends JPanel {
    private EstudianteService estudianteService;
    private PanelEstudiante panelEstudiante;
    private PersonaService personaService;
    private JTable tablaEstudiantes;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarEstudiante(EstudianteService estudianteService, PanelEstudiante panelEstudiante) {
        this.estudianteService = estudianteService;
        this.panelEstudiante = panelEstudiante;

        setLayout(new BorderLayout());

        setBorder(new EmptyBorder(20, 20, 20, 20));

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Codigo", "Activo", "Promedio", "ID Programa"}, 0);
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

        List<Estudiante> estudiantes = estudianteService.obtenerTodosLosEstudiantes();

        for (Estudiante p : estudiantes) {
            modeloTabla.addRow(new Object[]{p.getID(), p.getCodigo(), p.getActivo(), p.getPromedio(), p.getPrograma().getID()});
        }
    }
}
