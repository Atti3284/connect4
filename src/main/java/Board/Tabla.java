package Board;

import Player.Jatekos;
import Player.JatekosInterface;

import java.util.Arrays;

public class Tabla {
    private final int sorok = 6;
    private final int oszlopok = 7;
    private final String[][] tabla;

    public Tabla() {
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
}