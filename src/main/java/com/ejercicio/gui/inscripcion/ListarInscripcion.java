package com.ejercicio.gui.inscripcion;

import com.ejercicio.DAOServicios.InscripcionService;
import com.ejercicio.gui.inscripcion.PanelInscripcion;
import com.ejercicio.modelos.Inscripción;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarInscripcion extends JPanel {
    private InscripcionService inscripcionService;
    private PanelInscripcion panelInscripcion;
    private JTable tablaInscripciones;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarInscripcion(InscripcionService inscripcionService, PanelInscripcion panelInscripcion) {
        this.inscripcionService = inscripcionService;
        this.panelInscripcion = panelInscripcion;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));


        modeloTabla = new DefaultTableModel(new String[]{"ID Estudiante", "ID Curso", "Año", "Semestre"}, 0);
        tablaInscripciones = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaInscripciones);

        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelInscripcion.mostrarVistaPrincipal());
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        cargarDatos();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);

        List<Inscripción> inscripciones = inscripcionService.obtenerTodasLasInscripciones();

        for (Inscripción i : inscripciones) {
            modeloTabla.addRow(new Object[]{i.getEstudiante().getID(), i.getCurso().getID(), i.getAño(), i.getSemestre()});
        }
    }
}