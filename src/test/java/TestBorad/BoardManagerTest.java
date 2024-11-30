package TestBorad;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.model.Move;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardManagerTest {
    @Test
    void testApplyMove_validMove() {
        BoardManager boardManager = new BoardManager();
        Move move = new Move(3, 'P');
        assertTrue(boardManager.applyMove(move));
    }

    @Test
    void testApplyMove_invalidMove() {
        BoardManager boardManager = new BoardManager();
        Move move = new Move(-1, 'P');
        assertFalse(boardManager.applyMove(move));
    }

    @Test
    void testCheckWinCondition_horizontalWin() {
        BoardManager boardManager = new BoardManager();
        for (int i = 0; i < 4; i++) {
            boardManager.applyMove(new Move(i, 'P'));
        }
        assertTrue(boardManager.checkWinCondition(new Move(3, 'P')));
    }
}