/*
package me.nagyattila.main;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.Controller.GameController;
import me.nagyattila.main.players.AIPlayer;
import me.nagyattila.main.players.HumanPlayer;
import me.nagyattila.main.players.Player;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Válassz: 1 ha AI-al akarsz játszani, 2 ha Személyel akarsz játszani");
        int choice = scanner.nextInt();

        Player humanPlayer = new HumanPlayer('P');
        Player aiPlayer = new AIPlayer('A');

        GameController gameController;
        if (choice == 1) {
            gameController = new GameController(humanPlayer, aiPlayer);
        } else {
            gameController = new GameController(aiPlayer, humanPlayer);
        }

        gameController.startGame();
    }
}*/
/*
package me.nagyattila.main;

import me.nagyattila.main.Controller.GameController;
import me.nagyattila.main.players.AIPlayer;
import me.nagyattila.main.players.HumanPlayer;
import me.nagyattila.main.players.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Scanner létrehozása a felhasználói bemenet olvasásához
        Scanner scanner = new Scanner(System.in);

        // Bekérjük a nevet
        System.out.print("Kérlek, add meg a neved: ");
        String name = scanner.nextLine();

        // Kiírjuk a bekért nevet
        System.out.println("Üdvözöllek, " + name + "!");

        Player humanPlayer = new HumanPlayer('P');
        Player aiPlayer = new AIPlayer('A');

        GameController gameController = new GameController(humanPlayer, aiPlayer);
        gameController.startGame();
    }
}*/
// Második generált program ami XML-es

package me.nagyattila.main;

import me.nagyattila.main.Controller.GameController;
import me.nagyattila.main.players.AIPlayer;
import me.nagyattila.main.players.HumanPlayer;
import me.nagyattila.main.players.Player;

public class Main {
    public static void main(String[] args) {
        Player humanPlayer = new HumanPlayer('P');
        Player aiPlayer = new AIPlayer('A');

        GameController gameController = new GameController(humanPlayer, aiPlayer);
        gameController.startGame();
    }
}
