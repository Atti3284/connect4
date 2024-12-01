/*
package me.nagyattila.main.players;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.model.Move;


public abstract class Player {
    private final char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract Move makeMove(BoardManager boardManager);
}*/

package me.nagyattila.main.players;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.model.Move;

public abstract class Player {
    private final char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract Move makeMove(BoardManager boardManager);
}

