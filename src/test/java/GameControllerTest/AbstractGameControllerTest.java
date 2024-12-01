/*
package GameControllerTest;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.Controller.GameController;
import me.nagyattila.main.players.AIPlayer;
import me.nagyattila.main.players.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public abstract class AbstractGameControllerTest {
    protected BoardManager mockBoardManager;
    protected GameController gameController;
    protected HumanPlayer mockHumanPlayer;
    protected AIPlayer mockAIPlayer;

    @BeforeEach
    void setUp() {
        mockBoardManager = mock(BoardManager.class);
        mockHumanPlayer = createHumanPlayer("Player1");
        mockAIPlayer = createAIPlayer();
        gameController = new GameController("Player1");
    }

    protected abstract HumanPlayer createHumanPlayer(String name);

    protected abstract AIPlayer createAIPlayer();
}
*/