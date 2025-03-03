package com.ejercicio.DAOImplementacion;
import com.ejercicio.DAO.EstudianteDAO;
import com.ejercicio.modelos.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOImplementacion implements EstudianteDAO {
    private Connection conexion;

    public EstudianteDAOImplementacion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(Estudiante estudiante) {
        String sql = "INSERT INTO Estudiante (persona_id, codigo, programa_id, activo, promedio) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, estudiante.getID());
            stmt.setDouble(2, estudiante.getCodigo());
            stmt.setInt(3, estudiante.getPrograma().getID());
            stmt.setBoolean(4, estudiante.getActivo());
            stmt.setDouble(5, estudiante.getPromedio());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer obtenerPersonaID(int id){
        String sql = "SELECT e.id, e.persona_id " +
                "FROM Estudiante e " +
                "WHERE e.id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("persona_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Estudiante obtenerPorId(int id) {
        String sql = "SELECT e.id, e.persona_id, e.codigo, e.activo, e.promedio, e.programa_id " +
                "FROM Estudiante e " +
                "WHERE e.id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Estudiante(
                        rs.getInt("id"),
                        rs.getDouble("codigo"),
                        rs.getInt("programa_id"),
                        rs.getBoolean("activo"),
                        rs.getDouble("promedio")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Estudiante> obtenerTodos() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT e.id, e.persona_id, e.codigo, e.activo, e.promedio, e.programa_id " +
                "FROM Estudiante e ";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                estudiantes.add(new Estudiante(
                        rs.getInt("id"),
                        rs.getDouble("codigo"),
                        rs.getInt("programa_id"),
                        rs.getBoolean("activo"),
                        rs.getDouble("promedio")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }

    @Override
    public void actualizar(Estudiante estudiante) {
        String sql = "UPDATE Estudiante SET codigo=?, programa_id=?, activo=?, promedio=? WHERE id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setDouble(1, estudiante.getCodigo());
            stmt.setInt(2, estudiante.getPrograma().getID());
            stmt.setBoolean(3, estudiante.getActivo());
            stmt.setDouble(4, estudiante.getPromedio());
            stmt.setInt(5, estudiante.getID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Estudiante WHERE id=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}