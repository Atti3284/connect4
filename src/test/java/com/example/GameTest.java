/*
package com.example;

import Board.Tabla;
import RandomAI.Gep;
import Player.Jatekos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setup() {
        game = new Game(6, 7);  // Tábla méret például 6x7
    }

    @Test
    public void testJatekIndul() {
        assertFalse(game.isGameOver());  // Kezdetben nem lehet vége a játéknak
    }

    @Test
    public void testGameVege() {
        // Futtass egy példát, amelynek végén véget ér a játék
        game.makeMove("a");
        game.makeMove("b");
        // Hozz létre egy nyerő állapotot, például vízszintes
        game.makeMove("c");
        game.makeMove("d");
        assertTrue(game.isGameOver());  // Ha nyert a játékos
    }
}
*/

package com.example;

import Board.Tabla;
import RandomAI.Gep;
import Player.Jatekos;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    @Test
    public void testPlayerWin() {
        // Szimulált bemenet: játékos lépései és a játék befejezése
        String bemenet = "1\n2\n3\n4\n";
        ByteArrayInputStream input = new ByteArrayInputStream(bemenet.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream kimenet = new PrintStream(output);

        Game game = new Game(input, kimenet);
        game.startGame();

        String jatekKimenet = output.toString();
        assertTrue(jatekKimenet.contains("Gratulálok, nyertél!"),
                "A játékosnak győznie kellett volna!");
    }

    @Test
    public void testGepWin() {
        // Szimulált bemenet, ahol a játékos nem tud győzni
        String bemenet = "1\n1\n2\n2\n3\n3\n5\n";
        ByteArrayInputStream input = new ByteArrayInputStream(bemenet.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream kimenet = new PrintStream(output);

        Game game = new Game(input, kimenet);
        game.startGame();

        String jatekKimenet = output.toString();
        assertTrue(jatekKimenet.contains("Sajnos a gép nyert!"),
                "A gépnek győznie kellett volna!");
    }
}
