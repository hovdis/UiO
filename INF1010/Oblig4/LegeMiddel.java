/**
 * Created by Rune on 17.02.2016.
 */
public abstract class LegeMiddel {

    public String navn;
    public static int uniktNummer = 0;
    public int pris;
    public int virkestoffMG;

    public LegeMiddel(String navn, double pris, int virkestoffMG) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoffMG = virkestoffMG;
        uniktNummer++;
    }

    public int getPris() {
        return pris;
    }
}
