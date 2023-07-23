package org.game.player;

import io.quarkus.test.junit.QuarkusTest;
import org.game.controls.Choice;
import org.game.util.GameLogger;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class HumanPlayerTest {
    HumanPlayer classUnderTest = new HumanPlayer();
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
    void getPlayerType() {
        assertEquals(PlayerType.HUMAN, classUnderTest.getPlayerType());
    }

    @Test
    void engage_with_rockChoice() throws Exception {
        // preparation
        classUnderTest.choice = null;
        provideInput("1");
        // test
        classUnderTest.engage();

        // verification
        assertEquals(Choice.ROCK, classUnderTest.choice);
    }

    @Test
    void engage_with_paperChoice() throws Exception {
        // preparation
        classUnderTest.choice = null;
        provideInput("2");
        // test
        classUnderTest.engage();

        // verification
        assertEquals(Choice.PAPER, classUnderTest.choice);
    }

    @Test
    void engage_with_scissorsChoice() throws Exception {
        // preparation
        classUnderTest.choice = null;
        provideInput("3");
        // test
        classUnderTest.engage();

        // verification
        assertEquals(Choice.SCISSORS, classUnderTest.choice);
    }

    @Test
    void engage_with_invalidNumericChoice() throws Exception {
        // preparation
        TestLogger testLogger = new TestLogger();
        GameLogger.setLogger(testLogger);
        provideInput("4\n 1\n");
        // test
        classUnderTest.engage();

        // verification
        assertEquals(3, testLogger.getLogMessages().get(Logger.Level.INFO).size());
        assertEquals("Invalid choice: " + 4, testLogger.getLogMessages().get(Logger.Level.INFO).get(1));
    }

    @Test
    void engage_with_invalidChoice() throws Exception {
        // preparation
        TestLogger testLogger = new TestLogger();
        GameLogger.setLogger(testLogger);
        provideInput("something \n 3 \n");
        // test
        classUnderTest.engage();


        // verification
        assertEquals(3, testLogger.getLogMessages().get(Logger.Level.INFO).size());
        assertEquals("Invalid input. Please enter a valid integer.",
                testLogger.getLogMessages().get(Logger.Level.INFO).get(1));
    }

    @Test
    void getChoice() throws Exception {
        classUnderTest.choice = Choice.PAPER;
        assertEquals(Choice.PAPER, classUnderTest.getChoice());
    }
}