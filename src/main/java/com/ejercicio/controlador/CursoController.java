package com.ejercicio.controlador;

import com.ejercicio.DAOServicios.CursoService;
import com.ejercicio.factories.CursoFactory;
import com.ejercicio.modelos.Curso;
import com.ejercicio.modelos.Facultad;
import com.ejercicio.modelos.Programa;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CursoController {
    CursoService cursoService;
    ProgramaController programaController;

    public CursoController(Connection conexion){
        this.cursoService = new CursoService(conexion);
        this.programaController = new ProgramaController(conexion);
    }

    public Curso obtenerPorId(Integer id){
        return cursoService.obtenerPorId(id);
    }

    public List<String> obtenerDatosPorId(Integer id){
        Curso curso = cursoService.obtenerPorId(id);
        String nombre = curso.getNombre();
        String activo = String.valueOf(curso.getActivo());
        String programa_id = String.valueOf(curso.getPrograma().getID());

        List<String> datos = new ArrayList<>();

        datos.add(nombre);
        datos.add(activo);
        datos.add(programa_id);

        return datos;
    }

    public void agregar(String nombre, Boolean activo, Integer programa_id){
        Programa programa = programaController.obtenerPorId(programa_id);
        Curso curso = CursoFactory.crearCurso(null,nombre,activo,programa);
        cursoService.registrarCurso(curso);
    }

    public Boolean existe(Integer id){
        Curso curso = cursoService.obtenerPorId(id);
        if (curso == null){
            return false;
        } else {
            return true;
        }
    }

    public List<List<String>> obtenerTodosLosCursos(){
        List<Curso> cursos = cursoService.obtenerTodosLosCursos();
        List<List<String>> datosTodosCursos = new ArrayList<>();
        for (Curso p: cursos){
            List<String> datos = new ArrayList<>();
            datos.add(String.valueOf(p.getID()));
            datos.add(p.getNombre());
            datos.add(String.valueOf(p.getActivo()));
            datos.add(String.valueOf(p.getPrograma().getID()));
            datosTodosCursos.add(datos);
        }
        return datosTodosCursos;
    }

    public void actualizar(Integer id, String nombre, boolean activo, Integer programa_id){
        Programa programa = programaController.obtenerPorId(programa_id);

        Curso curso = CursoFactory.crearCurso(id,nombre,activo,programa);

        cursoService.actualizarCurso(curso);
    }

    public void eliminar(Integer id){
        cursoService.eliminarCurso(id);
    }


}
