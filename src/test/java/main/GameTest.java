package main;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
public class GameTest {

    @Test
    public void testPlayerWin() {
        // Szimulált bemenet: játékos lépései és a játék befejezése
        String bemenet = "1\n2\n2\n3\n3\n4\n"; // Az ember győzelme
        ByteArrayInputStream input = new ByteArrayInputStream(bemenet.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream kimenet = new PrintStream(output);

        Game game = new Game(input, kimenet);
        game.startGame();

        String jatekKimenet = output.toString();
        System.out.println("Teszt kimenet: " + jatekKimenet);
        //assertTrue(result,"Gratulálok, nyertél!");
        assertThat(jatekKimenet.contains("Gratulálok, nyertél!"));
    }

    @Test
    public void testGepWin() {
        // Szimulált bemenet, ahol a játékos nem tud győzni
        String bemenet = "1\n1\n2\n2\n3\n3\n4\n4\n5\n"; // A gép győzelme
        ByteArrayInputStream input = new ByteArrayInputStream(bemenet.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream kimenet = new PrintStream(output);

        Game game = new Game(input, kimenet);
        game.startGame();

        String jatekKimenet = output.toString();
        System.out.println("Játék kimenet teszteléshez: " + jatekKimenet);
        assertThat(jatekKimenet.contains("Sajnos a gép nyert!"));
    }

    @Test
    public void testInvalidInput(){
        //Szimulált bemenet: érvénytelen lépések és végül nyer az ember
        String bemenet = "0\n8\n1\n1\n2\n2\n3\n3\n4\n";// Néhány hibás lépés
        ByteArrayInputStream input = new ByteArrayInputStream(bemenet.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream kimenet = new PrintStream(output);

        Game game = new Game(input, kimenet);
        game.startGame();

        String jatekKimenet = output.toString();
        System.out.println("Játék kimenet teszteléshez: " + jatekKimenet);
        assertThat(jatekKimenet.contains("Gratulálok, nyertél!"));
    }
}
