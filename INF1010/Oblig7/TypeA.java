/**
 * Created by Rune on 17.02.2016.
 */
public class TypeA extends LegeMiddel {

    private final String type = "narkotisk";
    private int styrke;

    public TypeA(int styrke, String navn, int pris, int virkestoffMG, String type) {
        super(navn, pris, virkestoffMG, type);
        this.styrke = styrke;
    }
    public int getStyrke() {
        return styrke;
    }

}
