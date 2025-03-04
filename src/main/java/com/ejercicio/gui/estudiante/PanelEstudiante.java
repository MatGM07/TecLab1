package com.ejercicio.gui.estudiante;

import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.gui.MainFrame;
import com.ejercicio.gui.PanelBase;
import com.ejercicio.gui.estudiante.*;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Persona;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PanelEstudiante extends PanelBase {
    private EstudianteService estudianteService;
    private ProgramaService programaService;

    public PanelEstudiante(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionDB.obtenerConexion();
        this.estudianteService = new EstudianteService(connection);
        this.programaService = new ProgramaService(connection);

        btnAgregar.addActionListener(e -> abrirAgregarEstudiante());

        btnEditar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la estudiante a editar:", "Editar Estudiante", JOptionPane.QUESTION_MESSAGE);
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Estudiante estudiante = estudianteService.obtenerPorId(id);
                    if (estudiante != null) {
                        abrirEditarEstudiante(estudiante);
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
                    Estudiante estudiante = estudianteService.obtenerPorId(id);

                    if (estudiante != null) {
                        abrirEliminarEstudiante(estudiante);
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
                    Estudiante estudiante = estudianteService.obtenerPorId(id);

                    if (estudiante != null) {
                        abrirConsultarEstudiante(estudiante);
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
        AgregarEstudiante agregarEstudiante = new AgregarEstudiante(estudianteService, programaService,this);
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
        ListarEstudiante listarEstudiantes = new ListarEstudiante(estudianteService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarEstudiantes, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarEstudiante(Estudiante estudiante) {
        EditarEstudiante editarEstudiante = new EditarEstudiante(estudiante, estudianteService, programaService,this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarEstudiante, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarEstudiante(Estudiante estudiante) {
        EliminarEstudiante eliminarEstudiante = new EliminarEstudiante(estudiante, estudianteService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarEstudiante, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarEstudiante(Estudiante estudiante) {
        ConsultarEstudiante consultarEstudiante = new ConsultarEstudiante(estudiante, this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarEstudiante, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
