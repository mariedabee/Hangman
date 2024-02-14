package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HangmanUI extends JFrame {
    public HangmanGameService hangmanGame;
    private JLabel secretWordLabel;
    private JLabel attemptsLeftLabel;
    private JTextField guessField;
    private JButton guessButton;

    public HangmanUI(String[] words, int maxAttempts) {
        hangmanGame = new HangmanGameService(words, maxAttempts);

        setTitle("Hangman Game - :)");
        setSize(600, 400); // Increase the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        secretWordLabel = new JLabel(hangmanGame.getCurrentProgress());
        attemptsLeftLabel = new JLabel("Attempts left: " + hangmanGame.getAttemptsLeft());
        guessField = new JTextField();
        guessButton = new JButton("Guess");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 2));
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        add(secretWordLabel);
        add(attemptsLeftLabel);
        add(inputPanel);
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
}
