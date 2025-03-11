package com.ejercicio.gui.curso;

import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.controlador.CursoController;
import com.ejercicio.gui.curso.PanelCurso;
import com.ejercicio.modelos.Curso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class EliminarCurso extends JPanel{
    private CursoController cursoController;
    private PanelCurso panelCurso;

    public EliminarCurso(Integer id, CursoController cursoController, PanelCurso panelCurso) {
        this.cursoController = cursoController;
        this.panelCurso = panelCurso;

        List<String> datosCurso = cursoController.obtenerDatosPorId(id);

        setLayout(new GridLayout(5, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("¿Está seguro de que desea eliminar este curso?"));

        add(new JLabel("ID: " + id));
        add(new JLabel("Nombre: " + datosCurso.get(0)));

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminarCurso(id));
        btnCancelar.addActionListener(e -> panelCurso.mostrarVistaPrincipal());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones);
    }

    private void eliminarCurso(Integer id) {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este curso?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                cursoController.eliminar(id);
                JOptionPane.showMessageDialog(this, "Curso eliminada correctamente");
                panelCurso.mostrarVistaPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la curso: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
