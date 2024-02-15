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
            if (hangmanUI.getHangmanGame().guess(guess.charAt(0))) {
                hangmanUI.updateUI();
                if (hangmanUI.getHangmanGame().isGameOver()) {
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
        if (hangmanUI.getHangmanGame().isWordGuessed()) {
            hangmanUI.displayMessage("Congratulations! You guessed the word: " + hangmanUI.getHangmanGame().getSecretWord());
        } else {
            hangmanUI.displayMessage(
                    "Sorry, you ran out of attempts. The word was: " + hangmanUI.getHangmanGame().getSecretWord());
        }
        System.exit(0);
    }
}
