package org.game.logic.tiktok;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.game.logic.GameStrategy;
import org.game.player.Player;

import java.util.Optional;

@ApplicationScoped
@Named("tiktok")
public class TiktokStrategy implements GameStrategy {
    @Override
    public Optional<Player> determineWinner(Player... players) {
        // Will be implemented in future
        return Optional.empty();
    }
}
