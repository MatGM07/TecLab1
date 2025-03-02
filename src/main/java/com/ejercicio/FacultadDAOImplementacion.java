package com.ejercicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultadDAOImplementacion implements FacultadDAO{
    private Connection conexion;

    public FacultadDAOImplementacion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(Facultad facultad) {
        String sql = "INSERT INTO Facultad (nombre, decano_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, facultad.getNombre());
            stmt.setInt(2, facultad.getDecano().getID());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);
                        System.out.println("ID generado: " + idGenerado);
                        facultad.setID(idGenerado);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Facultad obtenerPorId(int id) {
        String sql = "SELECT f.id, f.nombre, f.decano_id " +
                "FROM Facultad f " +
                "WHERE f.id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Facultad(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("decano_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Facultad> obtenerTodos() {
        List<Facultad> facultads = new ArrayList<>();
        String sql = "SELECT f.id, f.nombre, f.decano_id " +
                "FROM Facultad f ";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                facultads.add(new Facultad(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("decano_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultads;
    }

    @Override
    public void actualizar(Facultad facultad) {
        String sql = "UPDATE Facultad SET nombre=?, decano_id=? WHERE id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, facultad.getNombre());
            stmt.setInt(2, facultad.getDecano().getID());
            stmt.setInt(3, facultad.getID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Facultad WHERE id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
