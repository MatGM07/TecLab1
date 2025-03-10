package com.ejercicio.gui.programa;

import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.controlador.ProgramaController;
import com.ejercicio.gui.programa.PanelPrograma;
import com.ejercicio.modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarPrograma extends JPanel {
    private ProgramaController programaController;
    private PanelPrograma panelPrograma;
    private JTable tablaProgramas;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarPrograma(ProgramaController programaController, PanelPrograma panelPrograma) {
        this.programaController = programaController;
        this.panelPrograma = panelPrograma;

        setLayout(new BorderLayout());


        setBorder(new EmptyBorder(20, 20, 20, 20));


        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Duracion", "Fecha Registro", "ID Facultad"}, 0);
        tablaProgramas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProgramas);

        add(scrollPane, BorderLayout.CENTER);


        JPanel panelBotones = new JPanel();
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelPrograma.mostrarVistaPrincipal());
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);


        cargarDatos();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);

        List<List<String>> programas = programaController.obtenerTodosLosProgramas();

        for (List<String> p : programas) {
            modeloTabla.addRow(new Object[]{p.get(0), p.get(1), p.get(2), p.get(3), p.get(4)});
        }
    }
}
