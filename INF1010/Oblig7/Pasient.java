/**
 * Created by Rune on 17.02.2016.
 */
public class Pasient {
    private String navn;
    private String foedselsNR;
    private String adresse;
    private String postNR;
    private static int loepeNR = 0;
    private int uniktNummer;

    public Pasient(String navn, String foedselsNR, String adresse, String postNR) {
        this.navn = navn;
        this.foedselsNR = foedselsNR;
        this.adresse = adresse;
        this.postNR = postNR;
        uniktNummer = loepeNR;
        loepeNR++;
    }

    public String hentNavn() {
        return this.navn;
    }

    public int getLoepeNR() {
        return uniktNummer;
    }

    public String toString(){
        return navn;
    }

    public String getFoedselsNR() {
        return foedselsNR;
    }

    public String getAdresse(){
        return adresse;
    }

    public String getPostNR() {
        return postNR;
    }




}
