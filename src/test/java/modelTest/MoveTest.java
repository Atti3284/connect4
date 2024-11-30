package modelTest;

import me.nagyattila.main.model.Move;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void testMoveConstructorAndGetters() {
        // Hozzuk létre a Move objektumot (oszlop: 2, játékos: 'X')
        Move move = new Move(2, 'X');

        // Ellenőrizzük, hogy a konstruktor helyesen állítja be az oszlopot és a játékost
        assertEquals(2, move.getColumn(), "A lépés oszlopa nem helyes.");
        assertEquals('X', move.getPlayer(), "A lépés játékos szimbóluma nem helyes.");
    }

    @Test
    void testMoveWithDifferentValues() {
        // Lépés egy másik oszloppal és játékkal
        Move move1 = new Move(4, 'O');
        Move move2 = new Move(6, 'X');

        // Ellenőrizzük az oszlopokat és a játékosokat
        assertEquals(4, move1.getColumn(), "A lépés oszlopa nem helyes.");
        assertEquals('O', move1.getPlayer(), "A lépés játékos szimbóluma nem helyes.");
        assertEquals(6, move2.getColumn(), "A lépés oszlopa nem helyes.");
        assertEquals('X', move2.getPlayer(), "A lépés játékos szimbóluma nem helyes.");
    }

    @Test
    void testMoveEquality() {
        // Két azonos Move objektum létrehozása
        Move move1 = new Move(3, 'X');
        Move move2 = new Move(3, 'X');

        // Ellenőrizzük, hogy az objektumok egyenlőek-e
        assertEquals(move1.getColumn(), move2.getColumn(), "Az oszlopok nem egyeznek.");
        assertEquals(move1.getPlayer(), move2.getPlayer(), "A játékosok nem egyeznek.");

        // Ellenőrizzük az egyenlőséget
        assertTrue(move1.getColumn() == move2.getColumn() && move1.getPlayer() == move2.getPlayer(),
                "A két Move objektum nem egyezik meg.");
    }
}
