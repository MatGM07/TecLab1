package com.ejercicio.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class PanelBase extends JPanel {
    protected JButton btnAgregar, btnEditar, btnEliminar, btnListar, btnCerrar;
    protected JTable tabla;
    protected MainFrame mainFrame;

    public PanelBase(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        JPanel panelBotones = new JPanel();

        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");
        btnCerrar = new JButton("Cerrar pestaña");

        btnCerrar.addActionListener(e -> mainFrame.getTabbedPane().remove(this));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);
        panelBotones.add(btnCerrar);

        tabla = new JTable();

        add(panelBotones, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
