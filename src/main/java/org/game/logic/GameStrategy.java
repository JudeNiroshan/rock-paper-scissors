package org.game.logic;

import org.game.player.Player;

import java.util.Optional;

public interface GameStrategy {
    Optional<Player> determineWinner(Player... players) throws Exception;
}
