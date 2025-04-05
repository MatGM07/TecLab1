package com.ejercicio.gui.profesor;

import com.ejercicio.DAOServicios.ProfesorService;
import com.ejercicio.controlador.ProfesorController;
import com.ejercicio.gui.profesor.PanelProfesor;
import com.ejercicio.modelos.Profesor;
import com.ejercicio.observador.ProfesorObservable;
import com.ejercicio.observador.ProfesorObserver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarProfesor extends JPanel implements ProfesorObserver {
    private ProfesorController profesorController;
    private PanelProfesor panelProfesor;
    private JTable tablaProfesors;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarProfesor(ProfesorController profesorController, PanelProfesor panelProfesor) {
        this.profesorController = profesorController;
        this.panelProfesor = panelProfesor;

        ProfesorObservable.agregarObservador(this);

        setLayout(new BorderLayout());

        setBorder(new EmptyBorder(20, 20, 20, 20));

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellidos", "Email", "Tipo de Contrato"}, 0);
        tablaProfesors = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProfesors);

        add(scrollPane, BorderLayout.CENTER);


        JPanel panelBotones = new JPanel();
        if (panelProfesor != null) {
            btnVolver = new JButton("Volver");
            btnVolver.addActionListener(e -> panelProfesor.mostrarVistaPrincipal());
            panelBotones.add(btnVolver);
        }

        add(panelBotones, BorderLayout.SOUTH);


        cargarDatos();
    }

    @Override
    public void actualizarLista() {
        cargarDatos();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<List<String>> profesores = profesorController.obtenerTodasLosProfesores();

        for (List<String> p : profesores) {
            modeloTabla.addRow(new Object[]{p.get(0), p.get(1), p.get(2), p.get(3), p.get(4)});
        }
    }
}
