package com.ejercicio.gui.estudiante;

import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.controlador.EstudianteController;
import com.ejercicio.gui.estudiante.PanelEstudiante;
import com.ejercicio.modelos.Estudiante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EliminarEstudiante extends JPanel {
    private EstudianteController estudianteController;
    private PanelEstudiante panelEstudiante;

    public EliminarEstudiante(Integer id, EstudianteController estudianteController, PanelEstudiante panelEstudiante) {
        this.estudianteController = estudianteController;
        this.panelEstudiante = panelEstudiante;

        List<String> datosEstudiantes = estudianteController.obtenerDatosPorId(id);

        setLayout(new GridLayout(5, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("¿Está seguro de que desea eliminar este estudiante?"));

        add(new JLabel("ID: " + id));
        add(new JLabel("Nombre: " + datosEstudiantes.get(0)));
        add(new JLabel("Apellido: " + datosEstudiantes.get(1)));
        add(new JLabel("Codigo: " + datosEstudiantes.get(3)));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarEstudiante(id));
        btnCancelar.addActionListener(e -> panelEstudiante.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarEstudiante(Integer id) {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este estudiante?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                estudianteController.eliminar(id);
                JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente");
                panelEstudiante.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar al estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
