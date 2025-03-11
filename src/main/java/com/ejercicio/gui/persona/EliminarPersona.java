package com.ejercicio.gui.persona;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.controlador.PersonaController;
import com.ejercicio.modelos.InscripcionesPersonas;
import com.ejercicio.modelos.Persona;

public class EliminarPersona extends JPanel {
    private PersonaController personaController;
    private PanelPersona panelPersona;

    public EliminarPersona(Integer id, PersonaController personaController, PanelPersona panelPersona) {
        this.personaController = personaController;
        this.panelPersona = panelPersona;

        List<String> datosPersona = personaController.obtenerDatosPorId(id);

        setLayout(new GridLayout(5, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("¿Está seguro de que desea eliminar esta persona?"));

        add(new JLabel("ID: " + id));
        add(new JLabel("Nombre: " + datosPersona.get(0)));
        add(new JLabel("Apellidos: " + datosPersona.get(1)));
        add(new JLabel("Email: " + datosPersona.get(2)));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarPersona(id));
        btnCancelar.addActionListener(e -> panelPersona.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarPersona(Integer id) {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta persona?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                personaController.eliminar(id);

                JOptionPane.showMessageDialog(this, "Persona eliminada correctamente");
                panelPersona.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la persona: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
