package com.ejercicio.gui.profesor;

import com.ejercicio.ConexionController;
import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.ProfesorService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.controlador.ProfesorController;
import com.ejercicio.gui.MainFrame;
import com.ejercicio.gui.PanelBase;
import com.ejercicio.gui.profesor.*;
import com.ejercicio.modelos.Profesor;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PanelProfesor extends PanelBase {
    private ProfesorController profesorController;


    public PanelProfesor(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionController.obtenerConexion();
        this.profesorController = new ProfesorController(connection);

        btnAgregar.addActionListener(e -> abrirAgregarProfesor());

        btnEditar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la profesor a editar:", "Editar Profesor", JOptionPane.QUESTION_MESSAGE);
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = profesorController.existe(id);
                    if (existe) {
                        abrirEditarProfesor(id);
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
                    Boolean existe = profesorController.existe(id);
                    if (existe != null) {
                        abrirEliminarProfesor(id);
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
                    Boolean existe = profesorController.existe(id);

                    if (existe != null) {
                        abrirConsultarProfesor(id);
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
        AgregarProfesor agregarProfesor = new AgregarProfesor(profesorController,this);
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
        ListarProfesor listarProfesors = new ListarProfesor(profesorController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarProfesors, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarProfesor(Integer id) {
        EditarProfesor editarProfesor = new EditarProfesor(id, profesorController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarProfesor, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarProfesor(Integer id) {
        EliminarProfesor eliminarProfesor = new EliminarProfesor(id, profesorController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarProfesor, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarProfesor(Integer id) {
        ConsultarProfesor consultarProfesor = new ConsultarProfesor(id, profesorController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarProfesor, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
