package com.example;

import java.io.File;
import java.io.IOException;

import javax.swing.SwingUtilities;
import com.example.HangmanUI;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String[] words = objectMapper.readValue(new File("src/main/java/com/shared/words.json"), String[].class);
            int maxAttempts = 6;
            HangmanUI hangmanUI = new HangmanUI(words, maxAttempts);
            HangmanUIEventHandler eventHandler = new HangmanUIEventHandler(hangmanUI);
            hangmanUI.addGuessButtonListener(eventHandler);

            hangmanUI.setVisible(true);
        } catch (IOException e) {
            System.err.println("Failed to read words from file: " + e.getMessage());
        }
    }

}
