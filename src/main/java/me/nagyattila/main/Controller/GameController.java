
package me.nagyattila.main.Controller;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.Database.DatabaseManager;
import me.nagyattila.main.model.Move;
import me.nagyattila.main.players.*;

public class GameController {
    private final BoardManager boardManager;
    private final Player player;
    private final Player aiPlayer;
    private final DatabaseManager databaseManager;

    public GameController(String playerName) {
        this.boardManager = new BoardManager();
        this.player = new HumanPlayer(playerName, 'P');
        this.aiPlayer = new AIPlayer('A');
        this.databaseManager = new DatabaseManager();
    }
    // Új konstruktor, amely lehetővé teszi a mock objektumok átadását
    public GameController(BoardManager boardManager, Player player, Player aiPlayer, DatabaseManager databaseManager) {
        this.boardManager = boardManager;
        this.player = player;
        this.aiPlayer = aiPlayer;
        this.databaseManager = databaseManager;
    }

    public void start() {
        boolean gameOver = false;
        while (!gameOver) {
            boardManager.printBoard();
            Move playerMove = player.makeMove(boardManager);
            if (boardManager.applyMove(playerMove) && boardManager.checkWinCondition(playerMove)) {
                System.out.println("Gratulálok, nyertél!");
                databaseManager.recordWin(((HumanPlayer) player).getName());
                break;
            }

            Move aiMove = aiPlayer.makeMove(boardManager);
            if (boardManager.applyMove(aiMove) && boardManager.checkWinCondition(aiMove)) {
                System.out.println("Sajnálom, az AI nyert!");
                databaseManager.recordWin("AI");
                break;
            }
        }

        boardManager.printBoard();
        System.out.println("High Score táblázat:");
        for (String score : databaseManager.getHighScores()) {
            System.out.println(score);
        }
    }
}
