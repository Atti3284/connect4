package Player;


public class Jatekos implements JatekosInterface{
    private final String jel;

    public Jatekos(String szin) {
        this.jel = szin.equals("Sárga") ? "O" : "X";
    }

    public String getJel() {
        return jel;
    }


}