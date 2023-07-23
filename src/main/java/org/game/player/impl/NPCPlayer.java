package org.game.player.impl;

import org.game.controls.Choice;
import org.game.player.Player;
import org.game.player.PlayerType;

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

    @Override
    public void reset() {
        choice = null;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }
}
