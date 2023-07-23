package org.game.logic.rock_paper_scissors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.game.controls.Choice;
import org.game.logic.GameStrategy;
import org.game.player.Player;

import java.util.Optional;

@ApplicationScoped
@Named("Rock-Paper-Scissors")
public class RockPaperScissorStrategy implements GameStrategy {
    @Override
    public Optional<Player> determineWinner(Player... players) throws Exception {
        if (players.length != 2) {
            throw new IllegalArgumentException("Only two players are allowed to play");
        }
        Player fp = players[0];
        Player sp = players[1];
        Choice fpChoice = fp.getChoice();
        Choice spChoice = sp.getChoice();

        if (fpChoice == spChoice) {
            return Optional.empty();
        }

        return switch (fpChoice) {
            case ROCK -> (spChoice == Choice.PAPER) ? Optional.of(sp) : Optional.of(fp);
            case PAPER -> (spChoice == Choice.ROCK) ? Optional.of(fp) : Optional.of(sp);
            case SCISSORS -> (spChoice == Choice.ROCK) ? Optional.of(sp) : Optional.of(fp);
        };
    }
}
