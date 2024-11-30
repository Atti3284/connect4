package TestRandomAi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import java.util.Random;
import Board.BoardManager;
import players.AIPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AIPlayerTest {

    @Test
    void testGepLepesKivalasztas() throws NoSuchFieldException, IllegalAccessException {
        // Mock-oljuk a Random osztályt
        Random realRandom = new Random();
        Random mockRandom = Mockito.spy(Random.class);

        // Állítsuk be, hogy a mock 5-öt adjon vissza
        //Mockito.when(mockRandom.nextInt(7)).thenReturn(5);
        Mockito.doReturn(5).when(mockRandom).nextInt(7);


        // Létrehozunk egy Gep példányt
        AIPlayer AIPlayer = new AIPlayer("Piros");

        // Reflection segítségével beállítjuk a mock Random-t
        Field veletlenField = players.AIPlayer.class.getDeclaredField("veletlen");
        veletlenField.setAccessible(true); // Tegyük hozzáférhetővé a privát mezőt
        veletlenField.set(AIPlayer, mockRandom);

        // Lépés végrehajtása
        BoardManager boardManager = new BoardManager();
        String oszlop = AIPlayer.lep(boardManager);

        // Az elvárt oszlop "6", mert a mock 5-öt adott vissza, és a Gep 1-től számoz
        assertEquals("6", oszlop);
    }
}
