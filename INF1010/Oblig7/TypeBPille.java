/**
 * Created by Rune on 17.02.2016.
 */
public class TypeBPille extends TypeB implements Pille{
    private int antPiller;
    private int MGPrPille;

    public TypeBPille(int styrkeVanedannende, String navn, int pris, int virkestoffMG, int antPiller, String type) {
        super(styrkeVanedannende, navn, pris, virkestoffMG, type);
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
