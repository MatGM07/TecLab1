package com.ejercicio;

import com.ejercicio.Estudiante;
import com.ejercicio.Inscripción;
import com.ejercicio.Programa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAOImplementacion implements InscripcionDAO {
    private Connection conexion;

    public InscripcionDAOImplementacion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(Inscripción inscripcion) {
        String sql = "INSERT INTO inscripciones (estudiante_id, programa_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, inscripcion.getEstudiante().getId());
            stmt.setInt(2, inscripcion.getPrograma().getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Inscripción inscripcion) {
        String sql = "UPDATE inscripciones SET programa_id = ? WHERE estudiante_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, inscripcion.getPrograma().getId());
            stmt.setInt(2, inscripcion.getEstudiante().getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int idEstudiante, int idPrograma) {
        String sql = "DELETE FROM inscripciones WHERE estudiante_id = ? AND programa_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idEstudiante);
            stmt.setInt(2, idPrograma);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Inscripción obtenerPorId(int idEstudiante, int idPrograma) {
        Inscripción inscripcion = null;
        String sql = "SELECT * FROM inscripciones WHERE estudiante_id = ? AND programa_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idEstudiante);
            stmt.setInt(2, idPrograma);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Estudiante estudiante = obtenerEstudiantePorId(idEstudiante);
                    Programa programa = obtenerProgramaPorId(idPrograma);
                    inscripcion = new Inscripción(estudiante, programa);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inscripcion;
    }

    @Override
    public List<Inscripción> obtenerTodos() {
        List<Inscripción> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM inscripciones";
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Estudiante estudiante = obtenerEstudiantePorId(rs.getInt("estudiante_id"));
                Programa programa = obtenerProgramaPorId(rs.getInt("programa_id"));
                inscripciones.add(new Inscripción(estudiante, programa));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inscripciones;
    }

    private Estudiante obtenerEstudiantePorId(int id) {
        Estudiante estudiante = null;
        String sql = "SELECT * FROM estudiantes WHERE ID = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estudiante = new Estudiante(
                        rs.getInt("ID"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getDouble("codigo"),
                        obtenerProgramaPorId(rs.getInt("programa_id")),
                        rs.getBoolean("activo"),
                        rs.getDouble("promedio")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estudiante;
    }

    private Programa obtenerProgramaPorId(int programaId) {
        Programa programa = null;
        String sql = "SELECT * FROM programas WHERE ID = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, programaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    programa = new Programa(
                        rs.getInt("ID"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return programa;
    }
}



