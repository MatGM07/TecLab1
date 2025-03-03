package com.ejercicio.gui.estudiante;

import com.ejercicio.gui.estudiante.PanelEstudiante;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Persona;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConsultarEstudiante extends JPanel {
    private Estudiante estudiante;
    private PanelEstudiante panelEstudiante;

    public ConsultarEstudiante(Estudiante estudiante, Persona persona, PanelEstudiante panelEstudiante) {
        this.estudiante = estudiante;
        this.panelEstudiante = panelEstudiante;

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Información de la Estudiante"));

        add(new JLabel("ID: " + estudiante.getID()));
        add(new JLabel("Codigo: " + estudiante.getCodigo()));
        add(new JLabel("Activo: " + estudiante.getActivo()));
        add(new JLabel("Promedio: " + estudiante.getPromedio()));
        add(new JLabel("ID Programa: " + estudiante.getPrograma().getID()));
        add(new JLabel("ID Persona: " + persona.getID()));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelEstudiante.mostrarVistaPrincipal());
        add(btnVolver);
    }
}

