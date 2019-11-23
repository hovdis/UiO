/**
 * Created by Rune on 17.02.2016.
 */
public class Lege implements Comparable<Lege>, Lik {
    String navn;
    int avtaler = 0;
    public Lege (String navn) {
        this.navn = navn;
    }
    public Lege (String navn, int avtaler) {
        this.navn = navn;
        this.avtaler = avtaler;
    }

    public boolean samme(String sNavn) {
        if (sNavn.equals(this.navn)) {
            return true;
        } return false;
    }

    public String hentNavn() {
        return this.navn;
    }
    public int getAvtaler() {
        return avtaler;
    }
    public int compareTo(Lege l) {
        return this.navn.compareTo(l.hentNavn());
    }

}
