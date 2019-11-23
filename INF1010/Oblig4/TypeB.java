/**
 * Created by Rune on 17.02.2016.
 */
public class TypeB extends LegeMiddel {

    private final String type = "vanedannende";
    private int styrkeVanedannende;

    public TypeB(int styrkeVanedannende, String navn, double pris, int virkestoffMG) {
        super(navn, pris, virkestoffMG);
        this.styrkeVanedannende = styrkeVanedannende;
    }
}
