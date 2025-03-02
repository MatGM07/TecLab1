package com.ejercicio.gui;

class PanelFacultad extends PanelBase {
    public PanelFacultad(MainFrame mainFrame) {
        super(mainFrame);
        btnAgregar.addActionListener(e -> System.out.println("Agregar Facultad"));
        btnEditar.addActionListener(e -> System.out.println("Editar Facultad"));
        btnEliminar.addActionListener(e -> System.out.println("Eliminar Facultad"));
        btnListar.addActionListener(e -> System.out.println("Listar Facultades"));
    }
}
