package com.ejercicio.gui.profesor;

import com.ejercicio.DAOServicios.ProfesorService;
import com.ejercicio.gui.profesor.PanelProfesor;
import com.ejercicio.modelos.Profesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EliminarProfesor extends JPanel {
    private Profesor profesor;
    private ProfesorService profesorService;
    private PanelProfesor panelProfesor;

    public EliminarProfesor(Profesor profesor, ProfesorService profesorService, PanelProfesor panelProfesor) {
        this.profesor = profesor;
        this.profesorService = profesorService;
        this.panelProfesor = panelProfesor;

        setLayout(new GridLayout(5, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("¿Está seguro de que desea eliminar esta profesor?"));

        add(new JLabel("ID: " + profesor.getID()));
        add(new JLabel("Nombre: " + profesor.getNombre()));
        add(new JLabel("Apellido: " + profesor.getApellidos()));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarProfesor());
        btnCancelar.addActionListener(e -> panelProfesor.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarProfesor() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta profesor?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                profesorService.eliminarProfesor(profesor.getID());
                JOptionPane.showMessageDialog(this, "Profesor eliminado correctamente");
                panelProfesor.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar al profesor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
