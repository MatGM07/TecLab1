package com.ejercicio.gui.curso;

import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.gui.curso.PanelCurso;
import com.ejercicio.modelos.Curso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EliminarCurso extends JPanel{
    private Curso curso;
    private CursoService cursoService;
    private PanelCurso panelCurso;

    public EliminarCurso(Curso curso, CursoService cursoService, PanelCurso panelCurso) {
        this.curso = curso;
        this.cursoService = cursoService;
        this.panelCurso = panelCurso;

        setLayout(new GridLayout(5, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("¿Está seguro de que desea eliminar este curso?"));

        add(new JLabel("ID: " + curso.getID()));
        add(new JLabel("Nombre: " + curso.getNombre()));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarCurso());
        btnCancelar.addActionListener(e -> panelCurso.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarCurso() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este curso?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                cursoService.eliminarCurso(curso.getID());
                JOptionPane.showMessageDialog(this, "Curso eliminada correctamente");
                panelCurso.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la curso: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
