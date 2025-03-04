package com.ejercicio.DAOImplementacion;


import com.ejercicio.DAO.CursoProfesorDAO;
import com.ejercicio.modelos.Curso;
import com.ejercicio.modelos.Profesor;
import com.ejercicio.modelos.CursoProfesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoProfesorDAOImplementacion implements CursoProfesorDAO {
    private Connection conexion;

    public CursoProfesorDAOImplementacion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(CursoProfesor cursoProfesor) {
        String sql = "INSERT INTO CursoProfesor (curso_id, profesor_id, año, semestre) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cursoProfesor.getCurso().getID());
            stmt.setInt(2, cursoProfesor.getProfesor().getID());
            stmt.setInt(3, cursoProfesor.getAño());
            stmt.setInt(4, cursoProfesor.getSemestre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CursoProfesor obtenerPorId(int profesorId, int cursoId) {
        String sql = "SELECT * FROM CursoProfesor WHERE profesor_id = ? AND curso_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, profesorId);
            stmt.setInt(2, cursoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CursoProfesor(
                        new Profesor(rs.getInt("profesor_id"), null, null, null, null),
                        rs.getInt("año"),
                        rs.getInt("semestre"),
                        new Curso(rs.getInt("curso_id"), null, -1, null)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CursoProfesor> obtenerTodos() {
        List<CursoProfesor> cursoProfesores = new ArrayList<>();
        String sql = "SELECT * FROM CursoProfesor";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cursoProfesores.add(new CursoProfesor(
                        new Profesor(rs.getInt("profesor_id"), null, null, null, null),
                        rs.getInt("año"),
                        rs.getInt("semestre"),
                        new Curso(rs.getInt("curso_id"), null, -1, null)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursoProfesores;
    }

    @Override
    public void actualizar(CursoProfesor cursoProfesor) {
        String sql = "UPDATE CursoProfesor SET año=?, semestre=? WHERE profesor_id=? AND curso_id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cursoProfesor.getAño());
            stmt.setInt(2, cursoProfesor.getSemestre());
            stmt.setInt(3, cursoProfesor.getProfesor().getID());
            stmt.setInt(4, cursoProfesor.getCurso().getID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int profesorId, int cursoId) {
        String sql = "DELETE FROM CursoProfesor WHERE profesor_id=? AND curso_id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, profesorId);
            stmt.setInt(2, cursoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}