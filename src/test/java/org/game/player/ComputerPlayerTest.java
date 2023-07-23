package org.game.player;

import org.game.controls.Choice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComputerPlayerTest {

    ComputerPlayer classUnderTest = new ComputerPlayer();

    @Test
    void getPlayerType() {
        assertEquals(PlayerType.COMPUTER, classUnderTest.getPlayerType());
    }

    @Test
    void engage() {
        // test
        classUnderTest.engage();

        // verification
        assertTrue(classUnderTest.choice == Choice.ROCK ||
                classUnderTest.choice == Choice.SCISSORS || classUnderTest.choice == Choice.PAPER);
    }

    @Test
    void getChoice() {
        classUnderTest.choice = Choice.PAPER;
        assertEquals(Choice.PAPER, classUnderTest.getChoice());
    }
}