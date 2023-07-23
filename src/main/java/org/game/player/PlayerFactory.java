package org.game.player;

import jakarta.annotation.Nonnull;
import org.game.player.impl.ComputerPlayer;
import org.game.player.impl.HumanPlayer;
import org.game.player.impl.NPCPlayer;

public class PlayerFactory {
    public static Player createPlayer(@Nonnull PlayerType playerType) {
        return switch (playerType) {
            case HUMAN -> new HumanPlayer();
            case COMPUTER -> new ComputerPlayer();
            case NPC -> new NPCPlayer();
        };
    }
}
