package me.nagyattila.main.model;

public class Move {
    private final int column;
    private final char player;

    public Move(int column, char player) {
        this.column = column;
        this.player = player;
    }

    public int getColumn() {
        return column;
    }

    public char getPlayer() {
        return player;
    }
}
