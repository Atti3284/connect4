package TestBorad;

import Board.Tabla;
import RandomAI.Gep;
import Player.Jatekos;
import Player.JatekosInterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TablaTest {
    private Tabla tabla;
    private Jatekos sárgaJatekos;
    private Jatekos pirosJatekos;

    @BeforeEach
    public void setup() {
        tabla = new Tabla();
        sárgaJatekos = new Jatekos("Sárga");
        pirosJatekos = new Jatekos("Piros");
    }
/*
    @Test
    public void testLepes() {
        assertTrue(tabla.lepes("1", sárgaJatekos));
        assertEquals("o", tabla.getTabla()[5][0]);
    }
*/

    @Test
    void testLepes() {
        Tabla tabla = new Tabla();
        Jatekos jatekos = new Jatekos("Sárga");

        boolean sikeresLepes = tabla.lepes("1", jatekos);
        assertTrue(sikeresLepes, "A lépésnek sikeresnek kellett volna lennie.");
        assertEquals("O", tabla.getTabla()[5][0]); // A jelnek az alsó sorba kell kerülnie
    }

    @Test
    public void testOszlopKorlatozas() {
        assertFalse(tabla.lepes("8", sárgaJatekos));
        assertFalse(tabla.lepes("-1", sárgaJatekos));
    }

    @Test
    public void testNyertes() {
        tabla.lepes("1", sárgaJatekos);
        tabla.lepes("2", sárgaJatekos);
        tabla.lepes("3", sárgaJatekos);
        tabla.lepes("4", sárgaJatekos);

        assertTrue(tabla.nyeres(sárgaJatekos));
    }
/*
    @Test
    public void testFuggolegesNyertes() {
        tabla.lepes("1", sárgaJatekos);
        tabla.lepes("1", pirosJatekos);
        tabla.lepes("1", sárgaJatekos);
        tabla.lepes("1", pirosJatekos);
        tabla.lepes("1", sárgaJatekos);
        tabla.lepes("1", pirosJatekos);
        assertTrue(tabla.nyeres(sárgaJatekos));
    }
*/
    @Test
     public void testFuggolegesNyertes() {
        Tabla tabla = new Tabla();
        Jatekos jatekos = new Jatekos("Piros"); // Játékos, aki X-el játszik

        // Függőleges nyerő helyzet létrehozása az 1. oszlopban (index: 0)
        tabla.lepes("1", jatekos);
        tabla.lepes("1", jatekos);
        tabla.lepes("1", jatekos);
        tabla.lepes("1", jatekos);

        // Nyertes állapot ellenőrzése
        assertTrue(tabla.nyeres(jatekos), "A játékosnak nyernie kellett volna függőlegesen az 1. oszlopban.");
    }

/*
    @Test
    public void testAtlosNyertes() {
        tabla.lepes("1", sárgaJatekos);
        tabla.lepes("2", pirosJatekos);
        tabla.lepes("2", sárgaJatekos);
        tabla.lepes("3", pirosJatekos);
        tabla.lepes("3", sárgaJatekos);
        tabla.lepes("4", pirosJatekos);
        assertTrue(tabla.nyeres(sárgaJatekos));
    }
*/
    @Test
    void testAtlosNyertes() {
        Tabla tabla = new Tabla();
        Jatekos jatekos = new Jatekos("Piros"); // Játékos, aki X-el játszik

        // Átlós nyerő helyzet létrehozása
        tabla.lepes("1", jatekos); // Sor: 5, Oszlop: 0
        tabla.lepes("2", jatekos); // Sor: 4, Oszlop: 1
        tabla.lepes("3", jatekos); // Sor: 3, Oszlop: 2
        tabla.lepes("4", jatekos); // Sor: 2, Oszlop: 3

        // Nyertes állapot ellenőrzése
        assertTrue(tabla.nyeres(jatekos), "A játékosnak nyernie kellett volna átlósan.");
    }

    @Test
    public void testTeliOszlop() {
        tabla.lepes("1", sárgaJatekos);
        tabla.lepes("1", pirosJatekos);
        tabla.lepes("1", sárgaJatekos);
        tabla.lepes("1", pirosJatekos);
        tabla.lepes("1", sárgaJatekos);
        tabla.lepes("1", pirosJatekos);
        tabla.lepes("1", sárgaJatekos);

        assertFalse(tabla.lepes("1", pirosJatekos));
    }
}