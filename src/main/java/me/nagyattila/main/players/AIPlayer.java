package me.nagyattila.main.players;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.model.Move;

import java.util.List;
import java.util.Random;


public class AIPlayer extends Player {
    public AIPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public Move makeMove(BoardManager boardManager) {
        List<Integer> validColumns = boardManager.getValidColumns();
        if (validColumns.isEmpty()) {
            throw new IllegalStateException("Nincs érvényes oszlop!");
        }
        Random random = new Random();
        int column = validColumns.get(random.nextInt(validColumns.size()));
        System.out.println("AI lépés az oszlopba: " + column);
        return new Move(column, getSymbol());
    }
}