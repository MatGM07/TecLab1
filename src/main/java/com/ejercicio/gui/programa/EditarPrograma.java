package com.ejercicio.gui.programa;

import com.ejercicio.DAOServicios.FacultadService;
import com.ejercicio.DAOServicios.ProgramaService;
import com.ejercicio.controlador.ProgramaController;
import com.ejercicio.gui.programa.PanelPrograma;
import com.ejercicio.modelos.Facultad;
import com.ejercicio.modelos.Programa;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class EditarPrograma extends JPanel {
    private JTextField txtNombre, txtDuracion, txtFacultad_id;
    private JButton btnGuardar, btnCancelar;
    private JDateChooser dateChooserRegistro;
    private ProgramaController programaController;
    private PanelPrograma panelPrograma;


    public EditarPrograma(Integer id, ProgramaController programaController, PanelPrograma panelPrograma) {
        this.programaController = programaController;
        this.panelPrograma = panelPrograma;

        List<String> datosPrograma = programaController.obtenerDatosPorId(id);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(createFieldPanel("ID:", new JTextField(String.valueOf(id), 20), false));
        txtNombre = new JTextField(datosPrograma.get(0), 20);
        add(createFieldPanel("Nombre:", txtNombre, true));
        txtDuracion = new JTextField(String.valueOf(datosPrograma.get(1)), 20);
        add(createFieldPanel("Duración:", txtDuracion, true));

        dateChooserRegistro = new JDateChooser();
        String registro = String.valueOf(datosPrograma.get(2));
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date fechaConvertida = formato.parse(registro);
            dateChooserRegistro.setDate(fechaConvertida);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateChooserRegistro.setDateFormatString("yyyy-MM-dd");
        add(createFieldPanel("Fecha Registro:", dateChooserRegistro, true));

        txtFacultad_id = new JTextField(String.valueOf(datosPrograma.get(3)), 20);
        add(createFieldPanel("ID Facultad:", txtFacultad_id, true));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios(id));
        buttonPanel.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> panelPrograma.mostrarVistaPrincipal());
        buttonPanel.add(btnCancelar);

        add(buttonPanel);
    }

    private JPanel createFieldPanel(String label, JComponent field, boolean editable) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(label));
        if (field instanceof JTextField) {
            ((JTextField) field).setEditable(editable);
        }
        panel.add(field);
        return panel;
    }

    private void guardarCambios(Integer id) {
        try {
            String nombre = txtNombre.getText();
            Double duracion = Double.valueOf(txtDuracion.getText());

            java.util.Date fechaUtil = dateChooserRegistro.getDate();
            if (fechaUtil == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha válida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Date registro = new java.sql.Date(fechaUtil.getTime());

            Integer facultad_id = Integer.valueOf(txtFacultad_id.getText());

            programaController.actualizar(id,nombre,duracion,registro,facultad_id);

            JOptionPane.showMessageDialog(this, "Programa actualizado correctamente");
            panelPrograma.mostrarVistaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el programa: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
