/**
 * Created by Rune on 17.02.2016.
 */
public class TypeCPille extends TypeC implements Pille {
    private int antPiller;
    private int MGPrPille;

    public TypeAPille(String navn, double pris, int virkestoffMG, int antPiller) {
        super(navn, pris, virkestoffMG);
        this.antPiller = antPiller;
        this.MGPrPille = virkestoffMG/antPiller;
    }

    public int getAntPiller() {
        return antPiller;
    }
    public int getMGPrPille() {
        return MGPrPille;
    }
}
