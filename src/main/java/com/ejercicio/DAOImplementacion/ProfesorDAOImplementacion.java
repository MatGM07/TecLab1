package com.ejercicio.DAOImplementacion;
import com.ejercicio.DAO.ProfesorDAO;
import com.ejercicio.modelos.Profesor;

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
        String sql = "INSERT INTO Profesor (tipo_contrato, nombre, apellidos, correo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, profesor.getTipoContrato());
            stmt.setString(2, profesor.getNombre());
            stmt.setString(3, profesor.getApellidos());
            stmt.setString(4, profesor.getEmail());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);
                        System.out.println("ID generado: " + idGenerado);
                        profesor.setID(idGenerado);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Profesor obtenerPorId(int id) {
        String sql = "SELECT * FROM Profesor WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Profesor(
                        rs.getInt("id"),
                        rs.getString("tipo_contrato"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("correo")
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
                        rs.getInt("id"),
                        rs.getString("tipo_contrato"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("correo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profesores;
    }

    @Override
    public void actualizar(Profesor profesor) {
        String sql = "UPDATE Profesor SET tipo_contrato = ?, nombre = ?, apellidos = ?, correo = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, profesor.getTipoContrato());
            stmt.setString(2, profesor.getNombre());
            stmt.setString(3, profesor.getApellidos());
            stmt.setString(4, profesor.getEmail());
            stmt.setInt(5, profesor.getID().intValue());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Profesor WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}