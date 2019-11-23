/**
 * Created by Rune on 17.02.2016.
 */
public abstract class TypeAPille extends TypeA implements Pille {

    private int antPiller;
    private int MGPrPille;

    public TypeAPille(int styrke, String navn, double pris, int virkestoffMG, int antPiller) {
        super(styrke, navn, pris, virkestoffMG);
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
