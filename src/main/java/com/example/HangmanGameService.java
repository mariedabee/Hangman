package com.example;

import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class HangmanGameService {
    private String secretWord;
    private Set<Character> guessedLetters;
    private int maxAttempts;
    private int attemptsLeft;

    public HangmanGameService(String[] words, int maxAttempts) {
        this.secretWord = getRandomWord(words);
        this.guessedLetters = new HashSet<>();
        this.maxAttempts = maxAttempts;
        this.attemptsLeft = maxAttempts;
    }

    private String getRandomWord(String[] words) {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

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

    public boolean isGameOver() {
        return isWordGuessed() || attemptsLeft <= 0;
    }

    public boolean isWordGuessed() {
        for (char c : secretWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public String getSecretWord() {
        return secretWord;
    }
}
