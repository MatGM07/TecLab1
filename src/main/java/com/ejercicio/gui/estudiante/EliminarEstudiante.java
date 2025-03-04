package com.ejercicio.gui.estudiante;

import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.gui.estudiante.PanelEstudiante;
import com.ejercicio.modelos.Estudiante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EliminarEstudiante extends JPanel {
    private Estudiante estudiante;
    private EstudianteService estudianteService;
    private PanelEstudiante panelEstudiante;

    public EliminarEstudiante(Estudiante estudiante, EstudianteService estudianteService, PanelEstudiante panelEstudiante) {
        this.estudiante = estudiante;
        this.estudianteService = estudianteService;
        this.panelEstudiante = panelEstudiante;

        setLayout(new GridLayout(5, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("¿Está seguro de que desea eliminar este estudiante?"));

        add(new JLabel("ID: " + estudiante.getID()));
        add(new JLabel("Nombre: " + estudiante.getNombre()));
        add(new JLabel("Apellido: " + estudiante.getApellidos()));
        add(new JLabel("Codigo: " + estudiante.getCodigo()));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarEstudiante());
        btnCancelar.addActionListener(e -> panelEstudiante.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarEstudiante() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este estudiante?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                estudianteService.eliminarEstudiante(estudiante.getID());
                JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente");
                panelEstudiante.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar al estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
