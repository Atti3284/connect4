
package me.nagyattila.main.Manager;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:connect4.db";

    public DatabaseManager() {
        createTables();
    }

    private void createTables() {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String createPlayersTable = """
                CREATE TABLE IF NOT EXISTS Players (
                    name TEXT PRIMARY KEY,
                    wins INTEGER DEFAULT 0
                );
            """;
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(createPlayersTable);
            }
        } catch (SQLException e) {
            System.err.println("Hiba az adatbázis inicializálása során: " + e.getMessage());
        }
    }

    public void addWin(String playerName) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String query = """
                INSERT INTO Players (name, wins)
                VALUES (?, 1)
                ON CONFLICT(name) DO UPDATE SET wins = wins + 1;
            """;
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, playerName);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Hiba a győzelem frissítésekor: " + e.getMessage());
        }
    }

    public void printHighScores() {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String query = "SELECT name, wins FROM Players ORDER BY wins DESC;";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                System.out.println("High Score Tábla:");
                while (rs.next()) {
                    System.out.printf("%s: %d győzelem\n", rs.getString("name"), rs.getInt("wins"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Hiba a high score táblázat megjelenítésekor: " + e.getMessage());
        }
    }
}
