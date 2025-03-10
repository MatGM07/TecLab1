package com.ejercicio.gui.programa;

import com.ejercicio.controlador.ProgramaController;
import com.ejercicio.gui.programa.PanelPrograma;
import com.ejercicio.modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ConsultarPrograma extends JPanel{
    private ProgramaController programaController;
    private PanelPrograma panelPrograma;

    public ConsultarPrograma(Integer id, ProgramaController programaController, PanelPrograma panelPrograma) {
        this.programaController = programaController;
        this.panelPrograma = panelPrograma;

        List<String> datosPrograma = programaController.obtenerDatosPorId(id);

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Información de la Programa"));

        add(new JLabel("ID: " + id));
        add(new JLabel("Nombre: " + datosPrograma.get(0)));
        add(new JLabel("Duracion: " + datosPrograma.get(1)));
        add(new JLabel("Fecha de Registro: " + datosPrograma.get(2)));
        add(new JLabel("ID Facultad: " + datosPrograma.get(3)));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelPrograma.mostrarVistaPrincipal());
        add(btnVolver);
    }
}
