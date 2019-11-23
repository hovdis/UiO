/**
 * Created by Rune on 17.02.2016.
 */
public abstract class Resepter {

    private static int uniktNummer = 0;
    //maa ha peker til Lege
    private LegeMiddel legeMiddelPeker;
    private int personNR;
    private int antallReit;
    private Lege legePeker;
    private int pris;

    public Resepter(LegeMiddel legeMiddelPeker, int antallReit, int personNR, Lege legePeker, int pris) {
        this.legeMiddelPeker = legeMiddelPeker;
        this.antallReit = antallReit;
        this.personNR = personNR;
        this.legePeker = legePeker;
        this.pris = pris;
        uniktNummer++;
    }

    public int getPris() {
        return pris;
    }
}
