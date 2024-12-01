
package GameControllerTest;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.Controller.GameController;
import me.nagyattila.main.Database.DatabaseManager;
import me.nagyattila.main.model.Move;
import me.nagyattila.main.players.HumanPlayer;
import me.nagyattila.main.players.Player;
import me.nagyattila.main.players.AIPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class GameCTest {

    private BoardManager boardManager;
    private HumanPlayer humanPlayer;
    private AIPlayer aiPlayer;
    private DatabaseManager databaseManager;
    private GameController gameController;

    @BeforeEach
    void setUp() {
        boardManager = mock(BoardManager.class);
        humanPlayer = mock(HumanPlayer.class);
        aiPlayer = mock(AIPlayer.class);
        databaseManager = mock(DatabaseManager.class);

        // Az új konstruktor használata a mock objektumokkal
        gameController = new GameController(boardManager, humanPlayer, aiPlayer, databaseManager);
    }

    @Test
    void testGameFlow() {
        // Mockoljuk a bemeneti lépéseket
        when(humanPlayer.makeMove(boardManager)).thenReturn(new Move(0, 'P'));  // Az emberi játékos 0. oszlopba lép
        when(aiPlayer.makeMove(boardManager)).thenReturn(new Move(1, 'A'));     // Az AI 1. oszlopba lép

        // Mockoljuk a BoardManager viselkedését
        when(boardManager.applyMove(any(Move.class))).thenReturn(true);  // Az applyMove mindig sikeres
        when(boardManager.checkWinCondition(any(Move.class))).thenReturn(false);  // Nincs győzelem minden lépés után

        // Indítjuk a játékot
        gameController.start();  // A start metódus most biztosan befejeződik

        // Ellenőrizzük, hogy a GameController-ban az applyMove és a checkWinCondition hívások megtörténtek
        ArgumentCaptor<Move> moveCaptor = ArgumentCaptor.forClass(Move.class);
        verify(boardManager, times(4)).applyMove(moveCaptor.capture());
        verify(boardManager, times(4)).checkWinCondition(moveCaptor.capture());

        // Ellenőrizzük, hogy a játék nem döntött még győztesről
        verify(databaseManager, never()).recordWin(anyString());
    }
}