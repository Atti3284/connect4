/*
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
}*/
package TestBorad;

import me.nagyattila.main.Board.BoardManager;
import me.nagyattila.main.model.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardManagerTest {
    private BoardManager boardManager;

    @BeforeEach
    void setUp() {
        boardManager = new BoardManager();
    }

    @Test
    void testApplyMove_ValidMove() {
        Move move = new Move(0, 'P'); // P játékos lép
        assertTrue(boardManager.applyMove(move)); // A lépésnek sikeresnek kell lennie
    }
//    @Test
//    void testApplyMove_validMove() {
//        BoardManager boardManager = new BoardManager();
//        Move move = new Move(3, 'P');
//        assertTrue(boardManager.applyMove(move));
//    }
    @Test
    void testApplyMove_invalidMove() {
        BoardManager boardManager = new BoardManager();
        Move move = new Move(-1, 'P');
        assertFalse(boardManager.applyMove(move));
    }

    @Test
    void testApplyMove_ColumnFull() {
        for (int i = 0; i < 6; i++) {
            boardManager.applyMove(new Move(0, 'P'));
        }
        Move move = new Move(0, 'A'); // A játékos próbál lépni
        assertFalse(boardManager.applyMove(move)); // A lépésnek érvénytelennek kell lennie
    }

    @Test
    void testCheckWinCondition_HorizontalWin() {
        for (int i = 0; i < 4; i++) {
            boardManager.applyMove(new Move(i, 'P'));
        }
        Move move = new Move(3, 'P');
        assertTrue(boardManager.checkWinCondition(move)); // A lépés után nyerés történik
    }

    @Test
    void testCheckWinCondition_VerticalWin() {
        for (int i = 0; i < 4; i++) {
            boardManager.applyMove(new Move(0, 'P'));
        }
        Move move = new Move(0, 'P');
        assertTrue(boardManager.checkWinCondition(move)); // A lépés után nyerés történik
    }

    @Test
    void testCheckWinCondition_DiagonalWin() {
        // Feltételezve, hogy a bal alsó sarokból kezdődnek a lépések
        boardManager.applyMove(new Move(0, 'P')); // Sor 5, oszlop 0
        boardManager.applyMove(new Move(1, 'P')); // Sor 5, oszlop 1
        boardManager.applyMove(new Move(1, 'P')); // Sor 4, oszlop 1
        boardManager.applyMove(new Move(2, 'P')); // Sor 4, oszlop 2
        boardManager.applyMove(new Move(2, 'P')); // Sor 3, oszlop 2
        boardManager.applyMove(new Move(3, 'P')); // Sor 3, oszlop 3

        // Most, amikor a 'P' játékos a (3,3) oszlopba lép, az egy diagonális nyereményt jelent.
        Move move = new Move(3, 'P');
        assertTrue(boardManager.checkWinCondition(move)); // A lépés után nyerés történik
    }

    @Test
    void testGetValidColumns() {
        for (int i = 0; i < 6; i++) {
            boardManager.applyMove(new Move(0, 'P'));
        }
        assertFalse(boardManager.getValidColumns().contains(0)); // Az oszlop nem érvényes
        assertTrue(boardManager.getValidColumns().contains(1)); // Az oszlop még érvényes
    }
}
