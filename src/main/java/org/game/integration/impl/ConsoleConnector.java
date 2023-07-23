package org.game.integration.impl;

import org.game.controls.Choice;
import org.game.integration.ExternalResourceConnector;
import org.game.util.GameLogger;

import java.util.Scanner;

import static org.game.player.Player.maxPossibleChoice;
import static org.game.player.Player.minPossibleChoice;

public class ConsoleConnector implements ExternalResourceConnector {

    Scanner scanner = new Scanner(System.in);
    @Override
    public int getNumberOfGames() {
        GameLogger.info("How many games do you want to play?");
        while (true) {
            if (scanner.hasNextInt()) {
                int inputNumber = scanner.nextInt();
                if (inputNumber > 0) {
                    return inputNumber;
                } else {
                    GameLogger.info("Invalid input. Please enter a positive integer.");
                }
            } else {
                GameLogger.info("Invalid input. Please enter a positive integer.");
                scanner.next(); // Consume the invalid input to avoid an infinite loop
            }
        }
    }

    @Override
    public Choice getChoice() {
        int playerChoice;
        while (true) {
            GameLogger.info("Enter your number: (1-rock, 2-paper, 3-scissors):");
            if (scanner.hasNextInt()) {
                playerChoice = scanner.nextInt();
                if (playerChoice >= minPossibleChoice && playerChoice <= maxPossibleChoice) {
                    return Choice.values()[playerChoice - 1];
                } else {
                    GameLogger.info("Invalid choice: " + playerChoice);
                }
            } else {
                GameLogger.info("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input to avoid an infinite loop
            }
        }
    }

    @Override
    public void close() {
        scanner.close();
    }
}
