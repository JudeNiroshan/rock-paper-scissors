package org.game.player;

import jakarta.annotation.Nonnull;

public class PlayerFactory {
    public static Player createPlayer(@Nonnull PlayerType playerType) {
        return switch (playerType) {
            case HUMAN -> new HumanPlayer();
            case COMPUTER -> new ComputerPlayer();
            case NPC -> new NPCPlayer();
        };
    }
}
