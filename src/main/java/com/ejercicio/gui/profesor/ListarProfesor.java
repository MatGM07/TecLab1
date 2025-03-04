package com.ejercicio.gui.profesor;

import com.ejercicio.DAOServicios.ProfesorService;
import com.ejercicio.gui.profesor.PanelProfesor;
import com.ejercicio.modelos.Profesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarProfesor extends JPanel {
    private ProfesorService profesorService;
    private PanelProfesor panelProfesor;
    private JTable tablaProfesors;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarProfesor(ProfesorService profesorService, PanelProfesor panelProfesor) {
        this.profesorService = profesorService;
        this.panelProfesor = panelProfesor;

        setLayout(new BorderLayout());

        setBorder(new EmptyBorder(20, 20, 20, 20));

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellidos", "Email", "Tipo de Contrato"}, 0);
        tablaProfesors = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProfesors);

        add(scrollPane, BorderLayout.CENTER);


        JPanel panelBotones = new JPanel();
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelProfesor.mostrarVistaPrincipal());
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);


        cargarDatos();
    }

    private void cargarDatos() {

        modeloTabla.setRowCount(0);

        List<Profesor> profesores = profesorService.obtenerTodosLosProfesores();

        for (Profesor p : profesores) {
            modeloTabla.addRow(new Object[]{p.getID(), p.getNombre(), p.getApellidos(), p.getEmail(), p.getTipoContrato()});
        }
    }
}
