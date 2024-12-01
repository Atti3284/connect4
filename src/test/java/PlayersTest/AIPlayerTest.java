
package PlayersTest;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.model.Move;
import me.nagyattila.main.players.AIPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AIPlayerTest {

    private AIPlayer aiPlayer;
    private BoardManager boardManager;

    @BeforeEach
    public void setUp() {
        aiPlayer = new AIPlayer('A');
        boardManager = Mockito.mock(BoardManager.class);  // Mockolja a BoardManager-t
    }

    @Test
    public void testAIPlayerInitialization() {
        assertEquals('A', aiPlayer.getSymbol(), "Az AI szimbóluma nem megfelelő.");
    }

    @Test
    public void testMakeMove() {
        // Mockolja a lehetséges érvényes oszlopokat
        List<Integer> validColumns = Arrays.asList(0, 2, 4);
        when(boardManager.getValidColumns()).thenReturn(validColumns);

        // Végrehajtja az AI lépést
        Move move = aiPlayer.makeMove(boardManager);

        // Ellenőrzi, hogy a lépés egy érvényes oszlopba történik
        assertTrue(validColumns.contains(move.getColumn()), "Az AI lépése nem egy érvényes oszlopba történt.");
    }

    @Test
    public void testMakeMoveWithEmptyColumns() {
        // Ha nincsenek érvényes oszlopok
        when(boardManager.getValidColumns()).thenReturn(Arrays.asList());

        // Meggyőződünk róla, hogy Exception-t dobunk
        assertThrows(IllegalStateException.class, () -> aiPlayer.makeMove(boardManager), "Nem dobott kivételt, amikor nincs érvényes oszlop.");
    }

    @Test
    public void testAIPlayerRandomMove() {
        // Mockolja a valid oszlopok listáját
        List<Integer> validColumns = Arrays.asList(0, 1, 3);
        when(boardManager.getValidColumns()).thenReturn(validColumns);

        // Teszteljük, hogy az AI véletlenszerűen választ
        Move move1 = aiPlayer.makeMove(boardManager);
        Move move2 = aiPlayer.makeMove(boardManager);

        // Ellenőrizzük, hogy két egymást követő lépés esetén is érvényes oszlopot választott
        assertTrue(validColumns.contains(move1.getColumn()), "Az első AI lépés nem érvényes oszlopba történt.");
        assertTrue(validColumns.contains(move2.getColumn()), "A második AI lépés nem érvényes oszlopba történt.");

        // Megjegyzés: a teszt nem várhatja, hogy move1 == move2, mivel az AI véletlenszerűen választ oszlopokat.
    }
}
