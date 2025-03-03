package com.ejercicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UniversidadGUI extends JFrame {
    private JTabbedPane tabbedPane;
    private DefaultTableModel estudiantesModel;
    private JTable tablaEstudiantes;
    
    public UniversidadGUI() {
        setTitle("Gestión Universitaria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        tabbedPane = new JTabbedPane();
        tabbedPane.add("Estudiantes", crearPanelEstudiantes());
        tabbedPane.add("Cursos", crearPanelCursos());
        tabbedPane.add("Profesores", crearPanelProfesores());
        tabbedPane.add("Inscripciones", crearPanelInscripciones());
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel crearPanelEstudiantes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(15);
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(lblNombre, gbc);
        gbc.gridx = 1;
        formPanel.add(txtNombre, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(btnAgregar, gbc);
        gbc.gridx = 1;
        formPanel.add(btnEliminar, gbc);
        
        estudiantesModel = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
        tablaEstudiantes = new JTable(estudiantesModel);
        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);
        
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                agregarEstudiante(nombre);
                cargarEstudiantes();
            }
        });
        
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tablaEstudiantes.getSelectedRow();
                if (fila != -1) {
                    int id = (int) estudiantesModel.getValueAt(fila, 0);
                    eliminarEstudiante(id);
                    cargarEstudiantes();
                }
            }
        });
        
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        cargarEstudiantes();
        return panel;
    }
    
    private void agregarEstudiante(String nombre) {
        try (Connection con = DriverManager.getConnection("jdbc:h2:./universidad", "sa", "");
             PreparedStatement stmt = con.prepareStatement("INSERT INTO estudiantes (nombre) VALUES (?)")) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void eliminarEstudiante(int id) {
        try (Connection con = DriverManager.getConnection("jdbc:h2:./universidad", "sa", "");
             PreparedStatement stmt = con.prepareStatement("DELETE FROM estudiantes WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void cargarEstudiantes() {
        estudiantesModel.setRowCount(0);
        try (Connection con = DriverManager.getConnection("jdbc:h2:./universidad", "sa", "");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM estudiantes")) {
            while (rs.next()) {
                estudiantesModel.addRow(new Object[]{rs.getInt("id"), rs.getString("nombre")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private JPanel crearPanelCursos() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Gestión de Cursos"));
        return panel;
    }
    
    private JPanel crearPanelProfesores() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Gestión de Profesores"));
        return panel;
    }
    
    private JPanel crearPanelInscripciones() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Gestión de Inscripciones"));
        return panel;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UniversidadGUI().setVisible(true);
        });
    }
}
