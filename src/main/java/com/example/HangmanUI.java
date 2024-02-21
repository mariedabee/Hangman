package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HangmanUI extends JFrame {
    private HangmanGameService hangmanGame;
    private JLabel secretWordLabel;
    private JLabel attemptsLeftLabel;
    private JTextField guessField;
    private JButton guessButton;
    private HangmanDrawingPanel drawingPanel;

    public HangmanUI(String[] words, int maxAttempts) {
        hangmanGame = new HangmanGameService(words, maxAttempts);

        setTitle("Hangman Game");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(2, 3));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Initialize drawing panel
        // TODO: why still not visible
        drawingPanel = new HangmanDrawingPanel();
        drawingPanel.setPreferredSize(new Dimension(200, 400));
        setHangmanDrawingPanel(drawingPanel);
        add(drawingPanel, BorderLayout.WEST); // Add drawing panel to the left side of the frame
        
        secretWordLabel = new JLabel(hangmanGame.getCurrentProgress());
        secretWordLabel.setFont(new Font("Arial", Font.BOLD, 27));
        centerPanel.add(secretWordLabel);

        attemptsLeftLabel = new JLabel("Attempts left: " + hangmanGame.getAttemptsLeft());
        attemptsLeftLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(attemptsLeftLabel);

        add(centerPanel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.PLAIN, 18));
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        add(inputPanel, BorderLayout.SOUTH);
    }

    public HangmanGameService getHangmanGame() {
        return hangmanGame;
    }

    public void updateUI() {
        secretWordLabel.setText(hangmanGame.getCurrentProgress());
        attemptsLeftLabel.setText("Attempts left: " + hangmanGame.getAttemptsLeft());
    }

    public void addGuessButtonListener(ActionListener listener) {
        guessButton.addActionListener(listener);
    }

    public String getGuessInput() {
        return guessField.getText().toLowerCase();
    }

    public void clearGuessInput() {
        guessField.setText("");
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public HangmanDrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

    public void setHangmanDrawingPanel(HangmanDrawingPanel hangmanpanel) {
        this.drawingPanel = hangmanpanel;
    }

}
