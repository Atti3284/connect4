package PlayersTest;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.model.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import me.nagyattila.main.players.AIPlayer;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AIPlayerTest {

    private BoardManager mockBoardManager;
    private AIPlayer aiPlayer;

    @BeforeEach
    void setUp() {
        mockBoardManager = mock(BoardManager.class); // Mockoljuk a BoardManager-t
        aiPlayer = new AIPlayer('A'); // AI játékos 'A' szimbólummal
    }

    @Test
    void testAISelectsValidColumn() {
        // Beállítunk valid oszlopokat
        List<Integer> validColumns = Arrays.asList(1, 3, 5);
        when(mockBoardManager.getValidColumns()).thenReturn(validColumns);

        // Az AI lépése
        Move move = aiPlayer.makeMove(mockBoardManager);

        // Ellenőrizzük, hogy a lépés oszlopa a valid oszlopok között van
        assertTrue(validColumns.contains(move.getColumn()), "AI érvénytelen oszlopot választott.");
        assertEquals('A', move.getPlayer(), "AI helytelen játékos szimbólumot használt.");
    }

    @Test
    void testAIRandomness() {
        // Beállítunk valid oszlopokat
        List<Integer> validColumns = Arrays.asList(0, 2, 4, 6);
        when(mockBoardManager.getValidColumns()).thenReturn(validColumns);

        // Többször meghívjuk az AI lépését, hogy ellenőrizzük a véletlenszerűséget
        boolean differentChoicesMade = false;
        int previousChoice = -1;

        for (int i = 0; i < 10; i++) {
            Move move = aiPlayer.makeMove(mockBoardManager);
            assertTrue(validColumns.contains(move.getColumn()), "AI érvénytelen oszlopot választott.");
            if (previousChoice != -1 && move.getColumn() != previousChoice) {
                differentChoicesMade = true; // Találtunk eltérő választást
            }
            previousChoice = move.getColumn();
        }

        assertTrue(differentChoicesMade, "AI nem választott különböző oszlopokat a próbák során.");
    }

    @Test
    void testAIThrowsExceptionWhenNoValidColumns() {
        // Nincs érvényes oszlop
        when(mockBoardManager.getValidColumns()).thenReturn(Arrays.asList());

        // Ellenőrizzük, hogy IllegalStateException-t dob
        Exception exception = assertThrows(IllegalStateException.class, () -> aiPlayer.makeMove(mockBoardManager));
        assertEquals("Nincs érvényes oszlop!", exception.getMessage());
    }
}
