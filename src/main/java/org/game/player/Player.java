package org.game.player;

import org.game.controls.Choice;

import java.util.Arrays;

public interface Player {
    int minPossibleChoice = Arrays.stream(Choice.values()).mapToInt(Choice::getValue).min().orElse(0);
    int maxPossibleChoice = Arrays.stream(Choice.values()).mapToInt(Choice::getValue).max().orElse(0);
    PlayerType getPlayerType();
    void engage() throws Exception;
    Choice getChoice() throws Exception;
}
