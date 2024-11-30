package PlayersTest;


import static org.mockito.Mockito.mock;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.model.Move;
import me.nagyattila.main.players.HumanPlayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HumanPlayerTest {

    private BoardManager mockBoardManager;
    private HumanPlayer humanPlayer;

    @BeforeEach
    void setUp() {
        mockBoardManager = mock(BoardManager.class); // Mockoljuk a BoardManager-t
        humanPlayer = new HumanPlayer('H'); // HumanPlayer 'H' szimbólummal
    }

    @Test
    void testValidMove() {
        // Mockoljuk a BoardManager getValidColums() metódust
        when(mockBoardManager.getValidColumns()).thenReturn(List.of(0, 1, 2, 3, 4, 5, 6)); // Az összes oszlop érvényes

        // Bemeneti adat szimulálása (oszlop: 3)
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Lépés
        Move move = humanPlayer.makeMove(mockBoardManager);

        // Ellenőrzések
        assertEquals(3, move.getColumn(), "A játékos nem a megfelelő oszlopot választotta.");
        assertEquals('H', move.getPlayer(), "A játékos szimbóluma helytelen.");
    }

    @Test
    void testInvalidInputHandled() {
        // Mockoljuk a BoardManager getValidColumns() metódust
        when(mockBoardManager.getValidColumns()).thenReturn(List.of(0, 1, 2, 3, 4, 5, 6)); // Az összes oszlop érvényes

        // Bemeneti adat szimulálása: érvénytelen (szöveg), majd érvényes (2)
        String input = "rosszBemenet\n2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Lépés
        Move move = humanPlayer.makeMove(mockBoardManager);

        // Ellenőrzések
        assertEquals(2, move.getColumn(), "A játékos nem a megfelelő oszlopot választotta érvénytelen bemenet után.");
        assertEquals('H', move.getPlayer(), "A játékos szimbóluma helytelen.");
    }

    @Test
    void testEdgeCaseColumnZero() {
        // Mockoljuk a BoardManager getValidColumns() metódust
        when(mockBoardManager.getValidColumns()).thenReturn(List.of(0, 1, 2, 3, 4, 5, 6)); // Az összes oszlop érvényes

        // Bemeneti adat szimulálása: oszlop 0
        String input = "0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Lépés
        Move move = humanPlayer.makeMove(mockBoardManager);

        // Ellenőrzések
        assertEquals(0, move.getColumn(), "A játékos nem a megfelelő oszlopot választotta (0).");
        assertEquals('H', move.getPlayer(), "A játékos szimbóluma helytelen.");
    }
}