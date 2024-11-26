package com.example.connect4;


import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner beolvas = new Scanner(System.in);
        Tabla tabla = new Tabla();
        Jatekos ember = new Jatekos("Sárga");
        Gep gep = new Gep("Piros");

        boolean jatekVege = false;
        System.out.println("Üdv az Amőba4 játékban!");
        tabla.megjelenit();

        while (!jatekVege) {
            // Ember lép
            System.out.println("Te jössz, válassz egy oszlopot (pl. '1', '2', ... ,'7'):");
            String oszlop = beolvas.nextLine();
            while (!tabla.lepes(oszlop, ember)) {
                System.out.println("Nem érvényes lépés, próbáld újra:");
                oszlop = beolvas.nextLine();
            }
            tabla.megjelenit();
            if (tabla.nyeres(ember)) {
                System.out.println("Gratulálok, nyertél!");
                jatekVege = true;
                continue;
            }

            // Gép lép
            System.out.println("A gép lép...");
            String gepLepes = gep.lep(tabla);
            tabla.lepes(gepLepes, gep);
            tabla.megjelenit();
            if (tabla.nyeres(gep)) {
                System.out.println("Sajnos a gép nyert!");
                jatekVege = true;
            }
        }
        beolvas.close();//vége
    }
}
