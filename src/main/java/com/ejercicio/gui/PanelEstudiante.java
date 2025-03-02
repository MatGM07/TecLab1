package com.ejercicio.gui;

class PanelEstudiante extends PanelBase {
    public PanelEstudiante(MainFrame mainFrame) {
        super(mainFrame);
        btnAgregar.addActionListener(e -> System.out.println("Agregar Estudiante"));
        btnEditar.addActionListener(e -> System.out.println("Editar Estudiante"));
        btnEliminar.addActionListener(e -> System.out.println("Eliminar Estudiante"));
        btnListar.addActionListener(e -> System.out.println("Listar Estudiantes"));
    }
}