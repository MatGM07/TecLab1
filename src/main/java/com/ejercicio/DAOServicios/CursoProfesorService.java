package com.ejercicio.DAOServicios;

import com.ejercicio.DAO.CursoProfesorDAO;
import com.ejercicio.DAOImplementacion.CursoProfesorDAOImplementacion;
import com.ejercicio.modelos.Curso;
import com.ejercicio.modelos.Profesor;
import com.ejercicio.modelos.CursoProfesor;

import java.util.List;
import java.sql.Connection;

public class CursoProfesorService {
    private CursoProfesorDAO cursoProfesorDAO;
    private CursoService cursoService;
    private ProfesorService profesorService;

    public CursoProfesorService(Connection conexion) {
        this.cursoProfesorDAO = new CursoProfesorDAOImplementacion(conexion);
        this.cursoService = new CursoService(conexion);
        this.profesorService = new ProfesorService(conexion);
    }

    public void registrarCursoProfesor(CursoProfesor cursoProfesor) {
        cursoProfesorDAO.insertar(cursoProfesor);
    }

    public List<CursoProfesor> obtenerTodosLosCursoProfesores() {
        List<CursoProfesor> cursoProfesores = cursoProfesorDAO.obtenerTodos();
        for (CursoProfesor cursoProfesor : cursoProfesores) {
            if (cursoProfesor != null) {
                Curso cursoCompleto = cursoService.obtenerPorId(cursoProfesor.getCurso().getID());
                Profesor profesorCompleto = profesorService.obtenerPorId(cursoProfesor.getProfesor().getID());
                cursoProfesor.setCurso(cursoCompleto);
                cursoProfesor.setProfesor(profesorCompleto);
            }
        }
        return cursoProfesores;
    }

    public CursoProfesor obtenerPorId(int profesorId, int cursoId) {
        CursoProfesor cursoProfesor = cursoProfesorDAO.obtenerPorId(profesorId, cursoId);
        if (cursoProfesor != null) {
            Curso cursoCompleto = cursoService.obtenerPorId(cursoProfesor.getCurso().getID());
            Profesor profesorCompleto = profesorService.obtenerPorId(cursoProfesor.getProfesor().getID());
            cursoProfesor.setCurso(cursoCompleto);
            cursoProfesor.setProfesor(profesorCompleto);
        }
        return cursoProfesor;
    }

    public void actualizarCursoProfesor(CursoProfesor cursoProfesor) {
        cursoProfesorDAO.actualizar(cursoProfesor);
    }

    public void eliminarCursoProfesor(int profesorId, int cursoId) {
        cursoProfesorDAO.eliminar(profesorId, cursoId);
    }
}
