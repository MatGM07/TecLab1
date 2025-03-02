package com.ejercicio.DAOImplementacion;

import com.ejercicio.DAO.ProgramaDAO;
import com.ejercicio.modelos.Programa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaDAOImplementacion implements ProgramaDAO {
    private Connection conexion;

    public ProgramaDAOImplementacion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(Programa programa) {
        String sql = "INSERT INTO Programa (nombre, duracion, registro, facultad_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, programa.getNombre());
            stmt.setDouble(2, programa.getDuracion());
            stmt.setDate(3, programa.getRegistro());
            stmt.setInt(4, programa.getFacultad().getID());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);
                        System.out.println("ID generado: " + idGenerado);
                        programa.setID(idGenerado);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Programa obtenerPorId(int id) {
        String sql = "SELECT p.id, p.nombre, p.duracion, p.registro, p.facultad_id " +
                "FROM Programa p " +
                "WHERE p.id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Programa(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("duracion"),
                        rs.getDate("registro"),
                        rs.getInt("facultad_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Programa> obtenerTodos() {
        List<Programa> programas = new ArrayList<>();
        String sql = "SELECT p.id, p.nombre, p.duracion, p.registro, p.facultad_id " +
                "FROM Programa p ";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                programas.add(new Programa(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("duracion"),
                        rs.getDate("registro"),
                        rs.getInt("facultad_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programas;
    }

    @Override
    public void actualizar(Programa programa) {
        String sql = "UPDATE Programa SET nombre=?,duracion=?,registro=?,facultad_id=? WHERE id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, programa.getNombre());
            stmt.setDouble(2, programa.getDuracion());
            stmt.setDate(3, programa.getRegistro());
            stmt.setInt(4, programa.getFacultad().getID());
            stmt.setInt(5, programa.getID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Programa WHERE id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
