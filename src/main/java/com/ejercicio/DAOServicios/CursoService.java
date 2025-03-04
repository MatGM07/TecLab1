package com.ejercicio.DAOServicios;

import com.ejercicio.DAO.CursoDAO;
import com.ejercicio.DAOImplementacion.CursoDAOImplementacion;
import com.ejercicio.modelos.Programa;
import com.ejercicio.modelos.Curso;

import java.sql.Connection;
import java.util.List;

public class CursoService {
    private CursoDAO cursoDAO;
    private ProgramaService programaService;

    public CursoService(Connection conexion) {
        this.cursoDAO = new CursoDAOImplementacion(conexion);
        this.programaService = new ProgramaService(conexion);
    }

    public void registrarCurso(Curso curso) {
        cursoDAO.insertar(curso);
    }

    public Curso obtenerPorId(int id) {
        Curso curso = cursoDAO.obtenerPorId(id);
        if (curso != null && curso.getPrograma().getID() > 0) {
            Programa programaCompleta = programaService.obtenerPorId(curso.getPrograma().getID());
            curso.setPrograma(programaCompleta);
        }
        return curso;
    }

    public List<Curso> obtenerTodosLosCursos() {
        List<Curso> cursos = cursoDAO.obtenerTodos();
        for (Curso curso : cursos){
            if (curso != null && curso.getPrograma().getID() > 0) {
                Programa programaCompleta = new Programa();
                programaCompleta = programaService.obtenerPorId(curso.getPrograma().getID());
                curso.setPrograma(programaCompleta);
            }
        }
        return cursos;
    }

    public void actualizarCurso(Curso curso) {
        cursoDAO.actualizar(curso);
    }

    public void eliminarCurso(int id) {
        cursoDAO.eliminar(id);
    }
}
