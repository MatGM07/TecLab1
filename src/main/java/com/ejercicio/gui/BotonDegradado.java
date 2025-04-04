package com.ejercicio.gui;

import javax.swing.*;
import java.awt.*;

public class BotonDegradado extends JButton {
    private Color color1 = new Color(153, 255, 153);
    private Color color2 = Color.WHITE;

    public BotonDegradado(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
            g2.setPaint(gradient);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
        super.paintComponent(g);
    }
}

