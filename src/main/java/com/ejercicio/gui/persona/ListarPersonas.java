package com.ejercicio.gui.persona;

import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.modelos.InscripcionesPersonas;
import com.ejercicio.modelos.Persona;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarPersonas extends JPanel {
    private PersonaService personaService;
    private PanelPersona panelPersona;
    private JTable tablaPersonas;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarPersonas(PersonaService personaService, PanelPersona panelPersona) {
        this.personaService = personaService;
        this.panelPersona = panelPersona;

        setLayout(new BorderLayout());


        setBorder(new EmptyBorder(20, 20, 20, 20));


        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellidos", "Email"}, 0);
        tablaPersonas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPersonas);

        add(scrollPane, BorderLayout.CENTER);


        JPanel panelBotones = new JPanel();
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelPersona.mostrarVistaPrincipal());
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);


        cargarDatos();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);

        List<Persona> personas = personaService.obtenerTodasLasPersonas();
        InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
        inscripcionesPersonas.cargarDatos();
        System.out.println(inscripcionesPersonas.getListado().toString());

        for (Persona p : personas) {
            modeloTabla.addRow(new Object[]{p.getID(), p.getNombre(), p.getApellidos(), p.getEmail()});
        }
    }
}
