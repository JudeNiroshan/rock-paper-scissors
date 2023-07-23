package org.game.player.impl;

import org.game.controls.Choice;
import org.game.integration.ConnectorProvider;
import org.game.integration.ExternalResourceConnector;
import org.game.player.Player;
import org.game.player.PlayerType;

public class HumanPlayer implements Player {
    Choice choice;

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.HUMAN;
    }

    @Override
    public void engage() throws Exception {
        ExternalResourceConnector resource = ConnectorProvider.getInstance().getConnector();
        choice = resource.getChoice();
    }

    @Override
    public Choice getChoice() throws Exception {
        if (choice == null) {
            engage();
        }
        return choice;
    }

    @Override
    public void reset() {
        choice = null;
    }
}
