package org.game.player;

import org.game.controls.Choice;

public class NPCPlayer implements Player {

    Choice choice;

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.NPC;
    }

    @Override
    public void engage() {

    }

    @Override
    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }
}
