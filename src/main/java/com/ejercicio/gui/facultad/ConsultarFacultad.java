package com.ejercicio.gui.facultad;

import com.ejercicio.gui.facultad.PanelFacultad;
import com.ejercicio.modelos.Facultad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConsultarFacultad extends JPanel {
    private Facultad facultad;
    private PanelFacultad panelFacultad;

    public ConsultarFacultad(Facultad facultad, PanelFacultad panelFacultad) {
        this.facultad = facultad;
        this.panelFacultad = panelFacultad;

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Información de la Facultad"));

        add(new JLabel("ID: " + facultad.getID()));
        add(new JLabel("Nombre: " + facultad.getNombre()));
        add(new JLabel("ID Decano: " + facultad.getDecano().getID()));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelFacultad.mostrarVistaPrincipal());
        add(btnVolver);
    }
}
