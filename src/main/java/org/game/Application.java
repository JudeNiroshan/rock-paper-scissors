package org.game;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.game.common.GameBuilder;
import org.game.controls.Choice;
import org.game.integration.ConsoleConnector;
import org.game.integration.ExternalResourceConnector;
import org.game.logic.rock_paper_scissors.RockPaperScissorStrategy;
import org.game.player.Player;
import org.game.player.PlayerFactory;
import org.game.player.PlayerType;
import org.game.util.GameLogger;

import java.util.Optional;

@QuarkusMain
public class Application implements QuarkusApplication {
    @Inject
    GameBuilder gameBuilder;

    @Override
    public int run(String... args) throws Exception {

        GameLogger.info("Welcome to Rock-Paper-Scissors!");
        gameBuilder.withGameStrategy(new RockPaperScissorStrategy());

        try (ExternalResourceConnector resource = new ConsoleConnector()) {
            int numberOfGames = resource.getNumberOfGames();
            int winCount = 0;

            for (int i = 0; i < numberOfGames; i++) {
                Player humanPlayer = PlayerFactory.createPlayer(PlayerType.HUMAN);
                Player computerPlayer = PlayerFactory.createPlayer(PlayerType.COMPUTER);

                Optional<Player> winner = gameBuilder.withPlayer(humanPlayer)
                        .withPlayer(computerPlayer)
                        .build().play();

                if (winner.isEmpty()) {
                    i--;
                    GameLogger.infof("Tie! Re-match again...");
                    continue;
                }
                displayResults(humanPlayer.getChoice(), computerPlayer.getChoice(), winner.get().getPlayerType());
                if (winner.get().getPlayerType() == PlayerType.HUMAN) {
                    winCount++;
                }
            }
            GameLogger.infof("You won [%d/%d] times!", winCount, numberOfGames);
        }
        return 0;
    }

    private void displayResults(Choice humanChoice, Choice computerChoice, PlayerType winner) {
        GameLogger.infof("""
                                                
                        You chose       : %s
                        Computer chose  : %s
                        ----------------------------
                        The winner is   : %s
                        ----------------------------""",
                humanChoice, computerChoice, winner);

    }
}
