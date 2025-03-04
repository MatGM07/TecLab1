package com.ejercicio.gui.profesor;

import com.ejercicio.gui.profesor.PanelProfesor;
import com.ejercicio.modelos.Profesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConsultarProfesor extends JPanel {
    private Profesor profesor;
    private PanelProfesor panelProfesor;

    public ConsultarProfesor(Profesor profesor, PanelProfesor panelProfesor) {
        this.profesor = profesor;
        this.panelProfesor = panelProfesor;

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Información del Profesor"));

        add(new JLabel("ID: " + profesor.getID()));
        add(new JLabel("Nombres: " + profesor.getNombre()));
        add(new JLabel("Apellidos: " + profesor.getApellidos()));
        add(new JLabel("Correo: " + profesor.getEmail()));
        add(new JLabel("Tipo de Contrato: " + profesor.getTipoContrato()));


        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelProfesor.mostrarVistaPrincipal());
        add(btnVolver);
    }
}

