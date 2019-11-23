/**
 * Created by Rune on 17.02.2016.
 */
public class LegeMiddel {

    public String navn;
    public static int uniktNummer = 0;
    public int pris;
    public int virkestoffMG;
    public String type;
    public int detUnikeNummeret;


    public LegeMiddel(String navn, int pris, int virkestoffMG, String type) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoffMG = virkestoffMG;
        this.type = type;
        detUnikeNummeret = uniktNummer;

        uniktNummer++;
    }

    public int getPris() {
        return pris;
    }
    public int getUniktNummer() {
        return detUnikeNummeret;
    }
    public String getType() {
        return type;
    }

    public String toString() {
        return navn;
    }
}
