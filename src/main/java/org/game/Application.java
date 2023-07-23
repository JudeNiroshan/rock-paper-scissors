package org.game;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.game.common.Game;
import org.game.common.GameBuilder;
import org.game.integration.ConnectorProvider;
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

        try (ExternalResourceConnector resource = ConnectorProvider.getInstance().getConnector()) {
            Player humanPlayer = PlayerFactory.createPlayer(PlayerType.HUMAN);
            Player computerPlayer = PlayerFactory.createPlayer(PlayerType.COMPUTER);
            Game rpcGame = gameBuilder.withGameStrategy(new RockPaperScissorStrategy())
                    .withPlayer(humanPlayer)
                    .withPlayer(computerPlayer).build();
            int numberOfGames = resource.getNumberOfGames();
            int winCount = 0;


            for (int i = 0; i < numberOfGames; i++) {
                Optional<Player> winner = rpcGame.play();

                if (winner.isEmpty()) {
                    i--;
                    GameLogger.infof("Tie! Re-match again...");
                    reset(humanPlayer, computerPlayer);
                    continue;
                }
                displayResults(humanPlayer, computerPlayer, winner.get().getPlayerType());

                if (winner.get().getPlayerType() == PlayerType.HUMAN) {
                    winCount++;
                }
                reset(humanPlayer, computerPlayer);
            }
            GameLogger.infof("You won [%d/%d] times!", winCount, numberOfGames);
        }
        return 0;
    }

    private void displayResults(Player human, Player computer, PlayerType winner) throws Exception {
        GameLogger.infof("""
                                                
                        You chose       : %s
                        Computer chose  : %s
                        ----------------------------
                        The winner is   : %s
                        ----------------------------""",
                human.getChoice(), computer.getChoice(), winner);

    }

    private void reset(Player... players) {
        for (Player player : players) {
            player.reset();
        }
    }
}
