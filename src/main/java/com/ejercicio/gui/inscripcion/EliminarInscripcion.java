package com.ejercicio.gui.inscripcion;

import com.ejercicio.controlador.InscripcionController;
import com.ejercicio.modelos.CursosInscritos;
import com.ejercicio.modelos.Inscripción;
import com.ejercicio.DAOServicios.InscripcionService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class EliminarInscripcion extends JPanel {
    private InscripcionController inscripcionController;
    private PanelInscripcion panelInscripcion;

    public EliminarInscripcion(Integer estudiante_id, Integer curso_id, InscripcionController inscripcionController, PanelInscripcion panelInscripcion) {
        this.inscripcionController = inscripcionController;
        this.panelInscripcion = panelInscripcion;

        List<String> datosInscripciones = inscripcionController.obtenerDatosPorId(estudiante_id,curso_id);

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding

        add(new JLabel("¿Está seguro de que desea eliminar esta inscripción?"));

        add(new JLabel("Estudiante ID: " + estudiante_id));
        add(new JLabel("Curso ID: " + curso_id));
        add(new JLabel("Año: " + datosInscripciones.get(0) + " | Semestre: " + datosInscripciones.get(1)));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarInscripcion(estudiante_id,curso_id));
        btnCancelar.addActionListener(e -> panelInscripcion.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarInscripcion(Integer estudiante_id, Integer curso_id) {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta inscripción?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                inscripcionController.eliminar(estudiante_id,curso_id);

                JOptionPane.showMessageDialog(this, "Inscripción eliminada correctamente");
                panelInscripcion.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la inscripción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
