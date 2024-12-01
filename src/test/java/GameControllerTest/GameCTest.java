/*
package GameControllerTest;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.Controller.GameController;
import me.nagyattila.main.Database.DatabaseManager;
import me.nagyattila.main.model.Move;
import me.nagyattila.main.players.HumanPlayer;
import me.nagyattila.main.players.AIPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest extends AbstractGameControllerTest {

    @Override
    protected HumanPlayer createHumanPlayer(String name) {
        return mock(HumanPlayer.class);
    }

    @Override
    protected AIPlayer createAIPlayer() {
        return mock(AIPlayer.class);
    }

    @Test
    void testGameStart() {
        // Szimuláld a játékot és tedd meg a szükséges ellenőrzéseket
        when(mockHumanPlayer.makeMove(mockBoardManager)).thenReturn(new Move(3, 'P'));
        when(mockAIPlayer.makeMove(mockBoardManager)).thenReturn(new Move(4, 'A'));

        gameController.start();  // A játék elindul

        // Ellenőrizd a játék kimenetét, vagy hogy történt-e valami a mock objektumokkal
        verify(mockBoardManager, times(2)).applyMove(any(Move.class));
        // További ellenőrzések a játék működésére vonatkozóan
    }
}
*/