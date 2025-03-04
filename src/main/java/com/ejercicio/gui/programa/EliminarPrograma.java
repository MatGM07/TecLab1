package com.ejercicio.gui.programa;

import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.gui.programa.PanelPrograma;
import com.ejercicio.modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EliminarPrograma extends JPanel {
    private Programa programa;
    private ProgramaService programaService;
    private PanelPrograma panelPrograma;

    public EliminarPrograma(Programa programa, ProgramaService programaService, PanelPrograma panelPrograma) {
        this.programa = programa;
        this.programaService = programaService;
        this.panelPrograma = panelPrograma;

        setLayout(new GridLayout(5, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("¿Está seguro de que desea eliminar este programa?"));

        add(new JLabel("ID: " + programa.getID()));
        add(new JLabel("Nombre: " + programa.getNombre()));
        add(new JLabel("Duracion: " + programa.getDuracion()));
        add(new JLabel("Facultad: " + programa.getFacultad().getID()));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarPrograma());
        btnCancelar.addActionListener(e -> panelPrograma.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarPrograma() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este programa?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                programaService.eliminarPrograma(programa.getID());
                JOptionPane.showMessageDialog(this, "Programa eliminada correctamente");
                panelPrograma.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la programa: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
