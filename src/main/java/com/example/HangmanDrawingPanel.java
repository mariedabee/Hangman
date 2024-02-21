package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class HangmanDrawingPanel extends JPanel {
    private List<Shape> shapes = new ArrayList<>();

    public void setIncorrectGuesses(int incorrectGuesses) {
        switch (incorrectGuesses) {
            case 6:
                // Draw head
                shapes.add(new Ellipse2D.Double(50, 50, 50, 50));
                break;
            case 5:
                // Draw body
                shapes.add(new Line2D.Double(75, 100, 75, 250));
                break;
            case 4:
                // Draw left arm
                shapes.add(new Line2D.Double(75, 150, 25, 100));
                break;
            case 3:
                // Draw right arm
                shapes.add(new Line2D.Double(75, 150, 125, 100));
                break;
            case 2:
                // Draw left leg
                shapes.add(new Line2D.Double(75, 250, 25, 300));
                break;
            case 1:
                // Draw right leg
                shapes.add(new Line2D.Double(75, 250, 125, 300));
                break;
        }
        repaint(); // Redraw the hangman figure
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        for (Shape shape : shapes) {
            g2d.draw(shape); // Draw all shapes
        }
    }
    
   
}
