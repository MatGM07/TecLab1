package com.ejercicio.DAOImplementacion;

import com.ejercicio.DAO.InscripcionDAO;
import com.ejercicio.modelos.Curso;
import com.ejercicio.modelos.Estudiante;
import com.ejercicio.modelos.Inscripción;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAOImplementacion implements InscripcionDAO {
    private Connection conexion;

    public InscripcionDAOImplementacion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(Inscripción inscripcion) {
        String sql = "INSERT INTO Inscripcion (curso_id, estudiante_id, año, semestre) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, inscripcion.getCurso().getID());
            stmt.setInt(2, inscripcion.getEstudiante().getID());
            stmt.setInt(3, inscripcion.getAño());
            stmt.setInt(4, inscripcion.getSemestre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Inscripción obtenerPorId(int estudianteId, int cursoId) {
        String sql = "SELECT * FROM Inscripcion WHERE estudiante_id = ? AND curso_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, estudianteId);
            stmt.setInt(2, cursoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Inscripción(
                        new Curso(rs.getInt("curso_id"), null, -1, null),
                        rs.getInt("año"),
                        rs.getInt("semestre"),
                        new Estudiante(rs.getInt("estudiante_id"), null, null, null, 0.0, -1, false, 0.0)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Inscripción> obtenerTodos() {
        List<Inscripción> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM Inscripcion";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                inscripciones.add(new Inscripción(
                        new Curso(rs.getInt("curso_id"), null, -1, null),
                        rs.getInt("año"),
                        rs.getInt("semestre"),
                        new Estudiante(rs.getInt("estudiante_id"), null, null, null, 0.0, -1, false, 0.0)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripciones;
    }

    @Override
    public void actualizar(Inscripción inscripcion) {
        String sql = "UPDATE Inscripcion SET año=?, semestre=? WHERE estudiante_id=? AND curso_id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, inscripcion.getAño());
            stmt.setInt(2, inscripcion.getSemestre());
            stmt.setInt(3, inscripcion.getEstudiante().getID());
            stmt.setInt(4, inscripcion.getCurso().getID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int estudianteId, int cursoId) {
        String sql = "DELETE FROM Inscripcion WHERE estudiante_id=? AND curso_id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, estudianteId);
            stmt.setInt(2, cursoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}