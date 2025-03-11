package com.ejercicio.gui.inscripcion;

import com.ejercicio.controlador.InscripcionController;
import com.ejercicio.modelos.Inscripción;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ConsultarInscripcion extends JPanel {
    private InscripcionController inscripcionController;
    private PanelInscripcion panelInscripcion;

    public ConsultarInscripcion(Integer estudiante_id, Integer curso_id,  InscripcionController inscripcionController, PanelInscripcion panelInscripcion) {
        this.inscripcionController = inscripcionController;
        this.panelInscripcion = panelInscripcion;

        List<String> datosInscripcion = inscripcionController.obtenerDatosPorId(estudiante_id,curso_id);

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Información de la Relación"));

        add(new JLabel("Estudiante ID: " + estudiante_id));
        add(new JLabel("Curso ID: " + curso_id));
        add(new JLabel("Año: " + datosInscripcion.get(0)));
        add(new JLabel("Semestre: " + datosInscripcion.get(1)));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelInscripcion.mostrarVistaPrincipal());
        add(btnVolver);
    }
}