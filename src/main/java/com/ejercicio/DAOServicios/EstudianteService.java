package com.ejercicio.DAOServicios;
import com.ejercicio.DAO.EstudianteDAO;
import com.ejercicio.DAOImplementacion.EstudianteDAOImplementacion;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Persona;
import com.ejercicio.modelos.Programa;

import java.util.List;
import java.sql.Connection;

public class EstudianteService {
    private EstudianteDAO estudianteDAO;
    private PersonaService personaService;
    private ProgramaService programaService;

    public EstudianteService(Connection conexion) {
        this.estudianteDAO = new EstudianteDAOImplementacion(conexion);
        this.personaService = new PersonaService(conexion);
        this.programaService = new ProgramaService(conexion);
    }

    public void registrarEstudiante(Estudiante estudiante) {
        personaService.registrarPersona(estudiante);
        estudianteDAO.insertar(estudiante);
    }

    public List<Estudiante> obtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = estudianteDAO.obtenerTodos();
        for (Estudiante estudiante : estudiantes){
            if (estudiante != null && estudiante.getPrograma().getID() > 0) {
                Programa programaCompleto = new Programa();
                programaCompleto = programaService.obtenerPorId(estudiante.getPrograma().getID());
                estudiante.setPrograma(programaCompleto);
            }
            Integer persona_id = estudianteDAO.obtenerPersonaID(estudiante.getID());
            Persona persona = personaService.obtenerPorId(persona_id);

            estudiante.setNombre(persona.getNombre());
            estudiante.setApellidos(persona.getApellidos());
            estudiante.setEmail(persona.getEmail());
        }
        return estudiantes;
    }

    public Integer obtenerNumeroId(int id){
        return estudianteDAO.obtenerPersonaID(id);
    }

    public Estudiante obtenerPorId(int id) {
        Estudiante estudiante = estudianteDAO.obtenerPorId(id);

        if (estudiante != null && estudiante.getPrograma().getID() > 0) {
            Programa programaCompleto = programaService.obtenerPorId(estudiante.getPrograma().getID());
            estudiante.setPrograma(programaCompleto);
        }

        Integer persona_id = estudianteDAO.obtenerPersonaID(id);
        Persona persona = personaService.obtenerPorId(persona_id);

        estudiante.setNombre(persona.getNombre());
        estudiante.setApellidos(persona.getApellidos());
        estudiante.setEmail(persona.getEmail());
        return estudiante;
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        estudianteDAO.actualizar(estudiante);
    }

    public void eliminarEstudiante(int id) {
        estudianteDAO.eliminar(id);
    }
}
