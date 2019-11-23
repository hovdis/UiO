/**
 * Created by Rune on 17.02.2016.
 */
public class TypeAPille extends TypeA implements Pille {

    private int antPiller;
    private int MGPrPille;

    public TypeAPille(int styrke, String navn, int pris, int virkestoffMG, int antPiller, String type) {
        super(styrke, navn, pris, virkestoffMG, type);
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
