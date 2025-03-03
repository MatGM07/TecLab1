package com.ejercicio.gui.facultad;

import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.gui.facultad.PanelFacultad;
import com.ejercicio.modelos.Facultad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EliminarFacultad extends JPanel {
    private Facultad facultad;
    private FacultadService facultadService;
    private PanelFacultad panelFacultad;

    public EliminarFacultad(Facultad facultad, FacultadService facultadService, PanelFacultad panelFacultad) {
        this.facultad = facultad;
        this.facultadService = facultadService;
        this.panelFacultad = panelFacultad;

        setLayout(new GridLayout(5, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding

        add(new JLabel("¿Está seguro de que desea eliminar esta facultad?"));

        add(new JLabel("ID: " + facultad.getID()));
        add(new JLabel("Nombre: " + facultad.getNombre()));
        add(new JLabel("ID Decano: " + facultad.getDecano().getID()));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarFacultad());
        btnCancelar.addActionListener(e -> panelFacultad.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarFacultad() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta facultad?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                facultadService.eliminarFacultad(facultad.getID());
                JOptionPane.showMessageDialog(this, "Facultad eliminada correctamente");
                panelFacultad.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la facultad: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
