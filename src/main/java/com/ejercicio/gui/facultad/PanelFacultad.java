package com.ejercicio.gui.facultad;

import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.gui.MainFrame;
import com.ejercicio.gui.PanelBase;
import com.ejercicio.gui.facultad.*;
import com.ejercicio.modelos.Facultad;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;


public class PanelFacultad extends PanelBase {
    private FacultadService facultadService;
    private PersonaService personaService;

    public PanelFacultad(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionDB.obtenerConexion();
        this.facultadService = new FacultadService(connection);
        this.personaService = new PersonaService(connection);

        btnAgregar.addActionListener(e -> abrirAgregarFacultad());

        btnEditar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la facultad a editar:", "Editar Facultad", JOptionPane.QUESTION_MESSAGE);
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Facultad facultad = facultadService.obtenerPorId(id);
                    if (facultad != null) {
                        abrirEditarFacultad(facultad);
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
                    Facultad facultad = facultadService.obtenerPorId(id);

                    if (facultad != null) {
                        abrirEliminarFacultad(facultad);
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
                    Facultad facultad = facultadService.obtenerPorId(id);

                    if (facultad != null) {
                        abrirConsultarFacultad(facultad);
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
        AgregarFacultad agregarFacultad = new AgregarFacultad(facultadService, this);
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
        ListarFacultad listarFacultads = new ListarFacultad(facultadService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarFacultads, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarFacultad(Facultad facultad) {
        EditarFacultad editarFacultad = new EditarFacultad(facultad, facultadService, personaService,this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarFacultad, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarFacultad(Facultad facultad) {
        EliminarFacultad eliminarFacultad = new EliminarFacultad(facultad, facultadService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarFacultad, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarFacultad(Facultad facultad) {
        ConsultarFacultad consultarFacultad = new ConsultarFacultad(facultad, this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarFacultad, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
