package org.game.logic.rock_paper_scissors;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.game.controls.Choice;
import org.game.logic.GameStrategy;
import org.game.player.NPCPlayer;
import org.game.player.Player;
import org.game.player.PlayerFactory;
import org.game.player.PlayerType;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RockPaperScissorStrategyTest {

    @Inject
    @Named("Rock-Paper-Scissors")
    GameStrategy gameStrategy;

    @Test
    void whenSameChoice_determineWinner_shouldReturnNoWinner() throws Exception {
        // preparation
        NPCPlayer player1 = (NPCPlayer) PlayerFactory.createPlayer(PlayerType.NPC);
        NPCPlayer player2 = (NPCPlayer) PlayerFactory.createPlayer(PlayerType.NPC);
        player1.setChoice(Choice.ROCK);
        player2.setChoice(Choice.ROCK);

        // test
        Optional<Player> winner = gameStrategy.determineWinner(player1, player2);

        // verification
        assertTrue(winner.isEmpty());
    }

    @Test
    void whenPlayer1Wins_determineWinner_shouldReturnPlayer1() throws Exception {
        // preparation
        NPCPlayer player1 = (NPCPlayer) PlayerFactory.createPlayer(PlayerType.NPC);
        NPCPlayer player2 = (NPCPlayer) PlayerFactory.createPlayer(PlayerType.NPC);
        player1.setChoice(Choice.PAPER);
        player2.setChoice(Choice.ROCK);

        // test
        Optional<Player> winner = gameStrategy.determineWinner(player1, player2);

        // verification
        assertTrue(winner.isPresent());
        assertEquals(player1, winner.get());
    }

    @Test
    void whenPlayer2Wins_determineWinner_shouldReturnPlayer2() throws Exception {
        // preparation
        NPCPlayer player1 = (NPCPlayer) PlayerFactory.createPlayer(PlayerType.NPC);
        NPCPlayer player2 = (NPCPlayer) PlayerFactory.createPlayer(PlayerType.NPC);
        player1.setChoice(Choice.SCISSORS);
        player2.setChoice(Choice.ROCK);

        // test
        Optional<Player> winner = gameStrategy.determineWinner(player1, player2);

        // verification
        assertTrue(winner.isPresent());
        assertEquals(player2, winner.get());
    }

    @Test
    void whenInvalidNumberOfPlayers_determineWinner_shouldThrowsIllegalArgumentException() {
        // preparation
        NPCPlayer player1 = (NPCPlayer) PlayerFactory.createPlayer(PlayerType.NPC);
        NPCPlayer player2 = (NPCPlayer) PlayerFactory.createPlayer(PlayerType.NPC);
        NPCPlayer player3 = (NPCPlayer) PlayerFactory.createPlayer(PlayerType.NPC);
        player1.setChoice(Choice.ROCK);
        player2.setChoice(Choice.PAPER);
        player3.setChoice(Choice.SCISSORS);

        // test
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> gameStrategy.determineWinner());
        assertEquals("Only two players are allowed to play", exception1.getMessage());
        IllegalArgumentException exception2 =assertThrows(IllegalArgumentException.class, () -> gameStrategy.determineWinner(player1, player2, player3));
        assertEquals("Only two players are allowed to play", exception2.getMessage());
    }
}