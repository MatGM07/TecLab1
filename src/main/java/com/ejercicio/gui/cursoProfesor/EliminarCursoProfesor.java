package com.ejercicio.gui.cursoProfesor;

import com.ejercicio.DAOServicios.CursoProfesorService;
import com.ejercicio.controlador.CursoProfesorController;
import com.ejercicio.gui.cursoProfesor.PanelCursoProfesor;
import com.ejercicio.gui.cursoProfesor.PanelCursoProfesor;
import com.ejercicio.modelos.CursoProfesor;
import com.ejercicio.modelos.CursosProfesores;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class EliminarCursoProfesor extends JPanel {
    private CursoProfesorController cursoProfesorController;
    private PanelCursoProfesor panelCursoProfesor;

    public EliminarCursoProfesor(Integer profesor_id, Integer curso_id, CursoProfesorController cursoProfesorController, PanelCursoProfesor panelCursoProfesor) {
        this.cursoProfesorController = cursoProfesorController;
        this.panelCursoProfesor = panelCursoProfesor;

        List<String> datosCursoProfesores = cursoProfesorController.obtenerDatosPorId(profesor_id,curso_id);

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding

        add(new JLabel("¿Está seguro de que desea eliminar esta inscripción?"));

        add(new JLabel("Profesor ID: " + profesor_id));
        add(new JLabel("Curso ID: " + curso_id));
        add(new JLabel("Año: " + datosCursoProfesores.get(0) + " | Semestre: " + datosCursoProfesores.get(1)));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarCursoProfesor(profesor_id,curso_id));
        btnCancelar.addActionListener(e -> panelCursoProfesor.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarCursoProfesor(Integer profesor_id, Integer curso_id) {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta inscripción?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                cursoProfesorController.eliminar(profesor_id,curso_id);

                JOptionPane.showMessageDialog(this, "Inscripción eliminada correctamente");
                panelCursoProfesor.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la inscripción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
