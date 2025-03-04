package com.ejercicio.DAOServicios;

import com.ejercicio.DAO.InscripcionDAO;
import com.ejercicio.DAOImplementacion.InscripcionDAOImplementacion;
import com.ejercicio.modelos.Curso;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Inscripción;

import java.util.List;
import java.sql.Connection;

public class InscripcionService {
    private InscripcionDAO inscripcionDAO;
    private CursoService cursoService;
    private EstudianteService estudianteService;

    public InscripcionService(Connection conexion) {
        this.inscripcionDAO = new InscripcionDAOImplementacion(conexion);
        this.cursoService = new CursoService(conexion);
        this.estudianteService = new EstudianteService(conexion);
    }

    public void registrarInscripcion(Inscripción inscripcion) {
        inscripcionDAO.insertar(inscripcion);
    }

    public List<Inscripción> obtenerTodasLasInscripciones() {
        List<Inscripción> inscripciones = inscripcionDAO.obtenerTodos();
        for (Inscripción inscripcion : inscripciones) {
            if (inscripcion != null) {
                Curso cursoCompleto = cursoService.obtenerPorId(inscripcion.getCurso().getID());
                Estudiante estudianteCompleto = estudianteService.obtenerPorId(inscripcion.getEstudiante().getID());
                inscripcion.setCurso(cursoCompleto);
                inscripcion.setEstudiante(estudianteCompleto);
            }
        }
        return inscripciones;
    }

    public Inscripción obtenerPorId(int estudianteId, int cursoId) {
        Inscripción inscripcion = inscripcionDAO.obtenerPorId(estudianteId, cursoId);
        if (inscripcion != null) {
            Curso cursoCompleto = cursoService.obtenerPorId(inscripcion.getCurso().getID());
            Estudiante estudianteCompleto = estudianteService.obtenerPorId(inscripcion.getEstudiante().getID());
            inscripcion.setCurso(cursoCompleto);
            inscripcion.setEstudiante(estudianteCompleto);
        }
        return inscripcion;
    }

    public void actualizarInscripcion(Inscripción inscripcion) {
        inscripcionDAO.actualizar(inscripcion);
    }

    public void eliminarInscripcion(int estudianteId, int cursoId) {
        inscripcionDAO.eliminar(estudianteId, cursoId);
    }
}