package players;

import Board.BoardManager;
import model.Move;

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