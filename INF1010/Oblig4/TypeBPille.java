/**
 * Created by Rune on 17.02.2016.
 */
public class TypeBPille extends TypeB implements Pille{
    private int antPiller;
    private int MGPrPille;

    public TypeAPille(int styrkeVanedannende, String navn, double pris, int virkestoffMG, int antPiller) {
        super(styrkeVanedannende, navn, pris, virkestoffMG);
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
