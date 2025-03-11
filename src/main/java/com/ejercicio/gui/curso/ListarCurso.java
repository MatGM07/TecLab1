package com.ejercicio.gui.curso;

import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.controlador.CursoController;
import com.ejercicio.gui.curso.PanelCurso;
import com.ejercicio.modelos.Curso;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarCurso extends JPanel {
    private CursoController cursoController;
    private PanelCurso panelCurso;
    private JTable tablaCursos;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarCurso(CursoController cursoController, PanelCurso panelCurso) {
        this.cursoController = cursoController;
        this.panelCurso = panelCurso;

        setLayout(new BorderLayout());

        setBorder(new EmptyBorder(20, 20, 20, 20));

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Activo", "ID Programa"}, 0);
        tablaCursos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaCursos);

        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelCurso.mostrarVistaPrincipal());
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        cargarDatos();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);

        List<List<String>> cursos = cursoController.obtenerTodosLosCursos();

        for (List<String> p : cursos) {
            modeloTabla.addRow(new Object[]{p.get(0), p.get(1), p.get(2), p.get(3)});
        }
    }
}
