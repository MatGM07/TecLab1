package com.ejercicio.gui.persona;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.modelos.Persona;

public class EliminarPersona extends JPanel {
    private Persona persona;
    private PersonaService personaService;
    private PanelPersona panelPersona;

    public EliminarPersona(Persona persona, PersonaService personaService, PanelPersona panelPersona) {
        this.persona = persona;
        this.personaService = personaService;
        this.panelPersona = panelPersona;

        setLayout(new GridLayout(5, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("¿Está seguro de que desea eliminar esta persona?"));

        add(new JLabel("ID: " + persona.getID()));
        add(new JLabel("Nombre: " + persona.getNombre()));
        add(new JLabel("Apellidos: " + persona.getApellidos()));
        add(new JLabel("Email: " + persona.getEmail()));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarPersona());
        btnCancelar.addActionListener(e -> panelPersona.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarPersona() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta persona?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                personaService.eliminarPersona(persona.getID());
                JOptionPane.showMessageDialog(this, "Persona eliminada correctamente");
                panelPersona.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la persona: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
