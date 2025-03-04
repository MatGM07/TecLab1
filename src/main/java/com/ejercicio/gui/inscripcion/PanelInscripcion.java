package com.ejercicio.gui.inscripcion;

import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.DAOServicios.InscripcionService;
import com.ejercicio.gui.MainFrame;
import com.ejercicio.gui.PanelBase;
import com.ejercicio.gui.inscripcion.*;
import com.ejercicio.modelos.Inscripción;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PanelInscripcion extends PanelBase {
    private InscripcionService inscripcionService;
    private CursoService cursoService;
    private EstudianteService estudianteService;

    public PanelInscripcion(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionDB.obtenerConexion();
        this.inscripcionService = new InscripcionService(connection);
        this.cursoService = new CursoService(connection);
        this.estudianteService = new EstudianteService(connection);

        btnAgregar.addActionListener(e -> abrirAgregarInscripcion());

        btnEditar.addActionListener(e -> {
            int[] ids = obtenerIdsEstudianteYCurso("editar");
            if (ids != null) {
                Inscripción inscripcion = inscripcionService.obtenerPorId(ids[0], ids[1]);
                if (inscripcion != null) {
                    abrirEditarInscripcion(inscripcion);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la inscripción con los IDs ingresados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            int[] ids = obtenerIdsEstudianteYCurso("eliminar");
            if (ids != null) {
                Inscripción inscripcion = inscripcionService.obtenerPorId(ids[0], ids[1]);
                if (inscripcion != null) {
                    abrirEliminarInscripcion(inscripcion);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la inscripción con los IDs ingresados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnConsultar.addActionListener(e -> {
            int[] ids = obtenerIdsEstudianteYCurso("consultar");
            if (ids != null) {
                Inscripción inscripcion = inscripcionService.obtenerPorId(ids[0], ids[1]);
                if (inscripcion != null) {
                    abrirConsultarInscripcion(inscripcion);
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
        AgregarInscripcion agregarInscripcion = new AgregarInscripcion(inscripcionService, cursoService, estudianteService,this);
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
        ListarInscripcion listarInscripcions = new ListarInscripcion(inscripcionService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarInscripcions, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarInscripcion(Inscripción inscripcion) {
        EditarInscripcion editarInscripcion = new EditarInscripcion(inscripcion, inscripcionService,this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarInscripcion, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarInscripcion(Inscripción inscripcion) {
        EliminarInscripcion eliminarInscripcion = new EliminarInscripcion(inscripcion, inscripcionService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarInscripcion, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarInscripcion(Inscripción inscripcion) {
        ConsultarInscripcion consultarInscripcion = new ConsultarInscripcion(inscripcion, this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarInscripcion, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
