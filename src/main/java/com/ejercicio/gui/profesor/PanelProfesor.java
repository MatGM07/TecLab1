package com.ejercicio.gui.profesor;

import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.ProfesorService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.gui.MainFrame;
import com.ejercicio.gui.PanelBase;
import com.ejercicio.gui.profesor.*;
import com.ejercicio.modelos.Profesor;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PanelProfesor extends PanelBase {
    private ProfesorService profesorService;

    public PanelProfesor(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionDB.obtenerConexion();
        this.profesorService = new ProfesorService(connection);

        btnAgregar.addActionListener(e -> abrirAgregarProfesor());

        btnEditar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la profesor a editar:", "Editar Profesor", JOptionPane.QUESTION_MESSAGE);
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Profesor profesor = profesorService.obtenerPorId(id);
                    if (profesor != null) {
                        abrirEditarProfesor(profesor);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró un profesor con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del profesor a eliminar:", "Eliminar Profesor", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Profesor profesor = profesorService.obtenerPorId(id);

                    if (profesor != null) {
                        abrirEliminarProfesor(profesor);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una profesor con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnConsultar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la profesor a consultar:", "Consultar Profesor", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Profesor profesor = profesorService.obtenerPorId(id);

                    if (profesor != null) {
                        abrirConsultarProfesor(profesor);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una profesor con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(e -> abrirListarProfesores());
    }

    private void abrirAgregarProfesor() {
        AgregarProfesor agregarProfesor = new AgregarProfesor(profesorService,this);
        removeAll();
        setLayout(new BorderLayout());
        add(agregarProfesor, BorderLayout.CENTER);
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

    private void abrirListarProfesores() {
        ListarProfesor listarProfesors = new ListarProfesor(profesorService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarProfesors, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarProfesor(Profesor profesor) {
        EditarProfesor editarProfesor = new EditarProfesor(profesor, profesorService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarProfesor, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarProfesor(Profesor profesor) {
        EliminarProfesor eliminarProfesor = new EliminarProfesor(profesor, profesorService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarProfesor, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarProfesor(Profesor profesor) {
        ConsultarProfesor consultarProfesor = new ConsultarProfesor(profesor, this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarProfesor, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
