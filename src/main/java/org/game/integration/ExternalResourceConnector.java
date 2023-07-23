package org.game.integration;

import org.game.controls.Choice;

public interface ExternalResourceConnector extends AutoCloseable {
    int getNumberOfGames();

    Choice getChoice();
}
