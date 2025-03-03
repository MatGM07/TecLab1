package com.ejercicio.gui.persona;

import com.ejercicio.DAOServicios.PersonaService;
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

        // Agregar padding alrededor del panel
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Crear tabla con modelo
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellidos", "Email"}, 0);
        tablaPersonas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPersonas);

        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones en la parte inferior
        JPanel panelBotones = new JPanel();
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> panelPersona.mostrarVistaPrincipal());
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // Cargar datos en la tabla
        cargarDatos();
    }

    private void cargarDatos() {
        // Limpiar tabla antes de cargar datos nuevos
        modeloTabla.setRowCount(0);

        // Obtener la lista de personas
        List<Persona> personas = personaService.obtenerTodasLasPersonas();

        // Llenar la tabla con los datos
        for (Persona p : personas) {
            modeloTabla.addRow(new Object[]{p.getID(), p.getNombre(), p.getApellidos(), p.getEmail()});
        }
    }
}
