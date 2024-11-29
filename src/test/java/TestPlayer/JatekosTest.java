package TestPlayer;


import Player.Jatekos;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class JatekosTest {

    @Test
    void testJatekosJel() {
        Jatekos jatekos = new Jatekos("Sárga");
        assertEquals("O", jatekos.getJel());
    }


    @Test
    public void testJatekosSzinSarga() {
        // Létrehozunk egy játékost a "Sárga" színnel
        Jatekos jatekos = new Jatekos("Sárga");
        // Ellenőrizzük, hogy a jel helyesen "O"-ra van állítva
        assertEquals("O", jatekos.getJel(), "A játékos jelének 'O'-nak kell lennie, ha a szín Sárga.");
    }

    @Test
    public void testJatekosSzinPiros() {
        // Létrehozunk egy játékost a "Piros" színnel
        Jatekos jatekos = new Jatekos("Piros");
        // Ellenőrizzük, hogy a jel helyesen "X"-re van állítva
        assertEquals("X", jatekos.getJel(), "A játékos jelének 'X'-nek kell lennie, ha a szín Piros.");
    }

    @Test
    public void testJatekosSzinIsmeretlen() {
        // Létrehozunk egy játékost ismeretlen színnel
        Jatekos jatekos = new Jatekos("Zöld");
        // Mivel nem Sárga, a jelnek "X"-nek kell lennie az alapértelmezett viselkedés miatt
        assertEquals("X", jatekos.getJel(), "A játékos jelének 'X'-nek kell lennie, ha a szín nem Sárga.");
    }
}
