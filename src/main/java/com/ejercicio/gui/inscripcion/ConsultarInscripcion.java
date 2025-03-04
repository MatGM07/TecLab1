package com.ejercicio.gui.inscripcion;

import com.ejercicio.modelos.Inscripción;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConsultarInscripcion extends JPanel {
    private Inscripción inscripcion;
    private PanelInscripcion panelInscripcion;

    public ConsultarInscripcion(Inscripción inscripcion, PanelInscripcion panelInscripcion) {
        this.inscripcion = inscripcion;
        this.panelInscripcion = panelInscripcion;

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding

        add(new JLabel("Información de la Inscripción"));

        add(new JLabel("Estudiante ID: " + inscripcion.getEstudiante().getID()));
        add(new JLabel("Estudiante Nombre: " + inscripcion.getEstudiante().getNombre()));
        add(new JLabel("Curso ID: " + inscripcion.getCurso().getID()));
        add(new JLabel("Curso Nombre: " + inscripcion.getCurso().getNombre()));
        add(new JLabel("Año: " + inscripcion.getAño()));
        add(new JLabel("Semestre: " + inscripcion.getSemestre()));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelInscripcion.mostrarVistaPrincipal());
        add(btnVolver);
    }
}