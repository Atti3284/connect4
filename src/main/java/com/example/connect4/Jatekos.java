package com.example.connect4;


public class Jatekos {
    private final String jel;

    public Jatekos(String szin) {
        this.jel = szin.equals("Sárga") ? "O" : "X";
    }

    public String getJel() {
        return jel;
    }
}