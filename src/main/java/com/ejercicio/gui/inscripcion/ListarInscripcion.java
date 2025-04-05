package com.ejercicio.gui.inscripcion;

import com.ejercicio.DAOServicios.InscripcionService;
import com.ejercicio.controlador.InscripcionController;
import com.ejercicio.gui.inscripcion.PanelInscripcion;
import com.ejercicio.modelos.CursosInscritos;
import com.ejercicio.modelos.Inscripción;
import com.ejercicio.observador.InscripcionObservable;
import com.ejercicio.observador.InscripcionObserver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarInscripcion extends JPanel implements InscripcionObserver {
    private InscripcionController inscripcionController;
    private PanelInscripcion panelInscripcion;
    private JTable tablaInscripciones;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarInscripcion(InscripcionController inscripcionController, PanelInscripcion panelInscripcion) {
        this.inscripcionController = inscripcionController;
        this.panelInscripcion = panelInscripcion;

        InscripcionObservable.agregarObservador(this);

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));


        modeloTabla = new DefaultTableModel(new String[]{"ID Estudiante", "ID Curso", "Año", "Semestre"}, 0);
        tablaInscripciones = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaInscripciones);

        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        if (panelInscripcion != null){
            btnVolver = new JButton("Volver");
            btnVolver.addActionListener(e -> panelInscripcion.mostrarVistaPrincipal());
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

        List<List<String>> inscripciones = inscripcionController.obtenerTodasLasInscripciones();

        for (List<String> i : inscripciones) {
            modeloTabla.addRow(new Object[]{i.get(0), i.get(1), i.get(2), i.get(3)});
        }
    }
}