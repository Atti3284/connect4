package Controller;

import Board.BoardManager;
import model.Move;
import players.AIPlayer;
import players.Player;

import java.util.Scanner;

public class GameController {
    private final BoardManager boardManager;
    private final Player player;
    private final Player aiPlayer;
    private final Scanner scanner;
    public GameController(Player humanPlayer, Player aiPlayer) {
        this.boardManager = new BoardManager();
        this.player = humanPlayer;
        this.aiPlayer = aiPlayer;
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        boolean gameOver = false;
        boardManager.printBoard();

        while (!gameOver) {
            // Játékos lépése
            System.out.println("Te következel! Írj egy számot (0-6) vagy írd be, hogy 'vege'...");
            String input = scanner.next();

            if (input.equalsIgnoreCase("vege")) {
                System.out.println("Kilépés...");
                break;
            }

            int playerMove = Integer.parseInt(input);
            if (!boardManager.applyMove(new Move(playerMove, player.getSymbol()))) {
                System.out.println("Helytelen lépés.");
                continue;
            }

            boardManager.printBoard();
            if (boardManager.checkWinCondition(new Move(playerMove, player.getSymbol()))) {
                System.out.println("Gratulálok! Nyertél!");
                gameOver = true;
                break;
            }

            // AI lépése
            Move aiMove = aiPlayer.makeMove(boardManager);
            if (aiMove == null) {
                System.out.println("Döntetlen!");
                break;
            }

            boardManager.applyMove(aiMove);
            boardManager.printBoard();
            if (boardManager.checkWinCondition(aiMove)) {
                System.out.println("Sajnálom, az AI nyert.");
                gameOver = true;
            }
        }
    }
}
