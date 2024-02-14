package com.example;

import java.io.File;
import java.io.IOException;

import javax.swing.SwingUtilities;
import com.example.HangmanUI;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        // TODO: use words from json file instead
        String[] example = objectMapper.readValue(new File("../shared/words.json"), String[].class);
        SwingUtilities.invokeLater(() -> {

            String[] words = { "java", "programming", "computer", "hangman", "algorithm" };
            int maxAttempts = 6;

            HangmanUI hangmanUI = new HangmanUI(words, maxAttempts);
            HangmanUIEventHandler eventHandler = new HangmanUIEventHandler(hangmanUI);
            hangmanUI.addGuessButtonListener(eventHandler);

            hangmanUI.setVisible(true);
        });
    }

}
