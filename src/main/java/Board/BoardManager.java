/*package Board;

import java.util.Arrays;

public class BoardManager {
    private final int sorok = 6;
    private final int oszlopok = 7;
    private final String[][] tabla;

    public BoardManager() {
        this.tabla = new String[sorok][oszlopok];
        for (String[] sor : tabla) {
            Arrays.fill(sor, ".");
        }
    }

    // Getter a táblához, hogy a tesztekben hozzáférhessünk
    public String[][] getTabla() {
        return tabla;
    }

    public void megjelenit() {
        System.out.println("1 2 3 4 5 6 7");
        for (String[] sor : tabla) {
            System.out.println(String.join(" ", sor));
        }
    }

    public boolean lepes(String oszlop, JatekosInterface jatekos) {
        int oszlopIndex = oszlop.charAt(0) - '1';
        if (oszlopIndex < 0 || oszlopIndex >= oszlopok) return false;

        for (int i = sorok - 1; i >= 0; i--) {
            if (tabla[i][oszlopIndex].equals(".")) {
                tabla[i][oszlopIndex] = jatekos.getJel();
                return true;
            }
        }
        return false;
    }

    public boolean nyeres(JatekosInterface jatekos) {
        String jel = jatekos.getJel();
        return vizszintesEllenorzes(jel) || fuggolegesEllenorzes(jel) || atlosEllenorzes(jel);
    }

    private boolean vizszintesEllenorzes(String jel) {
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok - 3; j++) {
                if (ellenoriz(jel, i, j, 0, 1)) return true;
            }
        }
        return false;
    }

    private boolean fuggolegesEllenorzes(String jel) {
        for (int i = 0; i < sorok - 3; i++) {
            for (int j = 0; j < oszlopok; j++) {
                if (ellenoriz(jel, i, j, 1, 0)) return true;
            }
        }
        return false;
    }

    private boolean atlosEllenorzes(String jel) {
        for (int i = 0; i < sorok - 3; i++) {
            for (int j = 0; j < oszlopok - 3; j++) {
                if (ellenoriz(jel, i, j, 1, 1)) return true; // Jobbra le
            }
            for (int j = 3; j < oszlopok; j++) {
                if (ellenoriz(jel, i, j, 1, -1)) return true; // Balra le
            }
        }
        return false;
    }

    private boolean ellenoriz(String jel, int startSor, int startOszlop, int deltaSor, int deltaOszlop) {
        for (int k = 0; k < 4; k++) {
            if (!tabla[startSor + k * deltaSor][startOszlop + k * deltaOszlop].equals(jel)) {
                return false;
            }
        }
        return true;
    }
}*/
package Board;

import model.Move;

import java.util.ArrayList;
import java.util.List;

public class BoardManager {
    private static final int ROWS = 6;
    public static final int COLUMNS = 7;
    private final char[][] board;

    public BoardManager() {
        this.board = new char[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = '.';  // Kezdeti állapot
            }
        }
    }

    public boolean applyMove(Move move) {
        int col = move.getColumn();
        char player = move.getPlayer();
        if (col < 0 || col >= COLUMNS || board[0][col] != '.') {
            return false;  // Érvénytelen lépés
        }

        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == '.') {
                board[row][col] = player;
                return true;
            }
        }
        return false;
    }

    public boolean checkWinCondition(Move move) {
        int row = -1;
        int col = move.getColumn();
        char player = move.getPlayer();

        for (int r = 0; r < ROWS; r++) {
            if (board[r][col] == player) {
                row = r;
                break;
            }
        }

        return row != -1 && (checkDirection(row, col, player, 0, 1) || // Horizontálisan
                checkDirection(row, col, player, 1, 0) || // Vertikálisan
                checkDirection(row, col, player, 1, 1) || // Átlósan
                checkDirection(row, col, player, 1, -1));  // Átlósan
    }

    private boolean checkDirection(int row, int col, char player, int dRow, int dCol) {
        int count = 1;
        for (int i = 1; i < 4; i++) {
            int r = row + i * dRow;
            int c = col + i * dCol;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && board[r][c] == player) {
                count++;
            } else {
                break;
            }
        }

        for (int i = 1; i < 4; i++) {
            int r = row - i * dRow;
            int c = col - i * dCol;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && board[r][c] == player) {
                count++;
            } else {
                break;
            }
        }

        return count >= 4;
    }

    public List<Integer> getValidColumns() {
        List<Integer> validColumns = new ArrayList<>();
        for (int col = 0; col < COLUMNS; col++) {
            if (board[0][col] == '.') {
                validColumns.add(col);
            }
        }
        return validColumns;
    }

    public void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}