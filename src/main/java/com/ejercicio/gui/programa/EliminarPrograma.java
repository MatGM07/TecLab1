package com.ejercicio.gui.programa;

import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.controlador.ProgramaController;
import com.ejercicio.gui.programa.PanelPrograma;
import com.ejercicio.modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class EliminarPrograma extends JPanel {
    private ProgramaController programaController;
    private PanelPrograma panelPrograma;

    public EliminarPrograma(Integer id, ProgramaController programaController, PanelPrograma panelPrograma) {
        this.programaController = programaController;
        this.panelPrograma = panelPrograma;

        List<String> datosPrograma = programaController.obtenerDatosPorId(id);

        setLayout(new GridLayout(5, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("¿Está seguro de que desea eliminar este programa?"));

        add(new JLabel("ID: " + id));
        add(new JLabel("Nombre: " + datosPrograma.get(0)));
        add(new JLabel("Duracion: " + datosPrograma.get(1)));
        add(new JLabel("Facultad: " + datosPrograma.get(3)));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarPrograma(id));
        btnCancelar.addActionListener(e -> panelPrograma.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarPrograma(Integer id) {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este programa?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                programaController.eliminar(id);
                JOptionPane.showMessageDialog(this, "Programa eliminada correctamente");
                panelPrograma.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la programa: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
