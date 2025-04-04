package com.ejercicio.gui.programa;

import com.ejercicio.ConexionController;
import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.controlador.ProgramaController;
import com.ejercicio.gui.MainFrame;
import com.ejercicio.gui.PanelBase;
import com.ejercicio.gui.programa.*;
import com.ejercicio.modelos.Programa;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PanelPrograma extends PanelBase {
    private ProgramaController programaController;

    public PanelPrograma(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionController.obtenerConexion();
        this.programaController = new ProgramaController(connection);

        btnAgregar.addActionListener(e -> abrirAgregarPrograma());

        btnEditar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del programa a editar:", "Editar Programa", JOptionPane.QUESTION_MESSAGE);
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = programaController.existe(id);
                    if (existe) {
                        abrirEditarPrograma(id);
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
                    Boolean existe = programaController.existe(id);

                    if (existe) {
                        abrirEliminarPrograma(id);
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
                    Boolean existe = programaController.existe(id);

                    if (existe) {
                        abrirConsultarPrograma(id);
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
        AgregarPrograma agregarPrograma = new AgregarPrograma(programaController, this);
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
        ListarPrograma listarProgramas = new ListarPrograma(programaController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarProgramas, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarPrograma(Integer id) {
        EditarPrograma editarPrograma = new EditarPrograma(id, programaController,this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarPrograma, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarPrograma(Integer id) {
        EliminarPrograma eliminarPrograma = new EliminarPrograma(id, programaController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarPrograma, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarPrograma(Integer id) {
        ConsultarPrograma consultarPrograma = new ConsultarPrograma(id, programaController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarPrograma, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
