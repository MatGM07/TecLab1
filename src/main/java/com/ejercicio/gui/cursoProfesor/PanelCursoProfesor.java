package com.ejercicio.gui.cursoProfesor;

import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.DAOServicios.ProfesorService;
import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.DAOServicios.CursoProfesorService;
import com.ejercicio.controlador.CursoProfesorController;
import com.ejercicio.gui.MainFrame;
import com.ejercicio.gui.PanelBase;
import com.ejercicio.gui.cursoProfesor.*;
import com.ejercicio.gui.cursoProfesor.*;
import com.ejercicio.modelos.CursoProfesor;
import com.ejercicio.modelos.CursosProfesores;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PanelCursoProfesor extends PanelBase {
    private CursoProfesorController cursoProfesorController;


    public PanelCursoProfesor(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionDB.obtenerConexion();
        this.cursoProfesorController = new CursoProfesorController(connection);

        btnAgregar.addActionListener(e -> abrirAgregarCursoProfesor());

        btnEditar.addActionListener(e -> {
            int[] ids = obtenerIdsProfesorYCurso("editar");
            if (ids != null) {
                Boolean existe = cursoProfesorController.existe(ids[0], ids[1]);
                if (existe) {
                    abrirEditarCursoProfesor(ids[0], ids[1]);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la inscripción con los IDs ingresados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            int[] ids = obtenerIdsProfesorYCurso("eliminar");
            if (ids != null) {
                Boolean existe = cursoProfesorController.existe(ids[0], ids[1]);
                if (existe) {
                    abrirEliminarCursoProfesor(ids[0], ids[1]);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la inscripción con los IDs ingresados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnConsultar.addActionListener(e -> {
            int[] ids = obtenerIdsProfesorYCurso("consultar");
            if (ids != null) {
                Boolean existe = cursoProfesorController.existe(ids[0], ids[1]);
                if (existe) {
                    abrirConsultarCursoProfesor(ids[0], ids[1]);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la inscripción con los IDs ingresados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(e -> abrirListarCursoProfesors());
    }

    private int[] obtenerIdsProfesorYCurso(String accion) {
        JTextField txtProfesorId = new JTextField();
        JTextField txtCursoId = new JTextField();
        Object[] message = {
                "ID del Profesor:", txtProfesorId,
                "ID del Curso:", txtCursoId
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Ingrese los IDs para " + accion, JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int profesorId = Integer.parseInt(txtProfesorId.getText().trim());
                int cursoId = Integer.parseInt(txtCursoId.getText().trim());
                return new int[]{profesorId, cursoId};
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese IDs válidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    private void abrirAgregarCursoProfesor() {
        AgregarCursoProfesor agregarCursoProfesor = new AgregarCursoProfesor(cursoProfesorController,this);
        removeAll();
        setLayout(new BorderLayout());
        add(agregarCursoProfesor, BorderLayout.CENTER);
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

    private void abrirListarCursoProfesors() {
        ListarCursoProfesor listarCursoProfesors = new ListarCursoProfesor(cursoProfesorController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarCursoProfesors, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarCursoProfesor(Integer id_profesor, Integer id_curso) {
        EditarCursoProfesor editarCursoProfesor = new EditarCursoProfesor(id_profesor,id_curso, cursoProfesorController,this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarCursoProfesor, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarCursoProfesor(Integer id_profesor, Integer id_curso) {
        EliminarCursoProfesor eliminarCursoProfesor = new EliminarCursoProfesor(id_profesor, id_curso, cursoProfesorController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarCursoProfesor, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarCursoProfesor(Integer id_profesor, Integer id_curso) {
        ConsultarCursoProfesor consultarCursoProfesor = new ConsultarCursoProfesor(id_profesor, id_curso,  cursoProfesorController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarCursoProfesor, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}