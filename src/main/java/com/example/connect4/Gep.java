package com.example.connect4;

import java.util.Random;


public class Gep {
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
        char oszlop = (char) ('1' + veletlen.nextInt(7));
        return String.valueOf(oszlop);
    }
}
