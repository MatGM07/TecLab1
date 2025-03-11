package com.ejercicio.gui.profesor;

import com.ejercicio.DAOServicios.ProfesorService;
import com.ejercicio.controlador.ProfesorController;
import com.ejercicio.gui.profesor.PanelProfesor;
import com.ejercicio.modelos.Profesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class EliminarProfesor extends JPanel {
    private ProfesorController profesorController;
    private PanelProfesor panelProfesor;

    public EliminarProfesor(Integer id, ProfesorController profesorController, PanelProfesor panelProfesor) {
        this.profesorController = profesorController;
        this.panelProfesor = panelProfesor;

        List<String> datosProfesor = profesorController.obtenerDatosPorId(id);

        setLayout(new GridLayout(5, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("¿Está seguro de que desea eliminar esta profesor?"));

        add(new JLabel("ID: " + id));
        add(new JLabel("Nombre: " + datosProfesor.get(0)));
        add(new JLabel("Apellido: " + datosProfesor.get(1)));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarProfesor(id));
        btnCancelar.addActionListener(e -> panelProfesor.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarProfesor(Integer id) {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta profesor?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                profesorController.eliminar(id);
                JOptionPane.showMessageDialog(this, "Profesor eliminado correctamente");
                panelProfesor.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar al profesor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
