package com.ejercicio.gui.programa;

import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.gui.MainFrame;
import com.ejercicio.gui.PanelBase;
import com.ejercicio.gui.programa.*;
import com.ejercicio.modelos.Programa;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PanelPrograma extends PanelBase {
    private ProgramaService programaService;
    private FacultadService facultadService;

    public PanelPrograma(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionDB.obtenerConexion();
        this.programaService = new ProgramaService(connection);
        this.facultadService = new FacultadService(connection);

        btnAgregar.addActionListener(e -> abrirAgregarPrograma());

        btnEditar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del programa a editar:", "Editar Programa", JOptionPane.QUESTION_MESSAGE);
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Programa programa = programaService.obtenerPorId(id);
                    if (programa != null) {
                        abrirEditarPrograma(programa);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una programa con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del programa a eliminar:", "Eliminar Programa", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Programa programa = programaService.obtenerPorId(id);

                    if (programa != null) {
                        abrirEliminarPrograma(programa);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una programa con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnConsultar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del programa a consultar:", "Consultar Programa", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Programa programa = programaService.obtenerPorId(id);

                    if (programa != null) {
                        abrirConsultarPrograma(programa);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró un programa con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(e -> abrirListarProgramas());
    }

    private void abrirAgregarPrograma() {
        AgregarPrograma agregarPrograma = new AgregarPrograma(programaService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(agregarPrograma, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void mostrarVistaPrincipal() {
        removeAll();
        setLayout(new BorderLayout());
        JPanel panelBotones = new JPanel();

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.NORTH);

        revalidate();
        repaint();
    }

    private void abrirListarProgramas() {
        ListarPrograma listarProgramas = new ListarPrograma(programaService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarProgramas, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarPrograma(Programa programa) {
        EditarPrograma editarPrograma = new EditarPrograma(programa, programaService, facultadService,this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarPrograma, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarPrograma(Programa programa) {
        EliminarPrograma eliminarPrograma = new EliminarPrograma(programa, programaService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarPrograma, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarPrograma(Programa programa) {
        ConsultarPrograma consultarPrograma = new ConsultarPrograma(programa, this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarPrograma, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
