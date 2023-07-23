package org.game.player;

import org.game.controls.Choice;

import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer implements Player {

    Choice choice;

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.COMPUTER;
    }

    @Override
    public void engage() {
        int randomNumber = ThreadLocalRandom.current().nextInt(Choice.values().length);
        choice = Choice.values()[randomNumber];
    }

    @Override
    public Choice getChoice() {
        if (choice == null) {
            engage();
        }
        return choice;
    }
}
