
package me.nagyattila.main;

import me.nagyattila.main.Controller.GameController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Üdvözöllek a Connect 4 játékban!");
        System.out.print("Kérlek, add meg a neved: ");
        String name = scanner.nextLine();

        GameController gameController = new GameController(name);
        gameController.start();
    }
}