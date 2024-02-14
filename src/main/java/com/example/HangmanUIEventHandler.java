package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanUIEventHandler implements ActionListener {
    private HangmanUI hangmanUI;

    public HangmanUIEventHandler(HangmanUI hangmanUI) {
        this.hangmanUI = hangmanUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String guess = hangmanUI.getGuessInput();
        if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) {
            if (hangmanUI.hangmanGame.guess(guess.charAt(0))) {
                hangmanUI.updateUI();
                if (hangmanUI.hangmanGame.isGameOver()) {
                    handleGameOver();
                }
            } else {
                hangmanUI.displayMessage("You have already guessed that letter. Try again.");
            }
        } else {
            hangmanUI.displayMessage("Please enter a single letter.");
        }
        hangmanUI.clearGuessInput();
    }

    private void handleGameOver() {
        if (hangmanUI.hangmanGame.isWordGuessed()) {
            hangmanUI.displayMessage("Congratulations! You guessed the word: " + hangmanUI.hangmanGame.getSecretWord());
        } else {
            hangmanUI.displayMessage(
                    "Sorry, you ran out of attempts. The word was: " + hangmanUI.hangmanGame.getSecretWord());
        }
        System.exit(0);
    }
}
