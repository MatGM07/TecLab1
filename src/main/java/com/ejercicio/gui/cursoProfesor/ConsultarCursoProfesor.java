package com.ejercicio.gui.cursoProfesor;

import com.ejercicio.gui.cursoProfesor.PanelCursoProfesor;
import com.ejercicio.modelos.CursoProfesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConsultarCursoProfesor extends JPanel {
    private CursoProfesor cursoProfesor;
    private PanelCursoProfesor panelCursoProfesor;

    public ConsultarCursoProfesor(CursoProfesor cursoProfesor, PanelCursoProfesor panelCursoProfesor) {
        this.cursoProfesor = cursoProfesor;
        this.panelCursoProfesor = panelCursoProfesor;

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Información de la relación CursoProfesor"));

        add(new JLabel("Profesor ID: " + cursoProfesor.getProfesor().getID()));
        add(new JLabel("Profesor Nombre: " + cursoProfesor.getProfesor().getNombre()));
        add(new JLabel("Curso ID: " + cursoProfesor.getCurso().getID()));
        add(new JLabel("Curso Nombre: " + cursoProfesor.getCurso().getNombre()));
        add(new JLabel("Año: " + cursoProfesor.getAño()));
        add(new JLabel("Semestre: " + cursoProfesor.getSemestre()));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelCursoProfesor.mostrarVistaPrincipal());
        add(btnVolver);
    }
}