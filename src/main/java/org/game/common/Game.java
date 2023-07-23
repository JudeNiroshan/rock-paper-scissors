package org.game.common;

import org.game.logic.GameStrategy;
import org.game.player.Player;

import java.util.List;
import java.util.Optional;

public class Game {
    private final GameStrategy gameStrategy;
    private final List<Player> players;

    public Game(GameStrategy gameStrategy, List<Player> players) {
        this.gameStrategy = gameStrategy;
        this.players = players;
    }

    public Optional<Player> play() throws Exception {
        return gameStrategy.determineWinner(players.toArray(new Player[0]));
    }
}
