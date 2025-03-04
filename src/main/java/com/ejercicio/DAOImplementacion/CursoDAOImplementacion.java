package com.ejercicio.DAOImplementacion;

import com.ejercicio.DAO.CursoDAO;
import com.ejercicio.modelos.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOImplementacion implements CursoDAO {
    private Connection conexion;

    public CursoDAOImplementacion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(Curso curso) {
        String sql = "INSERT INTO Curso (activo, nombre, programa_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setBoolean(1, curso.getActivo());
            stmt.setString(2, curso.getNombre());
            stmt.setInt(3, curso.getPrograma().getID());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);
                        System.out.println("ID generado: " + idGenerado);
                        curso.setID(idGenerado);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Curso obtenerPorId(int id) {
        String sql = "SELECT p.id, p.nombre, p.activo, p.programa_id " +
                "FROM Curso p " +
                "WHERE p.id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Curso(
                        rs.getInt("id"),
                        rs.getBoolean("activo"),
                        rs.getInt("programa_id"),
                        rs.getString("nombre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Curso> obtenerTodos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT p.id, p.nombre, p.activo, p.programa_id  " +
                "FROM Curso p ";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cursos.add(new Curso(
                        rs.getInt("id"),
                        rs.getBoolean("activo"),
                        rs.getInt("programa_id"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public void actualizar(Curso curso) {
        String sql = "UPDATE Curso SET nombre=?,activo=?,programa_id=? WHERE id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, curso.getNombre());
            stmt.setBoolean(2, curso.getActivo());
            stmt.setInt(3, curso.getPrograma().getID());
            stmt.setInt(4, curso.getID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Curso WHERE id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
