package org.game.integration.impl;

import org.game.integration.ConnectorProvider;
import org.game.player.TestLogger;
import org.game.util.GameLogger;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleConnectorTest {
    ConsoleConnector classUnderTest = new ConsoleConnector();
    private static final InputStream systemIn = System.in;

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @AfterAll
    public static void restoreSystemInput() {
        System.setIn(systemIn);
    }

    @Test
    void getNumberOfGames_withValidInput() {
        provideInput("2 \n");
        classUnderTest.scanner = new Scanner(System.in);
        TestLogger testLogger = new TestLogger();
        GameLogger.setLogger(testLogger);
        ConnectorProvider.getInstance().setExternalResourceConnector(classUnderTest);
        // test
        int numberOfGames = classUnderTest.getNumberOfGames();

        // verification
        assertEquals(2, numberOfGames);
    }

    @Test
    void getNumberOfGames_withTextInput() {
        provideInput("abc \n 1");
        classUnderTest.scanner = new Scanner(System.in);
        TestLogger testLogger = new TestLogger();
        GameLogger.setLogger(testLogger);
        ConnectorProvider.getInstance().setExternalResourceConnector(classUnderTest);
        // test
        int numberOfGames = classUnderTest.getNumberOfGames();

        // verification
        assertEquals(1, numberOfGames);
        assertEquals("Invalid input. Please enter a positive integer." , testLogger.getLogMessages().get(Logger.Level.INFO).get(1));
    }

    @Test
    void getNumberOfGames_withNegativeInput() {
        provideInput("-1 \n 2");
        classUnderTest.scanner = new Scanner(System.in);
        TestLogger testLogger = new TestLogger();
        GameLogger.setLogger(testLogger);
        ConnectorProvider.getInstance().setExternalResourceConnector(classUnderTest);
        // test
        int numberOfGames = classUnderTest.getNumberOfGames();

        // verification
        assertEquals(2, numberOfGames);
        assertEquals("Invalid input. Please enter a positive integer.", testLogger.getLogMessages().get(Logger.Level.INFO).get(1));
    }

    @Test
    void getChoice() {
    }
}