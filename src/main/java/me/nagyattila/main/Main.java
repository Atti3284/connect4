/*
package com.example;

import Board.Tabla;
import RandomAI.Gep;
import Players.Jatekos;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner beolvas = new Scanner(System.in);
        Tabla tabla = new Tabla();
        Jatekos ember = new Jatekos("Sárga");
        Gep gep = new Gep("Piros");

        boolean jatekVege = false;
        System.out.println("Üdv az Amőba4 játékban!");
        tabla.megjelenit();

        while (!jatekVege) {
            // Ember lép
            System.out.println("Te jössz, válassz egy oszlopot (pl. '1', '2', ... ,'7'):");
            String oszlop = beolvas.nextLine();
            while (!tabla.lepes(oszlop, ember)) {
                System.out.println("Nem érvényes lépés, próbáld újra:");
                oszlop = beolvas.nextLine();
            }
            tabla.megjelenit();
            if (tabla.nyeres(ember)) {
                System.out.println("Gratulálok, nyertél!");
                jatekVege = true;
                continue;
            }

            // Gép lép.
            System.out.println("A gép lép...");
            String gepLepes = gep.lep(tabla);
            tabla.lepes(gepLepes, gep);
            tabla.megjelenit();
            if (tabla.nyeres(gep)) {
                System.out.println("Sajnos a gép nyert!");
                jatekVege = true;
            }
        }
        beolvas.close();
    }
}
*/
/*
package com.example;

import Board.Tabla;
import RandomAI.Gep;
import Players.Jatekos;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Game {

    private final Tabla tabla;
    private final Jatekos ember;
    private final Gep gep;
    private final Scanner beolvas;
    private final PrintStream kimenet;

    public Game(InputStream bemenet, PrintStream kimenet) {
        this.tabla = new Tabla();
        this.ember = new Jatekos("Sárga");
        this.gep = new Gep("Piros");
        this.beolvas = new Scanner(bemenet);
        this.kimenet = kimenet;
    }

    public void startGame() {
        boolean jatekVege = false;
        kimenet.println("Üdv az Amőba4 játékban!");
        tabla.megjelenit();


        while (!jatekVege) {



            // Ember lép
            kimenet.println("Te jössz, válassz egy oszlopot (pl. '1', '2', ... ,'7'):");
            String oszlop = beolvas.nextLine();
            while (!tabla.lepes(oszlop, ember)) {
                kimenet.println("Nem érvényes lépés, próbáld újra:");
                oszlop = beolvas.nextLine();
            }


            tabla.megjelenit();
            if (tabla.nyeres(ember)) {
                kimenet.println("Gratulálok, nyertél!");
                jatekVege = true;
                continue;
            }

            // Gép lép
            kimenet.println("A gép lép...");
            String gepLepes = gep.lep(tabla);
            tabla.lepes(gepLepes, gep);
            tabla.megjelenit();
            if (tabla.nyeres(gep)) {
                kimenet.println("Sajnos a gép nyert!");
                jatekVege = true;
            }
        }
        beolvas.close();
    }
}*/
package me.nagyattila.main;

import me.nagyattila.main.Controller.GameController;
import me.nagyattila.main.players.AIPlayer;
import me.nagyattila.main.players.HumanPlayer;
import me.nagyattila.main.players.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Válassz: 1 a játékosnak, 2 az AI-nek");
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

}