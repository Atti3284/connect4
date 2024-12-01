
package me.nagyattila.main.model;

import java.util.Objects;

public final class Move {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return column == move.column && player == move.player;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, player);
    }

    @Override
    public String toString() {
        return "Move{" +
                "column=" + column +
                ", player=" + player +
                '}';
    }
}
