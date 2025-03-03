package com.ejercicio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAOImplementacion implements ProfesorDAO {
    private Connection conexion;

    public ProfesorDAOImplementacion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(Profesor profesor) {
        String sql = "INSERT INTO Profesor (persona_id, tipo_contrato) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, profesor.getID().intValue());
            stmt.setString(2, profesor.getTipoContrato());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Profesor obtenerPorId(int id) {
        String sql = "SELECT * FROM Profesor WHERE persona_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Profesor(
                        (double) rs.getInt("persona_id"),
                        "", "", "", // Datos de Persona (se deben obtener de otra consulta)
                        rs.getString("tipo_contrato")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Profesor> obtenerTodos() {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT * FROM Profesor";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                profesores.add(new Profesor(
                        (double) rs.getInt("persona_id"),
                        "", "", "",
                        rs.getString("tipo_contrato")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profesores;
    }

    @Override
    public void actualizar(Profesor profesor) {
        String sql = "UPDATE Profesor SET tipo_contrato = ? WHERE persona_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, profesor.getTipoContrato());
            stmt.setInt(2, profesor.getID().intValue());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Profesor WHERE persona_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
