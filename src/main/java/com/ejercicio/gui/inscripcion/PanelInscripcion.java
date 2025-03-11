package com.ejercicio.gui.inscripcion;

import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.DAOServicios.InscripcionService;
import com.ejercicio.controlador.InscripcionController;
import com.ejercicio.gui.MainFrame;
import com.ejercicio.gui.PanelBase;
import com.ejercicio.gui.inscripcion.*;
import com.ejercicio.modelos.CursosInscritos;
import com.ejercicio.modelos.Inscripción;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PanelInscripcion extends PanelBase {
    private InscripcionController inscripcionController;


    public PanelInscripcion(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionDB.obtenerConexion();
        this.inscripcionController = new InscripcionController(connection);

        btnAgregar.addActionListener(e -> abrirAgregarInscripcion());

        btnEditar.addActionListener(e -> {
            int[] ids = obtenerIdsEstudianteYCurso("editar");
            if (ids != null) {
                Boolean existe = inscripcionController.existe(ids[0], ids[1]);
                if (existe) {
                    abrirEditarInscripcion(ids[0], ids[1]);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la inscripción con los IDs ingresados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            int[] ids = obtenerIdsEstudianteYCurso("eliminar");
            if (ids != null) {
                Boolean existe = inscripcionController.existe(ids[0], ids[1]);
                if (existe) {
                    abrirEliminarInscripcion(ids[0], ids[1]);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la inscripción con los IDs ingresados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnConsultar.addActionListener(e -> {
            int[] ids = obtenerIdsEstudianteYCurso("consultar");
            if (ids != null) {
                Boolean existe = inscripcionController.existe(ids[0], ids[1]);
                if (existe) {
                    abrirConsultarInscripcion(ids[0], ids[1]);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la inscripción con los IDs ingresados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(e -> abrirListarInscripcions());
    }

    private int[] obtenerIdsEstudianteYCurso(String accion) {
        JTextField txtEstudianteId = new JTextField();
        JTextField txtCursoId = new JTextField();
        Object[] message = {
                "ID del Estudiante:", txtEstudianteId,
                "ID del Curso:", txtCursoId
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Ingrese los IDs para " + accion, JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int estudianteId = Integer.parseInt(txtEstudianteId.getText().trim());
                int cursoId = Integer.parseInt(txtCursoId.getText().trim());
                return new int[]{estudianteId, cursoId};
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese IDs válidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    private void abrirAgregarInscripcion() {
        AgregarInscripcion agregarInscripcion = new AgregarInscripcion(inscripcionController,this);
        removeAll();
        setLayout(new BorderLayout());
        add(agregarInscripcion, BorderLayout.CENTER);
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

    private void abrirListarInscripcions() {
        ListarInscripcion listarInscripcions = new ListarInscripcion(inscripcionController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarInscripcions, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarInscripcion(Integer id_estudiante, Integer id_curso) {
        EditarInscripcion editarInscripcion = new EditarInscripcion(id_estudiante,id_curso, inscripcionController,this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarInscripcion, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarInscripcion(Integer id_estudiante, Integer id_curso) {
        EliminarInscripcion eliminarInscripcion = new EliminarInscripcion(id_estudiante, id_curso, inscripcionController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarInscripcion, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarInscripcion(Integer id_estudiante, Integer id_curso) {
        ConsultarInscripcion consultarInscripcion = new ConsultarInscripcion(id_estudiante, id_curso,  inscripcionController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarInscripcion, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
