package com.ejercicio.gui.facultad;

import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.controlador.FacultadController;
import com.ejercicio.gui.facultad.PanelFacultad;
import com.ejercicio.modelos.Facultad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class EliminarFacultad extends JPanel {
    private FacultadController facultadController;
    private PanelFacultad panelFacultad;

    public EliminarFacultad(Integer id, FacultadController facultadController, PanelFacultad panelFacultad) {
        this.facultadController = facultadController;
        this.panelFacultad = panelFacultad;

        List<String> datosFacultad = facultadController.obtenerDatosPorId(id);

        setLayout(new GridLayout(5, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("¿Está seguro de que desea eliminar esta facultad?"));

        add(new JLabel("ID: " + id));
        add(new JLabel("Nombre: " + datosFacultad.get(0)));
        add(new JLabel("ID Decano: " + datosFacultad.get(1)));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarFacultad(id));
        btnCancelar.addActionListener(e -> panelFacultad.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarFacultad(Integer id) {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta facultad?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                facultadController.eliminar(id);
                JOptionPane.showMessageDialog(this, "Facultad eliminada correctamente");
                panelFacultad.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la facultad: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
