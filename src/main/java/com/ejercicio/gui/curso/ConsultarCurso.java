package com.ejercicio.gui.curso;

import com.ejercicio.gui.curso.PanelCurso;
import com.ejercicio.modelos.Curso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConsultarCurso extends JPanel {
    private Curso curso;
    private PanelCurso panelCurso;

    public ConsultarCurso(Curso curso, PanelCurso panelCurso) {
        this.curso = curso;
        this.panelCurso = panelCurso;

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding

        add(new JLabel("Información de la Curso"));

        add(new JLabel("ID: " + curso.getID()));
        add(new JLabel("Nombre: " + curso.getNombre()));
        add(new JLabel("Activo: " + curso.getActivo()));
        add(new JLabel("ID Facultad: " + curso.getPrograma().getID()));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelCurso.mostrarVistaPrincipal());
        add(btnVolver);
    }
}
