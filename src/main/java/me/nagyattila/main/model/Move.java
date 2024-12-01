/*
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
*/
/* //Chat GPT szerint ez VO osztály így
package me.nagyattila.main.model;
import java.util.Objects;

public class Move {
    private final int column;
    private final char player;

    public Move(int column, char player) {
        if (column < 0 || column >= 7) {
            throw new IllegalArgumentException("Invalid column index");
        }
        this.column = column;
        this.player = player;
    }

    public int getColumn() {
        return column;
    }

    public char getPlayer() {
        return player;
    }

    // Equals és hashCode felülírása a megfelelő egyenértékűség biztosításához
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

    // ToString felülírása a könnyebb olvashatóság érdekében
    @Override
    public String toString() {
        return "Move{column=" + column + ", player=" + player + '}';
    }
}*/

 //Újabbik generált osztály
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
