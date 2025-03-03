package com.ejercicio.gui.persona;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import com.ejercicio.modelos.Persona;

public class ConsultarPersona extends JPanel {
    private Persona persona;
    private PanelPersona panelPersona;

    public ConsultarPersona(Persona persona, PanelPersona panelPersona) {
        this.persona = persona;
        this.panelPersona = panelPersona;

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding

        add(new JLabel("Información de la Persona"));

        add(new JLabel("ID: " + persona.getID()));
        add(new JLabel("Nombre: " + persona.getNombre()));
        add(new JLabel("Apellidos: " + persona.getApellidos()));
        add(new JLabel("Email: " + persona.getEmail()));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelPersona.mostrarVistaPrincipal());
        add(btnVolver);
    }
}
