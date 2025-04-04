package com.ejercicio.gui.curso;

import com.ejercicio.ConexionController;
import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.controlador.CursoController;
import com.ejercicio.controlador.ProgramaController;
import com.ejercicio.gui.MainFrame;
import com.ejercicio.gui.PanelBase;
import com.ejercicio.gui.curso.*;
import com.ejercicio.modelos.Curso;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PanelCurso extends PanelBase {
    private CursoController cursoController;

    public PanelCurso(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionController.obtenerConexion();
        this.cursoController = new CursoController(connection);

        btnAgregar.addActionListener(e -> abrirAgregarCurso());

        btnEditar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del curso a editar:", "Editar Curso", JOptionPane.QUESTION_MESSAGE);
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = cursoController.existe(id);
                    if (existe) {
                        abrirEditarCurso(id);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una curso con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del curso a eliminar:", "Eliminar Curso", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = cursoController.existe(id);
                    if (existe) {
                        abrirEliminarCurso(id);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una curso con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnConsultar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del curso a consultar:", "Consultar Curso", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = cursoController.existe(id);
                    if (existe) {
                        abrirConsultarCurso(id);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró un curso con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(e -> abrirListarCursos());
    }

    private void abrirAgregarCurso() {
        AgregarCurso agregarCurso = new AgregarCurso(cursoController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(agregarCurso, BorderLayout.CENTER);
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

    private void abrirListarCursos() {
        ListarCurso listarCursos = new ListarCurso(cursoController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarCursos, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarCurso(Integer id) {
        EditarCurso editarCurso = new EditarCurso(id, cursoController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarCurso, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarCurso(Integer id) {
        EliminarCurso eliminarCurso = new EliminarCurso(id, cursoController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarCurso, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarCurso(Integer id) {
        ConsultarCurso consultarCurso = new ConsultarCurso(id, cursoController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarCurso, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
