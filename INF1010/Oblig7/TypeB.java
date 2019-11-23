/**
 * Created by Rune on 17.02.2016.
 */
public class TypeB extends LegeMiddel {

    private final String type = "vanedannende";
    private int styrkeVanedannende;

    public TypeB(int styrkeVanedannende, String navn, int pris, int virkestoffMG, String type) {
        super(navn, pris, virkestoffMG, type);
        this.styrkeVanedannende = styrkeVanedannende;
    }
    public int getStyrke(){
        return styrkeVanedannende;
    }
}
