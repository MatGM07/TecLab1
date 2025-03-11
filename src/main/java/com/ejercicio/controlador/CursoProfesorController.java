package com.ejercicio.controlador;

import com.ejercicio.DAOServicios.CursoProfesorService;
import com.ejercicio.modelos.Curso;
import com.ejercicio.modelos.Profesor;
import com.ejercicio.modelos.CursoProfesor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CursoProfesorController {
    CursoProfesorService cursoProfesorService;
    ProfesorController profesorController;
    CursoController cursoController;

    public CursoProfesorController(Connection conexion){
        this.cursoProfesorService = new CursoProfesorService(conexion);
        this.profesorController = new ProfesorController(conexion);
        this.cursoController = new CursoController(conexion);
    }

    public CursoProfesor obtenerPorId(Integer id_profesor, Integer id_curso){
        return  cursoProfesorService.obtenerPorId(id_profesor, id_curso);
    }

    public Boolean existen(Integer id_profesor, Integer id_curso){
        Boolean existeProfesor = profesorController.existe(id_profesor);
        Boolean existeCurso = cursoController.existe(id_curso);
        Boolean existen = existeCurso && existeProfesor;
        return existen;
    }

    public List<String> obtenerDatosPorId(Integer id_profesor, Integer id_curso){
        CursoProfesor inscripción = cursoProfesorService.obtenerPorId(id_profesor, id_curso);
        String año = String.valueOf(inscripción.getAño());
        String semestre = String.valueOf(inscripción.getSemestre());

        List<String> datos = new ArrayList<>();

        datos.add(año);
        datos.add(semestre);

        return datos;
    }

    public void agregar(Integer id_profesor, Integer id_curso,  Integer año, Integer semestre){
        Profesor profesor = profesorController.obtenerPorId(id_profesor);
        Curso curso = cursoController.obtenerPorId(id_curso);
        CursoProfesor inscripción = new CursoProfesor(profesor,año,semestre,curso);
        cursoProfesorService.registrarCursoProfesor(inscripción);
    }

    public Boolean existe(Integer id_profesor, Integer id_curso){
        CursoProfesor inscripción = cursoProfesorService.obtenerPorId(id_profesor, id_curso);
        if (inscripción == null){
            return false;
        } else {
            return true;
        }
    }

    public List<List<String>> obtenerTodasLasCursoProfesores(){
        List<CursoProfesor> cursoProfesores = cursoProfesorService.obtenerTodosLosCursoProfesores();
        List<List<String>> datosTodosCursoProfesores = new ArrayList<>();
        for (CursoProfesor i : cursoProfesores){
            List<String> datos = new ArrayList<>();
            datos.add(String.valueOf(i.getProfesor().getID()));
            datos.add(String.valueOf(i.getCurso().getID()));
            datos.add(String.valueOf(i.getAño()));
            datos.add(String.valueOf(i.getSemestre()));
            datosTodosCursoProfesores.add(datos);
        }
        return datosTodosCursoProfesores;
    }

    public void actualizar(Integer id_profesor, Integer id_curso, Integer año, Integer semestre){
        Profesor profesor = profesorController.obtenerPorId(id_profesor);
        Curso curso = cursoController.obtenerPorId(id_curso);

        CursoProfesor cursoProfesor = new CursoProfesor(profesor, año, semestre, curso);
        cursoProfesorService.actualizarCursoProfesor(cursoProfesor);
    }

    public void eliminar(Integer id_profesor, Integer id_curso){
        cursoProfesorService.eliminarCursoProfesor(id_profesor,id_curso);
    }


}


