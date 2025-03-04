package com.ejercicio.gui.inscripcion;

import com.ejercicio.modelos.Inscripción;
import com.ejercicio.DAOServicios.InscripcionService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EliminarInscripcion extends JPanel {
    private Inscripción inscripcion;
    private InscripcionService inscripcionService;
    private PanelInscripcion panelInscripcion;

    public EliminarInscripcion(Inscripción inscripcion, InscripcionService inscripcionService, PanelInscripcion panelInscripcion) {
        this.inscripcion = inscripcion;
        this.inscripcionService = inscripcionService;
        this.panelInscripcion = panelInscripcion;

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding

        add(new JLabel("¿Está seguro de que desea eliminar esta inscripción?"));

        add(new JLabel("Estudiante ID: " + inscripcion.getEstudiante().getID()));
        add(new JLabel("Estudiante Nombre: " + inscripcion.getEstudiante().getNombre()));
        add(new JLabel("Curso ID: " + inscripcion.getCurso().getID()));
        add(new JLabel("Curso Nombre: " + inscripcion.getCurso().getNombre()));
        add(new JLabel("Año: " + inscripcion.getAño() + " | Semestre: " + inscripcion.getSemestre()));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarInscripcion());
        btnCancelar.addActionListener(e -> panelInscripcion.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarInscripcion() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta inscripción?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                inscripcionService.eliminarInscripcion(inscripcion.getEstudiante().getID(),inscripcion.getCurso().getID());
                JOptionPane.showMessageDialog(this, "Inscripción eliminada correctamente");
                panelInscripcion.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la inscripción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
