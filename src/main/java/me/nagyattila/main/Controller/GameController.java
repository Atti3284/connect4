/*
package me.nagyattila.main.Controller;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.model.Move;
import me.nagyattila.main.players.Player;

import java.io.IOException;
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
}*/
/*
package me.nagyattila.main.Controller;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.model.Move;
import me.nagyattila.main.players.Player;

import java.io.IOException;
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


        System.out.println("Adja meg a játékállás betöltési fájlnevét (üres, ha nem szeretné betölteni):");
        String filePath = scanner.nextLine();

        if (!filePath.isEmpty()) {
            try {
                boardManager.loadBoardFromFile(filePath);
            } catch (IOException e) {
                System.out.println("Hiba a fájl betöltésekor: " + e.getMessage());
            }
        }

        boolean gameOver = false;
        boardManager.printBoard();

        while (!gameOver) {
            // Játékos lépése
            System.out.println("Te következel!");// (Ha azt írod 'vege' akkor vége a játéknak)");
            //String input = scanner.next();
            Move playerMove = player.makeMove(boardManager);



//            if (input.equalsIgnoreCase("vege")) {
//                System.out.println("Kilépés...");
//                break;
//            }

            if (!boardManager.applyMove(playerMove)) {
                System.out.println("Helytelen lépés.");
                continue;
            }

            boardManager.printBoard();
            if (boardManager.checkWinCondition(playerMove)) {
                System.out.println("Gratulálok! Nyertél!");
                gameOver = true;
                break;
            }

            // AI lépése
            Move aiMove = aiPlayer.makeMove(boardManager);
            boardManager.applyMove(aiMove);
            boardManager.printBoard();
            if (boardManager.checkWinCondition(aiMove)) {
                System.out.println("Sajnálom, az AI nyert.");
                gameOver = true;
            }
        }

        System.out.println("Adja meg a mentési fájlnevet (üres, ha nem szeretné menteni):");
        String saveFilePath = scanner.nextLine();
        if (!saveFilePath.isEmpty()) {
            try {
                boardManager.saveBoardToFile(saveFilePath);
            } catch (IOException e) {
                System.out.println("Hiba a mentés során: " + e.getMessage());
            }
        }
    }
}
*/

// Második változat, amely XML-es

package me.nagyattila.main.Controller;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.Manager.DatabaseManager;
import me.nagyattila.main.model.Move;
import me.nagyattila.main.players.Player;
import me.nagyattila.main.Manager.XMLManager;

import java.io.IOException;
import java.util.Scanner;

public class GameController {
    private final BoardManager boardManager;
    private final Player player;
    private final Player aiPlayer;
    private final XMLManager xmlManager;
    private final DatabaseManager databaseManager;
    private final Scanner scanner;

    public GameController(Player humanPlayer, Player aiPlayer) {
        this.boardManager = new BoardManager();
        this.player = humanPlayer;
        this.aiPlayer = aiPlayer;
        this.xmlManager = new XMLManager();
        this.databaseManager = new DatabaseManager();
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Adja meg az XML fájl nevét a játék betöltéséhez (üres, ha nem szeretné betölteni):");
        String filePath = scanner.nextLine();

        if (!filePath.isEmpty()) {
            Object[] gameState = xmlManager.loadFromXML(filePath);
            if (gameState != null) {
                boardManager.loadBoard((char[][]) gameState[0]);
                System.out.println("Játék betöltve.");
            }
        }

        boolean gameOver = false;
        boardManager.printBoard();

        while (!gameOver) {
            // Játékos lépése
            Move playerMove = player.makeMove(boardManager);
            if (!boardManager.applyMove(playerMove)) {
                System.out.println("Érvénytelen lépés.");
                continue;
            }

            boardManager.printBoard();
            if (boardManager.checkWinCondition(playerMove)) {
                System.out.println(player.getSymbol() + " győzött!");
                databaseManager.addWin(player.getSymbol() == 'P' ? "Human" : "AI");
                gameOver = true;
                break;
            }

            // AI lépése
            Move aiMove = aiPlayer.makeMove(boardManager);
            boardManager.applyMove(aiMove);
            boardManager.printBoard();
            if (boardManager.checkWinCondition(aiMove)) {
                System.out.println(aiPlayer.getSymbol() + " győzött!");
                databaseManager.addWin("AI");
                gameOver = true;
            }
        }

        System.out.println("Menteni szeretnéd az állást? Írj be egy fájlnevet (XML).");
        String saveFilePath = scanner.nextLine();
        if (!saveFilePath.isEmpty()) {
            xmlManager.saveToXML(saveFilePath, boardManager.getBoard(), String.valueOf(player.getSymbol()));
        }

        System.out.println("High Score Tábla:");
        databaseManager.printHighScores();
    }
}

