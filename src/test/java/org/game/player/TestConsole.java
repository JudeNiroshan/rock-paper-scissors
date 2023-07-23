package org.game.player;

import org.game.controls.Choice;
import org.game.integration.impl.ConsoleConnector;

public class TestConsole extends ConsoleConnector {
    private int numberOfGames;
    private Choice choice;

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }

    @Override
    public int getNumberOfGames() {
        return this.numberOfGames;
    }

    @Override
    public Choice getChoice() {
        return this.choice;
    }
}
