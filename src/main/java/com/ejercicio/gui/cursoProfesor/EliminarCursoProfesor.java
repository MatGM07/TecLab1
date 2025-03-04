package com.ejercicio.gui.cursoProfesor;

import com.ejercicio.DAOServicios.CursoProfesorService;
import com.ejercicio.gui.cursoProfesor.PanelCursoProfesor;
import com.ejercicio.modelos.CursoProfesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EliminarCursoProfesor extends JPanel {
    private CursoProfesor cursoProfesor;
    private CursoProfesorService cursoProfesorService;
    private PanelCursoProfesor panelCursoProfesor;

    public EliminarCursoProfesor(CursoProfesor cursoProfesor, CursoProfesorService cursoProfesorService, PanelCursoProfesor panelCursoProfesor) {
        this.cursoProfesor = cursoProfesor;
        this.cursoProfesorService = cursoProfesorService;
        this.panelCursoProfesor = panelCursoProfesor;

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding

        add(new JLabel("¿Está seguro de que desea eliminar esta inscripción?"));

        add(new JLabel("Profesor ID: " + cursoProfesor.getProfesor().getID()));
        add(new JLabel("Profesor Nombre: " + cursoProfesor.getProfesor().getNombre()));
        add(new JLabel("Curso ID: " + cursoProfesor.getCurso().getID()));
        add(new JLabel("Curso Nombre: " + cursoProfesor.getCurso().getNombre()));
        add(new JLabel("Año: " + cursoProfesor.getAño() + " | Semestre: " + cursoProfesor.getSemestre()));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarCursoProfesor());
        btnCancelar.addActionListener(e -> panelCursoProfesor.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarCursoProfesor() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta inscripción?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                cursoProfesorService.eliminarCursoProfesor(cursoProfesor.getProfesor().getID(),cursoProfesor.getCurso().getID());
                JOptionPane.showMessageDialog(this, "CursoProfesor eliminada correctamente");
                panelCursoProfesor.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la inscripción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
