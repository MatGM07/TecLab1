package com.ejercicio.gui.facultad;

import com.ejercicio.controlador.FacultadController;
import com.ejercicio.gui.facultad.PanelFacultad;
import com.ejercicio.modelos.Facultad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ConsultarFacultad extends JPanel {
    private FacultadController facultadController;
    private PanelFacultad panelFacultad;

    public ConsultarFacultad(Integer id, FacultadController facultadController, PanelFacultad panelFacultad) {
        this.facultadController = facultadController;
        this.panelFacultad = panelFacultad;

        List<String> datosFacultad = facultadController.obtenerDatosPorId(id);

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Información de la Facultad"));

        add(new JLabel("ID: " + id));
        add(new JLabel("Nombre: " + datosFacultad.get(0)));
        add(new JLabel("ID Decano: " + datosFacultad.get(1)));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelFacultad.mostrarVistaPrincipal());
        add(btnVolver);
    }
}
