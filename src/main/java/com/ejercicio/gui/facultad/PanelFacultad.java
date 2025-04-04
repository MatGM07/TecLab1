package com.ejercicio.gui.facultad;

import com.ejercicio.ConexionController;
import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.controlador.FacultadController;
import com.ejercicio.gui.MainFrame;
import com.ejercicio.gui.PanelBase;
import com.ejercicio.gui.facultad.*;
import com.ejercicio.modelos.Facultad;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;


public class PanelFacultad extends PanelBase {
    private FacultadController facultadController;


    public PanelFacultad(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionController.obtenerConexion();
        this.facultadController = new FacultadController(connection);

        btnAgregar.addActionListener(e -> abrirAgregarFacultad());

        btnEditar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la facultad a editar:", "Editar Facultad", JOptionPane.QUESTION_MESSAGE);
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = facultadController.existe(id);
                    if (existe) {
                        abrirEditarFacultad(id);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una facultad con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la facultad a eliminar:", "Eliminar Facultad", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = facultadController.existe(id);

                    if (existe) {
                        abrirEliminarFacultad(id);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una facultad con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnConsultar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la facultad a consultar:", "Consultar Facultad", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = facultadController.existe(id);

                    if (existe) {
                        abrirConsultarFacultad(id);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una facultad con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(e -> abrirListarFacultades());
    }

    private void abrirAgregarFacultad() {
        AgregarFacultad agregarFacultad = new AgregarFacultad(facultadController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(agregarFacultad, BorderLayout.CENTER);
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

    private void abrirListarFacultades() {
        ListarFacultad listarFacultads = new ListarFacultad(facultadController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarFacultads, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarFacultad(Integer id) {
        EditarFacultad editarFacultad = new EditarFacultad(id, facultadController,this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarFacultad, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarFacultad(Integer id) {
        EliminarFacultad eliminarFacultad = new EliminarFacultad(id, facultadController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarFacultad, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarFacultad(Integer id) {
        ConsultarFacultad consultarFacultad = new ConsultarFacultad(id, facultadController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarFacultad, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
