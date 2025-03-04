package com.ejercicio.gui.persona;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

import com.ejercicio.ConexionDB;
import com.ejercicio.DAOServicios.PersonaService;
import com.ejercicio.gui.*;
import com.ejercicio.modelos.InscripcionesPersonas;
import com.ejercicio.modelos.Persona;

public class PanelPersona extends PanelBase {
    private PersonaService personaService;

    public PanelPersona(MainFrame mainFrame) {
        super(mainFrame);

        Connection connection = ConexionDB.obtenerConexion();
        this.personaService = new PersonaService(connection);

        btnAgregar.addActionListener(e -> abrirAgregarPersona());

        btnEditar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la persona a editar:", "Editar Persona", JOptionPane.QUESTION_MESSAGE);
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Persona persona = personaService.obtenerPorId(id);
                    if (persona != null) {
                        abrirEditarPersona(persona);
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
                    Persona persona = personaService.obtenerPorId(id);

                    if (persona != null) {
                        abrirEliminarPersona(persona);
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
                    Persona persona = personaService.obtenerPorId(id);
                    InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
                    inscripcionesPersonas.cargarDatos();
                    inscripcionesPersonas.imprimirPosicion(inscripcionesPersonas.encontrar(persona).get());

                    if (persona != null) {
                        abrirConsultarPersona(persona);
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
        AgregarPersona agregarPersona = new AgregarPersona(personaService, this);
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
        ListarPersonas listarPersonas = new ListarPersonas(personaService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(listarPersonas, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEditarPersona(Persona persona) {
        EditarPersona editarPersona = new EditarPersona(persona, personaService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(editarPersona, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirEliminarPersona(Persona persona) {
        EliminarPersona eliminarPersona = new EliminarPersona(persona, personaService, this);
        removeAll();
        setLayout(new BorderLayout());
        add(eliminarPersona, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void abrirConsultarPersona(Persona persona) {
        ConsultarPersona consultarPersona = new ConsultarPersona(persona, this);
        removeAll();
        setLayout(new BorderLayout());
        add(consultarPersona, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

}
