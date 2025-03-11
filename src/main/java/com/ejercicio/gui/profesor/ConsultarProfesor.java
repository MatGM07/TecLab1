package com.ejercicio.gui.profesor;

import com.ejercicio.controlador.ProfesorController;
import com.ejercicio.gui.profesor.PanelProfesor;
import com.ejercicio.modelos.Profesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ConsultarProfesor extends JPanel {
    private ProfesorController profesorController;
    private PanelProfesor panelProfesor;

    public ConsultarProfesor(Integer id, ProfesorController profesorController, PanelProfesor panelProfesor) {
        this.profesorController = profesorController;
        this.panelProfesor = panelProfesor;

        List<String> datosProfesor = profesorController.obtenerDatosPorId(id);

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Información del Profesor"));

        add(new JLabel("ID: " + id));
        add(new JLabel("Nombres: " + datosProfesor.get(0)));
        add(new JLabel("Apellidos: " + datosProfesor.get(1)));
        add(new JLabel("Correo: " + datosProfesor.get(2)));
        add(new JLabel("Tipo de Contrato: " + datosProfesor.get(3)));


        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelProfesor.mostrarVistaPrincipal());
        add(btnVolver);
    }
}

