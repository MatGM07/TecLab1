package com.ejercicio.gui;

import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.DAOServicios.FacultadService;

class ControladorGUI {
    private FacultadService facultadService;
    private EstudianteService estudianteService;

    public ControladorGUI(FacultadService facultadService, EstudianteService estudianteService) {
        this.facultadService = facultadService;
        this.estudianteService = estudianteService;
    }

    public void agregarFacultad() {
        // Lógica para agregar facultad con el servicio
    }
}