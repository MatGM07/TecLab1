package com.ejercicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAOImplementacion implements PersonaDAO {
    private Connection conexion;

    public PersonaDAOImplementacion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(Persona persona) {
        String sql = "INSERT INTO Persona (email, apellidos, nombres) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, persona.getEmail());
            stmt.setString(2, persona.getApellidos());
            stmt.setString(3, persona.getNombre());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);
                        System.out.println("ID generado: " + idGenerado);
                        persona.setID(idGenerado);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Persona obtenerPorId(int id) {
        String sql = "SELECT p.id, p.email, p.apellidos, p.nombres " +
                "FROM Persona p " +
                "WHERE p.id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Persona(
                        rs.getInt("id"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Persona> obtenerTodos() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT p.id, p.email, p.apellidos, p.nombres " +
                "FROM Persona p ";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                personas.add(new Persona(
                        rs.getInt("id"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }

    @Override
    public void actualizar(Persona persona) {
        String sql = "UPDATE Persona SET email=?, apellidos=?, nombres=? WHERE id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, persona.getEmail());
            stmt.setString(2, persona.getApellidos());
            stmt.setString(3, persona.getNombre());
            stmt.setInt(4, persona.getID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Persona WHERE id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
