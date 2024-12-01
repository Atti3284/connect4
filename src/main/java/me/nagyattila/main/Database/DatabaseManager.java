
package me.nagyattila.main.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    public static final String DB_URL = "jdbc:sqlite:connect4.db";

    public DatabaseManager() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {
            String sql = """
                    CREATE TABLE IF NOT EXISTS players (
                        name TEXT PRIMARY KEY,
                        wins INTEGER DEFAULT 0
                    )
                    """;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Hiba az adatbázis inicializálásakor: " + e.getMessage());
        }
    }

    public void recordWin(String playerName) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO players (name, wins) VALUES (?, 1) ON CONFLICT(name) DO UPDATE SET wins = wins + 1")) {
            statement.setString(1, playerName);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Hiba a győzelem rögzítésekor: " + e.getMessage());
        }
    }

    public List<String> getHighScores() {
        List<String> scores = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name, wins FROM players ORDER BY wins DESC")) {
            while (resultSet.next()) {
                scores.add(resultSet.getString("name") + ": " + resultSet.getInt("wins") + " győzelem");
            }
        } catch (SQLException e) {
            System.out.println("Hiba a high score lekérdezésekor: " + e.getMessage());
        }
        return scores;
    }
}
