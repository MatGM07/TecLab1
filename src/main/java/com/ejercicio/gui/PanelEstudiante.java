package com.ejercicio.gui;
import javax.swing.*;
import java.awt.*;

import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.EstudianteService;

import java.sql.Connection;

class PanelEstudiante extends PanelBase {
    private EstudianteService estudianteService;

    public PanelEstudiante(MainFrame mainFrame) {
        super(mainFrame);


        Connection connection = ConexionDB.obtenerConexion();
        this.estudianteService = new EstudianteService(connection);

        btnAgregar.addActionListener(e -> abrirAgregarEstudiante());
        btnEditar.addActionListener(e -> System.out.println("Editar Estudiante"));
        btnEliminar.addActionListener(e -> System.out.println("Eliminar Estudiante"));
        btnListar.addActionListener(e -> System.out.println("Listar Estudiantes"));
    }

    private void abrirAgregarEstudiante() {
        JDialog dialog = new JDialog((Frame) null, "Agregar Estudiante", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);
        dialog.add(new AgregarEstudiante(estudianteService));
        dialog.setVisible(true);
    }
}