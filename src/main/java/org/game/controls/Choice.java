package org.game.controls;

public enum Choice {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int value;

    Choice(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
