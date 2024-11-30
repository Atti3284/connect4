package TestPlayer;


import players.Player;


import org.junit.jupiter.api.Test;


public class PlayerTest {

    @Test
    void testJatekosJel() {
        Player player = new Player("Sárga");
        assertEquals("O", player.getJel());
    }


    @Test
    public void testJatekosSzinSarga() {
        // Létrehozunk egy játékost a "Sárga" színnel
        Player player = new Player("Sárga");
        // Ellenőrizzük, hogy a jel helyesen "O"-ra van állítva
        assertEquals("O", player.getJel(), "A játékos jelének 'O'-nak kell lennie, ha a szín Sárga.");
    }

    @Test
    public void testJatekosSzinPiros() {
        // Létrehozunk egy játékost a "Piros" színnel
        Player player = new Player("Piros");
        // Ellenőrizzük, hogy a jel helyesen "X"-re van állítva
        assertEquals("X", player.getJel(), "A játékos jelének 'X'-nek kell lennie, ha a szín Piros.");
    }

    @Test
    public void testJatekosSzinIsmeretlen() {
        // Létrehozunk egy játékost ismeretlen színnel
        Player player = new Player("Zöld");
        // Mivel nem Sárga, a jelnek "X"-nek kell lennie az alapértelmezett viselkedés miatt
        assertEquals("X", player.getJel(), "A játékos jelének 'X'-nek kell lennie, ha a szín nem Sárga.");
    }
}
