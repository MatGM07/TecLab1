package com.ejercicio.controlador;

import com.ejercicio.DAOServicios.EstudianteService;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Programa;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class EstudianteController {
    private EstudianteService estudianteService;
    private ProgramaController programaController;

    public EstudianteController(Connection conexion){
        this.estudianteService = new EstudianteService(conexion);
        this.programaController = new ProgramaController(conexion);
    }

    public void agregar(String nombres, String apellidos, String email, Double codigo, Integer programa_id, Boolean activo, Double promedio){
        Programa programa = programaController.obtenerPorId(programa_id);

        Estudiante estudiante = new Estudiante(null, nombres, apellidos, email, codigo, programa, activo, promedio);

        estudianteService.registrarEstudiante(estudiante);
    }

    public void actualizar(Integer id, String nombres, String apellidos, String email, Double codigo, Integer programa_id, Boolean activo, Double promedio){
        Programa programa = programaController.obtenerPorId(programa_id);

        Estudiante estudiante = new Estudiante(id, nombres, apellidos, email, codigo, programa, activo, promedio);

        estudianteService.actualizarEstudiante(estudiante);
    }

    public List<List<String>> obtenerTodosLosEstudiantes(){
        List<Estudiante> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
        List<List<String>> datosTodosEstudiantes = new ArrayList<>();
        for (Estudiante e: estudiantes){
            List<String> datos = new ArrayList<>();
            datos.add(String.valueOf(e.getID()));
            datos.add(e.getNombre());
            datos.add(e.getApellidos());
            datos.add(e.getEmail());
            datos.add(String.valueOf(e.getCodigo()));
            datos.add(String.valueOf(e.getActivo()));
            datos.add(String.valueOf(e.getPromedio()));
            datos.add(String.valueOf(e.getPrograma().getID()));
            datosTodosEstudiantes.add(datos);
        }
        return datosTodosEstudiantes;
    }

    public List<String> obtenerDatosPorId(Integer id){
        Estudiante estudiante = estudianteService.obtenerPorId(id);
        String nombres = estudiante.getNombre();
        String apellidos = estudiante.getApellidos();
        String email = estudiante.getEmail();
        String codigo = String.valueOf(estudiante.getCodigo());
        String activo = String.valueOf(estudiante.getActivo());
        String programa_id = String.valueOf(estudiante.getPrograma().getID());
        String promedio = String.valueOf(estudiante.getPromedio());

        List<String> datos = new ArrayList<>();
        datos.add(nombres);
        datos.add(apellidos);
        datos.add(email);
        datos.add(codigo);
        datos.add(activo);
        datos.add(programa_id);
        datos.add(promedio);

        return datos;
    }

    public Boolean existe(Integer id){
        Estudiante estudiante = estudianteService.obtenerPorId(id);
        if (estudiante == null){
            return false;
        } else {
            return true;
        }
    }

    public void eliminar(Integer id){
        estudianteService.eliminarEstudiante(id);
    }
}
