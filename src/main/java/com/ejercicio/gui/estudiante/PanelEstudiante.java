package com.ejercicio.gui.estudiante;

import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.controlador.EstudianteController;
import com.ejercicio.controlador.ProgramaController;
import com.ejercicio.gui.MainFrame;
import com.ejercicio.gui.PanelBase;
import com.ejercicio.gui.estudiante.*;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Persona;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PanelEstudiante extends PanelBase {
    private EstudianteController estudianteController;

    public PanelEstudiante(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionDB.obtenerConexion();
        this.estudianteController = new EstudianteController(connection);

        btnAgregar.addActionListener(e -> abrirAgregarEstudiante());

        btnEditar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la estudiante a editar:", "Editar Estudiante", JOptionPane.QUESTION_MESSAGE);
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = estudianteController.existe(id);
                    if (existe) {
                        abrirEditarEstudiante(id);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una estudiante con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la estudiante a eliminar:", "Eliminar Estudiante", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = estudianteController.existe(id);

                    if (existe) {
                        abrirEliminarEstudiante(id);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una estudiante con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnConsultar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la estudiante a consultar:", "Consultar Estudiante", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = estudianteController.existe(id);

                    if (existe) {
                        abrirConsultarEstudiante(id);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una estudiante con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(e -> abrirListarEstudiantees());
    }

    private void abrirAgregarEstudiante() {
        AgregarEstudiante agregarEstudiante = new AgregarEstudiante(estudianteController,this);
        removeAll();
        setLayout(new BorderLayout());
        add(agregarEstudiante, BorderLayout.CENTER);
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

    private void abrirListarEstudiantees() {
        ListarEstudiante listarEstudiantes = new ListarEstudiante(estudianteController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarEstudiantes, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarEstudiante(Integer id) {
        EditarEstudiante editarEstudiante = new EditarEstudiante(id, estudianteController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarEstudiante, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarEstudiante(Integer id) {
        EliminarEstudiante eliminarEstudiante = new EliminarEstudiante(id, estudianteController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarEstudiante, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarEstudiante(Integer id) {
        ConsultarEstudiante consultarEstudiante = new ConsultarEstudiante(id, estudianteController,this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarEstudiante, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
