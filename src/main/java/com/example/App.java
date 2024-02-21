package com.example;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String[] words = objectMapper.readValue(new File("src/main/java/com/shared/words.json"), String[].class);
            int maxAttempts = 6;

            // Create HangmanUI instance
            HangmanUI hangmanUI = new HangmanUI(words, maxAttempts);

            // Create HangmanUIEventHandler instance and add as action listener
            HangmanUIEventHandler eventHandler = new HangmanUIEventHandler(hangmanUI );
            hangmanUI.addGuessButtonListener(eventHandler);

            // Set the UI visible
            hangmanUI.setVisible(true);
        } catch (IOException e) {
            System.err.println("Failed to read words from file: " + e.getMessage());
        }
    }
}
