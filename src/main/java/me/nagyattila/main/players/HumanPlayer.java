package me.nagyattila.main.players;

import me.nagyattila.main.model.Move;
import me.nagyattila.main.Board.BoardManager;
import java.util.Scanner;
public class HumanPlayer extends Player {
    public HumanPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public Move makeMove(BoardManager boardManager) {
        Scanner scanner = new Scanner(System.in);
        int column;
        while (true) {
            System.out.println("Add meg az oszlopot, ahová lépni szeretnél (0-6):");
            try {
                column = scanner.nextInt();
                if (column < 0 || column >= BoardManager.COLUMNS || !boardManager.getValidColumns().contains(column)) {
                    System.out.println("Érvénytelen oszlop! Próbáld újra.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Helytelen bemenet! Írj be egy számot.");
                scanner.next(); // Tisztítsuk meg a bemeneti puffert
            }
        }
        return new Move(column, getSymbol());
    }
}
