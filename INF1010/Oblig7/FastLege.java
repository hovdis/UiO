/**
 * Created by Rune on 17.02.2016.
 */
public class FastLege extends Lege implements Avtale {

    private int avtaleNR;

    FastLege(String navn, int avtaleNR) {
        super(navn);
        this.avtaleNR = avtaleNR;
    }

    public int getAvtaleNR() {
        return avtaleNR;
    }
}
