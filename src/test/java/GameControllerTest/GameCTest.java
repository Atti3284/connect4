
package GameControllerTest;

import me.nagyattila.main.model.Move;
import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.Controller.GameController;
import me.nagyattila.main.players.Player;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;
public class GameCTest {
    @Test
    void testGameEndsWhenPlayerWins() {
        BoardManager mockBoardManager = mock(BoardManager.class);
        Player mockPlayer = mock(Player.class);

        // Szimuláljuk a bemenetet, mintha a játékos 0-ás oszlopot választott volna
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes())); // szimulált bemenet a Scanner-nek

        // Mockoljuk a játékmenet során szükséges metódusokat
        when(mockPlayer.makeMove(mockBoardManager)).thenReturn(new Move(0, 'P'));
        when(mockBoardManager.applyMove(any(Move.class))).thenReturn(true);
        when(mockBoardManager.checkWinCondition(any(Move.class))).thenReturn(true);

        // Indítjuk a játékot
        GameController gameController = new GameController(mockPlayer, mockPlayer);
        gameController.startGame();

        // Ellenőrizzük, hogy a win condition ellenőrzés megtörtént
        verify(mockBoardManager, atLeastOnce()).checkWinCondition(any(Move.class)); // Ellenőrizzük, hogy legalább egyszer meghívták
    }
    @Test
    void testGameEndsWhenPlayerLoses() {
        // Mockoljuk a BoardManager-t és a játékost
        BoardManager mockBoardManager = mock(BoardManager.class);
        Player mockPlayer = mock(Player.class);

        // Szimuláljuk a bemenetet, mintha a játékos 0-ás oszlopot választott volna
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Szimulált bemenet a Scanner-nek

        // Mockoljuk a játékmenet során szükséges metódusokat
        when(mockPlayer.makeMove(mockBoardManager)).thenReturn(new Move(0, 'P'));  // A játékos lépése
        when(mockBoardManager.applyMove(any(Move.class))).thenReturn(true);  // A lépés érvényes
        when(mockBoardManager.checkWinCondition(any(Move.class))).thenReturn(false);  // A győzelem feltételét nem teljesíti, tehát nem nyer

        // Indítjuk a játékot
        GameController gameController = new GameController(mockPlayer, mockPlayer);
        gameController.startGame();

        // Ellenőrizzük, hogy a win condition nem lett meghívva (ne győzzön)
        verify(mockBoardManager, atLeastOnce()).checkWinCondition(any(Move.class));  // Ellenőrizzük, hogy legalább egyszer meghívták
    }
}
