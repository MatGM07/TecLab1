package com.ejercicio.controlador;

import com.ejercicio.DAOServicios.InscripcionService;
import com.ejercicio.modelos.Curso;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Inscripción;
import com.ejercicio.modelos.Persona;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class InscripcionController {
    InscripcionService inscripcionService;
    EstudianteController estudianteController;
    CursoController cursoController;

    public InscripcionController(Connection conexion){
        this.inscripcionService = new InscripcionService(conexion);
        this.estudianteController = new EstudianteController(conexion);
        this.cursoController = new CursoController(conexion);
    }

    public Inscripción obtenerPorId(Integer id_estudiante, Integer id_curso){
        return  inscripcionService.obtenerPorId(id_estudiante, id_curso);
    }

    public Boolean existen(Integer id_estudiante, Integer id_curso){
        Boolean existeEstudiante = estudianteController.existe(id_estudiante);
        Boolean existeCurso = cursoController.existe(id_curso);
        Boolean existen = existeCurso && existeEstudiante;
        return existen;
    }

    public List<String> obtenerDatosPorId(Integer id_estudiante, Integer id_curso){
        Inscripción inscripción = inscripcionService.obtenerPorId(id_estudiante, id_curso);
        String año = String.valueOf(inscripción.getAño());
        String semestre = String.valueOf(inscripción.getSemestre());

        List<String> datos = new ArrayList<>();

        datos.add(año);
        datos.add(semestre);

        return datos;
    }

    public void agregar(Integer id_estudiante, Integer id_curso,  Integer año, Integer semestre){
        Estudiante estudiante = estudianteController.obtenerPorId(id_estudiante);
        Curso curso = cursoController.obtenerPorId(id_curso);
        Inscripción inscripción = new Inscripción(curso,año,semestre,estudiante);
        inscripcionService.registrarInscripcion(inscripción);
    }

    public Boolean existe(Integer id_estudiante, Integer id_curso){
        Inscripción inscripción = inscripcionService.obtenerPorId(id_estudiante, id_curso);
        if (inscripción == null){
            return false;
        } else {
            return true;
        }
    }

    public List<List<String>> obtenerTodasLasInscripciones(){
        List<Inscripción> inscripciones = inscripcionService.obtenerTodasLasInscripciones();
        List<List<String>> datosTodosInscripciones = new ArrayList<>();
        for (Inscripción i : inscripciones){
            List<String> datos = new ArrayList<>();
            datos.add(String.valueOf(i.getEstudiante().getID()));
            datos.add(String.valueOf(i.getCurso().getID()));
            datos.add(String.valueOf(i.getAño()));
            datos.add(String.valueOf(i.getSemestre()));
            datosTodosInscripciones.add(datos);
        }
        return datosTodosInscripciones;
    }

    public void actualizar(Integer id_estudiante, Integer id_curso, Integer año, Integer semestre){
        Estudiante estudiante = estudianteController.obtenerPorId(id_estudiante);
        Curso curso = cursoController.obtenerPorId(id_curso);

        Inscripción inscripcion = new Inscripción(curso, año, semestre, estudiante);
        inscripcionService.actualizarInscripcion(inscripcion);
    }

    public void eliminar(Integer id_estudiante, Integer id_curso){
        inscripcionService.eliminarInscripcion(id_estudiante,id_curso);
    }


}
