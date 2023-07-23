package org.game.player;

import org.game.controls.Choice;
import org.game.integration.ConsoleConnector;
import org.game.integration.ExternalResourceConnector;

public class HumanPlayer implements Player {
    Choice choice;

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.HUMAN;
    }

    @Override
    public void engage() throws Exception {
        try (ExternalResourceConnector resource = new ConsoleConnector()) {
            choice = resource.getChoice();
        }
    }

    @Override
    public Choice getChoice() throws Exception {
        if (choice == null) {
            engage();
        }
        return choice;
    }
}
