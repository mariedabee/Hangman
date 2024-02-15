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


    public HangmanUI(String[] words, int maxAttempts) {
        hangmanGame = new HangmanGameService(words, maxAttempts);

        setTitle("Hangman Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        secretWordLabel = new JLabel(hangmanGame.getCurrentProgress());
        secretWordLabel.setFont(new Font("Arial", Font.BOLD, 24));
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
}
