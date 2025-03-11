package com.ejercicio.gui.cursoProfesor;

import com.ejercicio.controlador.CursoProfesorController;
import com.ejercicio.gui.cursoProfesor.PanelCursoProfesor;
import com.ejercicio.gui.cursoProfesor.PanelCursoProfesor;
import com.ejercicio.modelos.CursoProfesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ConsultarCursoProfesor extends JPanel {
    private CursoProfesorController cursoProfesorController;
    private PanelCursoProfesor panelCursoProfesor;

    public ConsultarCursoProfesor(Integer profesor_id, Integer curso_id,  CursoProfesorController cursoProfesorController, PanelCursoProfesor panelCursoProfesor) {
        this.cursoProfesorController = cursoProfesorController;
        this.panelCursoProfesor = panelCursoProfesor;

        List<String> datosCursoProfesor = cursoProfesorController.obtenerDatosPorId(profesor_id,curso_id);

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Información de la Relación"));

        add(new JLabel("Profesor ID: " + profesor_id));
        add(new JLabel("Curso ID: " + curso_id));
        add(new JLabel("Año: " + datosCursoProfesor.get(0)));
        add(new JLabel("Semestre: " + datosCursoProfesor.get(1)));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelCursoProfesor.mostrarVistaPrincipal());
        add(btnVolver);
    }
}