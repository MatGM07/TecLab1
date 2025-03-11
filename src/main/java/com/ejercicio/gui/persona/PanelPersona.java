package com.ejercicio.gui.persona;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.controlador.PersonaController;
import com.ejercicio.gui.*;
import com.ejercicio.modelos.InscripcionesPersonas;
import com.ejercicio.modelos.Persona;

public class PanelPersona extends PanelBase {
    private PersonaController personaController;

    public PanelPersona(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionDB.obtenerConexion();
        this.personaController = new PersonaController(connection);

        btnAgregar.addActionListener(e -> abrirAgregarPersona());

        btnEditar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la persona a editar:", "Editar Persona", JOptionPane.QUESTION_MESSAGE);
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = personaController.existe(id);
                    if (existe) {
                        abrirEditarPersona(id);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una persona con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la persona a eliminar:", "Eliminar Persona", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = personaController.existe(id);

                    if (existe) {
                        abrirEliminarPersona(id);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una persona con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnConsultar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la persona a consultar:", "Consultar Persona", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Boolean existe = personaController.existe(id);


                    if (existe) {
                        abrirConsultarPersona(id);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró una persona con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(e -> abrirListarPersonas());
    }

    private void abrirAgregarPersona() {
        AgregarPersona agregarPersona = new AgregarPersona(personaController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(agregarPersona, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void mostrarVistaPrincipal() {
        removeAll();
        setLayout(new BorderLayout());
        JPanel panelBotones = new JPanel();

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.NORTH);

        revalidate();
        repaint();
    }

    private void abrirListarPersonas() {
        ListarPersonas listarPersonas = new ListarPersonas(personaController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarPersonas, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarPersona(Integer id) {
        EditarPersona editarPersona = new EditarPersona(id, personaController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarPersona, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarPersona(Integer id) {
        EliminarPersona eliminarPersona = new EliminarPersona(id, personaController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarPersona, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarPersona(Integer id) {
        ConsultarPersona consultarPersona = new ConsultarPersona(id, personaController, this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarPersona, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

}
