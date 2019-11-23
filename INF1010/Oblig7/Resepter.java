/**
 * Created by Rune on 17.02.2016.
 */
public  class Resepter {

    private static int uniktNummer = 0;
    private LegeMiddel legeMiddelPeker;
    private int personNR;
    private int antallReit;
    private Lege legePeker;
    private int pris;
    private int nr;
    private String farge;

    public Resepter(LegeMiddel legeMiddelPeker, int antallReit, int personNR, Lege legePeker, int pris, String farge) {
        this.legeMiddelPeker = legeMiddelPeker;
        this.antallReit = antallReit;
        this.personNR = personNR;
        this.legePeker = legePeker;
        this.pris = pris;
        this.farge = farge;
        nr = uniktNummer;
        uniktNummer++;
    }

    public int getUniktNummer() {
        return nr;
    }

    public boolean erGyldig() {
        return (antallReit > 0);
    }

    public String getFarge() {
        return farge;
    }

    public LegeMiddel getLegeMiddelPeker() {
        return legeMiddelPeker;
    }

    public int getPersonNR() {
        return personNR;
    }

    public int getAntallReit() {
        return antallReit;
    }

    public Lege getLegePeker() {
        return legePeker;
    }

    public int getPris() {
        return pris;
    }
    public int getNr() {
        return nr;
    }
    public LegeMiddel getLegeMiddel() {
        return legeMiddelPeker;
    }
}

