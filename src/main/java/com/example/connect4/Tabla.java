package com.example.connect4;

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

    public void megjelenit() {
        System.out.println("1 2 3 4 5 6 7");
        for (String[] sor : tabla) {
            for (String mezo : sor) {
                System.out.print(mezo + " ");
            }
            System.out.println();
        }
    }

    public boolean lepes(String oszlop, Gep jatekos) {
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
    public boolean lepes(String oszlop, Jatekos jatekos) {
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

    public boolean nyeres(Gep jatekos) {
        String jel = jatekos.getJel();

        // Vízszintes ellenőrzés
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok - 3; j++) {
                if (tabla[i][j].equals(jel) && tabla[i][j + 1].equals(jel) && tabla[i][j + 2].equals(jel) && tabla[i][j + 3].equals(jel)) {
                    return true;
                }
            }
        }

        // Függőleges ellenőrzés
        for (int i = 0; i < sorok - 3; i++) {
            for (int j = 0; j < oszlopok; j++) {
                if (tabla[i][j].equals(jel) && tabla[i + 1][j].equals(jel) && tabla[i + 2][j].equals(jel) && tabla[i + 3][j].equals(jel)) {
                    return true;
                }
            }
        }

        // Átlós ellenőrzés (jobbra le)
        for (int i = 0; i < sorok - 3; i++) {
            for (int j = 0; j < oszlopok - 3; j++) {
                if (tabla[i][j].equals(jel) && tabla[i + 1][j + 1].equals(jel) && tabla[i + 2][j + 2].equals(jel) && tabla[i + 3][j + 3].equals(jel)) {
                    return true;
                }
            }
        }

        // Átlós ellenőrzés (balra le)
        for (int i = 0; i < sorok - 3; i++) {
            for (int j = 3; j < oszlopok; j++) {
                if (tabla[i][j].equals(jel) && tabla[i + 1][j - 1].equals(jel) && tabla[i + 2][j - 2].equals(jel) && tabla[i + 3][j - 3].equals(jel)) {
                    return true;
                }
            }
        }

        return false;
    }
    public boolean nyeres(Jatekos jatekos) {
        String jel = jatekos.getJel();

        // Vízszintes ellenőrzés
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok - 3; j++) {
                if (tabla[i][j].equals(jel) && tabla[i][j + 1].equals(jel) && tabla[i][j + 2].equals(jel) && tabla[i][j + 3].equals(jel)) {
                    return true;
                }
            }
        }

        // Függőleges ellenőrzés
        for (int i = 0; i < sorok - 3; i++) {
            for (int j = 0; j < oszlopok; j++) {
                if (tabla[i][j].equals(jel) && tabla[i + 1][j].equals(jel) && tabla[i + 2][j].equals(jel) && tabla[i + 3][j].equals(jel)) {
                    return true;
                }
            }
        }

        // Átlós ellenőrzés (jobbra le)
        for (int i = 0; i < sorok - 3; i++) {
            for (int j = 0; j < oszlopok - 3; j++) {
                if (tabla[i][j].equals(jel) && tabla[i + 1][j + 1].equals(jel) && tabla[i + 2][j + 2].equals(jel) && tabla[i + 3][j + 3].equals(jel)) {
                    return true;
                }
            }
        }

        // Átlós ellenőrzés (balra le)
        for (int i = 0; i < sorok - 3; i++) {
            for (int j = 3; j < oszlopok; j++) {
                if (tabla[i][j].equals(jel) && tabla[i + 1][j - 1].equals(jel) && tabla[i + 2][j - 2].equals(jel) && tabla[i + 3][j - 3].equals(jel)) {
                    return true;
                }
            }
        }

        return false;
    }
}

