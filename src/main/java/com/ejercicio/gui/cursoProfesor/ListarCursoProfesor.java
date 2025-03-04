package com.ejercicio.gui.cursoProfesor;

import com.ejercicio.DAOServicios.CursoProfesorService;
import com.ejercicio.gui.cursoProfesor.PanelCursoProfesor;
import com.ejercicio.modelos.CursoProfesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarCursoProfesor extends JPanel{
    private CursoProfesorService cursoProfesorService;
    private PanelCursoProfesor panelCursoProfesor;
    private JTable tablaCursoProfesores;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarCursoProfesor(CursoProfesorService cursoProfesorService, PanelCursoProfesor panelCursoProfesor) {
        this.cursoProfesorService = cursoProfesorService;
        this.panelCursoProfesor = panelCursoProfesor;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));


        modeloTabla = new DefaultTableModel(new String[]{"ID Profesor", "ID Curso", "Año", "Semestre"}, 0);
        tablaCursoProfesores = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaCursoProfesores);

        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelCursoProfesor.mostrarVistaPrincipal());
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        cargarDatos();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);

        List<CursoProfesor> cursoProfesores = cursoProfesorService.obtenerTodosLosCursoProfesores();

        for (CursoProfesor i : cursoProfesores) {
            modeloTabla.addRow(new Object[]{i.getProfesor().getID(), i.getCurso().getID(), i.getAño(), i.getSemestre()});
        }
    }
}
