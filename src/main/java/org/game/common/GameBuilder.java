package org.game.common;

import jakarta.enterprise.context.ApplicationScoped;
import org.game.logic.GameStrategy;
import org.game.player.Player;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GameBuilder {
    private GameStrategy gameStrategy;
    private final List<Player> players = new ArrayList<>();

    public GameBuilder withGameStrategy(GameStrategy gameStrategy) {
        this.gameStrategy = gameStrategy;
        return this;
    }

    public GameBuilder withPlayer(Player player) {
        players.add(player);
        return this;
    }

    public Game build() {
        List<Player> qualifiedPlayers = List.copyOf(players);
        players.clear();
        return new Game(gameStrategy, qualifiedPlayers);
    }
}
