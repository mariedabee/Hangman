package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.example.HangmanGameService;

public class HangmanUI extends JFrame {
    private HangmanGameService hangmanGame;
    private JLabel secretWordLabel;
    private JLabel attemptsLeftLabel;
    private JTextField guessField;
    private JButton guessButton;

    public HangmanUI(String[] words, int maxAttempts) {
        hangmanGame = new HangmanGameService(words, maxAttempts);

        setTitle("Hangman Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        secretWordLabel = new JLabel(hangmanGame.getCurrentProgress());
        attemptsLeftLabel = new JLabel("Attempts left: " + hangmanGame.getAttemptsLeft());
        guessField = new JTextField();
        guessButton = new JButton("Guess");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guess = guessField.getText().toLowerCase();
                if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) {
                    if (hangmanGame.guess(guess.charAt(0))) {
                        secretWordLabel.setText(hangmanGame.getCurrentProgress());
                        attemptsLeftLabel.setText("Attempts left: " + hangmanGame.getAttemptsLeft());
                        if (hangmanGame.isGameOver()) {
                            handleGameOver();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "You have already guessed that letter. Try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a single letter.");
                }
                guessField.setText("");
            }
        });

        add(secretWordLabel);
        add(attemptsLeftLabel);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 2));
        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        add(inputPanel);

        setVisible(true);
    }

    private void handleGameOver() {
        if (hangmanGame.isWordGuessed()) {
            JOptionPane.showMessageDialog(null,
                    "Congratulations! You guessed the word: " + hangmanGame.getSecretWord());
        } else {
            JOptionPane.showMessageDialog(null,
                    "Sorry, you ran out of attempts. The word was: " + hangmanGame.getSecretWord());
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String[] words = { "java", "programming", "computer", "hangman", "algorithm" };
                int maxAttempts = 6;
                new HangmanUI(words, maxAttempts);
            }
        });
    }
}
