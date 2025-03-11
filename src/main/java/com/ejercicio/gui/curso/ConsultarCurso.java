package com.ejercicio.gui.curso;

import com.ejercicio.controlador.CursoController;
import com.ejercicio.gui.curso.PanelCurso;
import com.ejercicio.modelos.Curso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ConsultarCurso extends JPanel {
    private CursoController cursoController;
    private PanelCurso panelCurso;

    public ConsultarCurso(Integer id, CursoController cursoController, PanelCurso panelCurso) {
        this.cursoController = cursoController;
        this.panelCurso = panelCurso;

        List<String> datosCurso = cursoController.obtenerDatosPorId(id);

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(new JLabel("Información de la Curso"));

        add(new JLabel("ID: " + id));
        add(new JLabel("Nombre: " + datosCurso.get(0)));
        add(new JLabel("Activo: " + datosCurso.get(1)));
        add(new JLabel("ID Facultad: " + datosCurso.get(2)));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelCurso.mostrarVistaPrincipal());
        add(btnVolver);
    }
}
