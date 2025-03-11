package com.ejercicio.gui.persona;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

import com.ejercicio.controlador.PersonaController;
import com.ejercicio.modelos.Persona;

public class ConsultarPersona extends JPanel {
    private PersonaController personaController;
    private PanelPersona panelPersona;

    public ConsultarPersona(Integer id, PersonaController personaController, PanelPersona panelPersona) {
        this.personaController = personaController;
        this.panelPersona = panelPersona;

        List<String> datosPersona = personaController.obtenerDatosPorId(id);

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding

        add(new JLabel("Información de la Persona"));

        add(new JLabel("ID: " + id));
        add(new JLabel("Nombre: " + datosPersona.get(0)));
        add(new JLabel("Apellidos: " + datosPersona.get(1)));
        add(new JLabel("Email: " + datosPersona.get(2)));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelPersona.mostrarVistaPrincipal());
        add(btnVolver);
    }
}
