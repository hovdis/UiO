/**
 * Created by Rune on 17.02.2016.
 */
public class Pasient {
    private String navn;
    private int foedselsNR;
    private String adresse;
    private int postNR;
    private static int loepeNR = 0;

    public Pasient(String navn, int foedselsNR, String adresse, int postNR) {
        this.navn = navn;
        this.foedselsNR = foedselsNR;
        this.adresse = adresse;
        this.postNR = postNR;
        loepeNR++;
    }


}
