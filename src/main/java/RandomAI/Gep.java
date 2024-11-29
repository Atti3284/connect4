package RandomAI;

import Board.Tabla;
import Player.JatekosInterface;

import java.util.Random;


public class Gep implements JatekosInterface {
    private final String jel;
    private final Random veletlen;

    public Gep(String szin) {
        this.jel = szin.equals("Piros") ? "X" : "O";
        this.veletlen = new Random();
    }

    public String getJel() {
        return jel;
    }

    public String lep(Tabla tabla) {
        String[][] currentBoard = tabla.getTabla();
        while (true) {
            int oszlopIndex = veletlen.nextInt(7); // 0-6
            for (int i = currentBoard.length - 1; i >= 0; i--) {
                if (currentBoard[i][oszlopIndex].equals(".")) {
                    return String.valueOf(oszlopIndex + 1); // 1-indexelt
                }
            }
        }
    }

}
