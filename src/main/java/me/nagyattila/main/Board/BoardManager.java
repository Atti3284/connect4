package me.nagyattila.main.Board;

import me.nagyattila.main.model.Move;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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