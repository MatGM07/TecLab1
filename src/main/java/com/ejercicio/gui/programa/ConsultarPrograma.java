package com.ejercicio.gui.programa;

import com.ejercicio.gui.programa.PanelPrograma;
import com.ejercicio.modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConsultarPrograma extends JPanel{
    private Programa programa;
    private PanelPrograma panelPrograma;

    public ConsultarPrograma(Programa programa, PanelPrograma panelPrograma) {
        this.programa = programa;
        this.panelPrograma = panelPrograma;

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding

        add(new JLabel("Información de la Programa"));

        add(new JLabel("ID: " + programa.getID()));
        add(new JLabel("Nombre: " + programa.getNombre()));
        add(new JLabel("Duracion: " + programa.getDuracion()));
        add(new JLabel("Fecha de Registro: " + programa.getRegistro()));
        add(new JLabel("ID Facultad: " + programa.getFacultad().getID()));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelPrograma.mostrarVistaPrincipal());
        add(btnVolver);
    }
}
