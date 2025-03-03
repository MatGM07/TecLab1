package com.ejercicio.gui.facultad;

import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.gui.facultad.PanelFacultad;
import com.ejercicio.modelos.Facultad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarFacultad extends JPanel {
    private FacultadService facultadService;
    private PanelFacultad panelFacultad;
    private JTable tablaFacultads;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarFacultad(FacultadService facultadService, PanelFacultad panelFacultad) {
        this.facultadService = facultadService;
        this.panelFacultad = panelFacultad;

        setLayout(new BorderLayout());


        setBorder(new EmptyBorder(20, 20, 20, 20));


        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "ID Decano"}, 0);
        tablaFacultads = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaFacultads);

        add(scrollPane, BorderLayout.CENTER);


        JPanel panelBotones = new JPanel();
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelFacultad.mostrarVistaPrincipal());
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);


        cargarDatos();
    }

    private void cargarDatos() {

        modeloTabla.setRowCount(0);

        List<Facultad> facultads = facultadService.obtenerTodasLasFacultades();

        for (Facultad p : facultads) {
            modeloTabla.addRow(new Object[]{p.getID(), p.getNombre(), p.getDecano().getID()});
        }
    }
}
