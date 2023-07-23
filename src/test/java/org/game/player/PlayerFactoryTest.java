package org.game.player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlayerFactoryTest {

    @Test
    void createPlayer_computer() {
        Player player = PlayerFactory.createPlayer(PlayerType.COMPUTER);
        assertNotNull(player);
        assertEquals(PlayerType.COMPUTER, player.getPlayerType());
    }
    @Test
    void createPlayer_human() {
        Player player = PlayerFactory.createPlayer(PlayerType.HUMAN);
        assertNotNull(player);
        assertEquals(PlayerType.HUMAN, player.getPlayerType());
    }

    @Test
    void createPlayer_npc() {
        Player player = PlayerFactory.createPlayer(PlayerType.NPC);
        assertNotNull(player);
        assertEquals(PlayerType.NPC, player.getPlayerType());
    }
}