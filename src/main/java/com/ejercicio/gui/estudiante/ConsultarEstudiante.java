package com.ejercicio.gui.estudiante;

import com.ejercicio.controlador.EstudianteController;
import com.ejercicio.gui.estudiante.PanelEstudiante;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Persona;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ConsultarEstudiante extends JPanel {
    private PanelEstudiante panelEstudiante;
    private EstudianteController estudianteController;

    public ConsultarEstudiante(Integer id, EstudianteController estudianteController, PanelEstudiante panelEstudiante) {
        this.panelEstudiante = panelEstudiante;
        this.estudianteController = estudianteController;
        List<String> datosEstudiante = estudianteController.obtenerDatosPorId(id);

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Información del Estudiante"));

        add(new JLabel("ID: " + String.valueOf(id)));
        add(new JLabel("Nombres: " + datosEstudiante.get(0)));
        add(new JLabel("Apellidos: " + datosEstudiante.get(1)));
        add(new JLabel("Correo: " + datosEstudiante.get(2)));
        add(new JLabel("Codigo: " + datosEstudiante.get(3)));
        add(new JLabel("Activo: " + datosEstudiante.get(4)));
        add(new JLabel("ID Programa: " + datosEstudiante.get(5)));
        add(new JLabel("Promedio: " + datosEstudiante.get(6)));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelEstudiante.mostrarVistaPrincipal());
        add(btnVolver);
    }
}

