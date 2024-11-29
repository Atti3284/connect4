/*
package TestRandomAi;

import Board.Tabla;
import Player.Jatekos;
import RandomAI.Gep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GepTest {
    private Tabla tabla;
    private Gep gep;
    private Random veletlenMock;

    @BeforeEach
    public void setup() {
        tabla = new Tabla(); // Tábla létrehozása
        veletlenMock = mock(Random.class); // Mockoljuk a Random objektumot
        gep = new Gep("Piros") {
            @Override
            public String lep(Tabla tabla) {
                // Override-oljuk a lépést, hogy a mockolt veletlen objektumot használja
                when(veletlenMock.nextInt(7)).thenReturn(3);  // Mindig a 4-es oszlopot választja (0 indexelés)
                return super.lep(tabla);  // Meghívjuk a Gep osztály eredeti metódusát
            }
        };
    }

    @Test
    void testGepLepes() {
        Tabla tabla = new Tabla();
        Gep gep = new Gep("Piros");

        // Szimuláljuk az első gépi lépést egy üres táblán
        String lepes = gep.lep(tabla);
        int oszlop = Integer.parseInt(lepes);

        // Ellenőrizzük, hogy az oszlop érvényes legyen (1-7 között)
        assertTrue(oszlop >= 1 && oszlop <= 7, "A gép lépése érvénytelen oszlop volt.");

        // Ellenőrizzük, hogy a lépés ténylegesen végrehajtható
        boolean sikeresLepes = tabla.lepes(lepes, gep);
        assertTrue(sikeresLepes, "A gép lépése sikertelen volt az adott oszlopban.");
    }


    @Test
    public void testGepLepesValiditas() {
        // A lépés valóban érvényes legyen
        String oszlop = gep.lep(tabla);
        assertTrue(oszlop.matches("[1-7]"));  // A gép mindig érvényes oszlopot választ (1-7)
    }

    @Test
    public void testGepJel() {
        // Ellenőrizzük, hogy a jel helyesen van beállítva
        assertEquals("X", gep.getJel());  // A gép "Piros" színű, tehát X a jele
    }

    @Test
    void testGepLepesKivalasztas() {
        // Mock-oljuk a Random osztályt
        Random mockRandom = Mockito.mock(Random.class);

        // Állítsuk be, hogy a mock 5-öt adjon vissza
        Mockito.when(mockRandom.nextInt(7)).thenReturn(5);

        // Létrehozunk egy Gep példányt a mock Random-nal
        Gep gep = new Gep("Piros");
        ReflectionTestUtils.setField(gep, "veletlen", mockRandom);

        Tabla tabla = new Tabla();
        String oszlop = gep.lep(tabla);

        // A mock alapján a 6. oszlopot kell választania (5 + 1 = 6)
        assertEquals("6", oszlop);
    }
}*/
package TestRandomAi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Random;
import Board.Tabla;
import Player.Jatekos;
import RandomAI.Gep;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GepTest {

    @Test
    void testGepLepesKivalasztas() throws NoSuchFieldException, IllegalAccessException {
        // Mock-oljuk a Random osztályt
        Random mockRandom = Mockito.mock(Random.class);

        // Állítsuk be, hogy a mock 5-öt adjon vissza
        Mockito.when(mockRandom.nextInt(7)).thenReturn(5);

        // Létrehozunk egy Gep példányt
        Gep gep = new Gep("Piros");

        // Reflection segítségével beállítjuk a mock Random-t
        Field veletlenField = Gep.class.getDeclaredField("veletlen");
        veletlenField.setAccessible(true); // Tegyük hozzáférhetővé a privát mezőt
        veletlenField.set(gep, mockRandom);

        // Lépés végrehajtása
        Tabla tabla = new Tabla();
        String oszlop = gep.lep(tabla);

        // Az elvárt oszlop "6", mert a mock 5-öt adott vissza, és a Gep 1-től számoz
        assertEquals("6", oszlop);
    }
}
