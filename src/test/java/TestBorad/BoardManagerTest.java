package TestBorad;

import Board.BoardManager;
import players.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardManagerTest {
    private BoardManager boardManager;
    private Player sárgaPlayer;
    private Player pirosPlayer;

    @BeforeEach
    public void setup() {
        boardManager = new BoardManager();
        sárgaPlayer = new Player("Sárga");
        pirosPlayer = new Player("Piros");
    }
/*
    @Test
    public void testLepes() {
        assertTrue(tabla.lepes("1", sárgaJatekos));
        assertEquals("o", tabla.getTabla()[5][0]);
    }
*/

    @Test
    void testLepes() {
        BoardManager boardManager = new BoardManager();
        Player player = new Player("Sárga");

        boolean sikeresLepes = boardManager.lepes("1", player);
        assertTrue(sikeresLepes, "A lépésnek sikeresnek kellett volna lennie.");
        assertEquals("O", boardManager.getTabla()[5][0]); // A jelnek az alsó sorba kell kerülnie
    }

    @Test
    public void testOszlopKorlatozas() {
        assertFalse(boardManager.lepes("8", sárgaPlayer));
        assertFalse(boardManager.lepes("-1", sárgaPlayer));
    }

    @Test
    public void testNyertes() {
        boardManager.lepes("1", sárgaPlayer);
        boardManager.lepes("2", sárgaPlayer);
        boardManager.lepes("3", sárgaPlayer);
        boardManager.lepes("4", sárgaPlayer);

        assertTrue(boardManager.nyeres(sárgaPlayer));
    }

    @Test
     public void testFuggolegesNyertes() {
        BoardManager boardManager = new BoardManager();
        Player player = new Player("Piros"); // Játékos, aki X-el játszik

        // Függőleges nyerő helyzet létrehozása az 1. oszlopban (index: 0)
        boardManager.lepes("1", player);
        boardManager.lepes("1", player);
        boardManager.lepes("1", player);
        boardManager.lepes("1", player);

        // Nyertes állapot ellenőrzése
        assertTrue(boardManager.nyeres(player), "A játékosnak nyernie kellett volna függőlegesen az 1. oszlopban.");
    }


    @Test
    void testAtlosNyertes() {
        BoardManager boardManager = new BoardManager();
        Player player = new Player("Piros"); // Játékos, aki X-el játszik

        // Átlós nyerő helyzet létrehozása
        boardManager.lepes("1", player); // Sor: 5, Oszlop: 0
        boardManager.lepes("2", player); // Sor: 4, Oszlop: 1
        boardManager.lepes("3", player); // Sor: 3, Oszlop: 2
        boardManager.lepes("4", player); // Sor: 2, Oszlop: 3

        // Nyertes állapot ellenőrzése
        assertTrue(boardManager.nyeres(player), "A játékosnak nyernie kellett volna átlósan.");
    }

    @Test
    public void testTeliOszlop() {
        boardManager.lepes("1", sárgaPlayer);
        boardManager.lepes("1", pirosPlayer);
        boardManager.lepes("1", sárgaPlayer);
        boardManager.lepes("1", pirosPlayer);
        boardManager.lepes("1", sárgaPlayer);
        boardManager.lepes("1", pirosPlayer);
        boardManager.lepes("1", sárgaPlayer);

        assertFalse(boardManager.lepes("1", pirosPlayer));
    }
}