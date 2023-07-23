package org.game.player.impl;

import io.quarkus.test.junit.QuarkusTest;
import org.game.controls.Choice;
import org.game.integration.ConnectorProvider;
import org.game.player.PlayerType;
import org.game.player.TestConsole;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class HumanPlayerTest {
    TestConsole testConsole = new TestConsole();
    HumanPlayer classUnderTest = new HumanPlayer();
    private static final InputStream systemIn = System.in;

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

//    @BeforeAll
//    public static void mockConnector() {
//        ConnectorProvider.getInstance().setConfiguredConnectorName("console");
//    }

    @AfterAll
    public static void restoreSystemInput() {
        System.setIn(systemIn);
    }

    @Test
    void getPlayerType() {
        Assertions.assertEquals(PlayerType.HUMAN, classUnderTest.getPlayerType());
    }

    @Test
    void engage_with_rockChoice() throws Exception {
        // preparation
        classUnderTest.choice = null;
        testConsole.setChoice(Choice.ROCK);
        testConsole.setNumberOfGames(1);
        ConnectorProvider.getInstance().setExternalResourceConnector(testConsole);
        provideInput("1");
        // test
        classUnderTest.engage();

        // verification
        assertEquals(testConsole.getChoice(), classUnderTest.choice);
    }

    @Test
    void engage_with_paperChoice() throws Exception {
        // preparation
        classUnderTest.choice = null;
        testConsole.setChoice(Choice.PAPER);
        testConsole.setNumberOfGames(1);
        ConnectorProvider.getInstance().setExternalResourceConnector(testConsole);
        provideInput("2");
        // test
        classUnderTest.engage();

        // verification
        assertEquals(testConsole.getChoice(), classUnderTest.choice);
    }

    @Test
    void engage_with_scissorsChoice() throws Exception {
        // preparation
        classUnderTest.choice = null;
        testConsole.setChoice(Choice.SCISSORS);
        testConsole.setNumberOfGames(1);
        ConnectorProvider.getInstance().setExternalResourceConnector(testConsole);
        provideInput("3");
        // test
        classUnderTest.engage();

        // verification
        assertEquals(testConsole.getChoice(), classUnderTest.choice);
    }

    @Test
    void getChoice() throws Exception {
        classUnderTest.choice = Choice.PAPER;
        assertEquals(Choice.PAPER, classUnderTest.getChoice());
    }
}