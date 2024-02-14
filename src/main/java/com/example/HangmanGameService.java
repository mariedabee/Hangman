package com.example;

import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class HangmanGameService {
    private String secretWord;
    private Set<Character> guessedLetters;
    private int maxAttempts;
    private int attemptsLeft;

    /**
     * nitializes the game by selecting a random word from the given array of words
     * and setting the maximum number of attempts.
     * 
     * @param words       array of words
     * @param maxAttempts maximum number of attempts
     */
    public HangmanGameService(String[] words, int maxAttempts) {
        this.secretWord = getRandomWord(words);
        this.guessedLetters = new HashSet<>();
        this.maxAttempts = maxAttempts;
        this.attemptsLeft = maxAttempts;
    }

    /* 
     * Helper method to select a random word from the provided array of words. 
     */
    private String getRandomWord(String[] words) {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    /**
     * Handles a player's guess by checking if the guessed letter is in the secret
     * word and updating the game state accordingly.
     * 
     * @param letter
     * @return boolean
     */
    public boolean guess(char letter) {
        if (guessedLetters.contains(letter)) {
            return false; // Letter already guessed
        }

        guessedLetters.add(letter);
        if (!secretWord.contains(String.valueOf(letter))) {
            attemptsLeft--;
        }
        return true;
    }

    /**
     * Generates a string representing the current progress of the guessed word,
     * with underscores for unrevealed letters.
     * 
     * @return String
     */
    public String getCurrentProgress() {
        StringBuilder progress = new StringBuilder();
        for (char c : secretWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                progress.append(c);
            } else {
                progress.append('_');
            }
            progress.append(' ');
        }
        return progress.toString();
    }

    /**
     * Checks if the game is over by determining if the word has been guessed or if
     * there are no attempts left.
     * @return boolean 
     */
    public boolean isGameOver() {
        return isWordGuessed() || attemptsLeft <= 0;
    }

    /**
     * Checks if the word has been completely guessed by the player.
     * 
     * @return boolean
     */
    public boolean isWordGuessed() {
        for (char c : secretWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the number of attempts remaining for the player.
     * 
     * @return integer
     */
    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    /**
     * Returns the secret word (for testing purposes).
     * 
     * @return String
     */
    public String getSecretWord() {
        return secretWord;
    }
}
