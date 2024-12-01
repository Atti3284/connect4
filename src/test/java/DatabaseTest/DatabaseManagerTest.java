package DatabaseTest;

import me.nagyattila.main.Database.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseManagerTest {

    private DatabaseManager databaseManager;

    // Tesztelő adatbázis előkészítése
    @BeforeEach
    void setUp() {
        // Inicializáljuk a DatabaseManager objektumot
        databaseManager = new DatabaseManager();

        // Töröljük az adatokat a players táblából minden teszt előtt
        try (Connection connection = DriverManager.getConnection(DatabaseManager.DB_URL);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM players");  // Töröljük a táblát
        } catch (SQLException e) {
            System.out.println("Hiba az adatbázis törlésénél: " + e.getMessage());
        }
    }

    @Test
    void testRecordWin_ExistingPlayer() {
        // Péter győzelmének rögzítése kétszer
        databaseManager.recordWin("Péter");
        databaseManager.recordWin("Péter");

        // Ellenőrizzük, hogy a high scores listában Péter neve szerepel a megfelelő győzelmek számával
        List<String> highScores = databaseManager.getHighScores();
        System.out.println("High Scores: " + highScores);  // Debugging output

        // Ellenőrizzük, hogy Péter neve és 2 győzelme szerepel-e a high scores listában
        assertTrue(highScores.contains("Péter: 2 győzelem"));
    }

    @Test
    void testRecordWin_NewPlayer() {
        // Új játékos győzelmének rögzítése
        databaseManager.recordWin("János");

        // Ellenőrizzük, hogy a high scores listában János neve szerepel
        List<String> highScores = databaseManager.getHighScores();
        System.out.println("High Scores: " + highScores);  // Debugging output

        // Ellenőrizzük, hogy János neve és 1 győzelme szerepel-e a high scores listában
        assertTrue(highScores.contains("János: 1 győzelem"));
    }

    @Test
    void testHighScoresOrder() {
        // Több játékos győzelmeinek rögzítése
        databaseManager.recordWin("Péter");
        databaseManager.recordWin("János");
        databaseManager.recordWin("János");

        // Ellenőrizzük, hogy a játékosok győzelmeik száma alapján csökkenő sorrendben szerepelnek
        List<String> highScores = databaseManager.getHighScores();
        System.out.println("High Scores: " + highScores);  // Debugging output

        // Ellenőrizzük, hogy János előtt szerepel Péter, mert ő több győzelemmel rendelkezik
        assertEquals("János: 2 győzelem", highScores.get(0));
        assertEquals("Péter: 1 győzelem", highScores.get(1));
    }

    @Test
    void testRecordWin_EmptyDatabase() {
        // Ellenőrizzük, hogy az adatbázis üres, mielőtt bármilyen győzelmet rögzítenénk
        List<String> highScores = databaseManager.getHighScores();
        assertTrue(highScores.isEmpty(), "Az adatbázisnak üresnek kell lennie.");

        // Új játékos győzelmének rögzítése
        databaseManager.recordWin("Zoltán");

        // Ellenőrizzük, hogy Zoltán győzelme megjelent-e az adatbázisban
        highScores = databaseManager.getHighScores();
        assertFalse(highScores.isEmpty(), "Az adatbázisnak tartalmaznia kell legalább egy rekordot.");
        assertTrue(highScores.contains("Zoltán: 1 győzelem"));
    }
}
