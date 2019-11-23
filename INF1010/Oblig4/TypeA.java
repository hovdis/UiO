/**
 * Created by Rune on 17.02.2016.
 */
public class TypeA extends LegeMiddel {

    private final String type = "narkotisk";
    private int styrke;

    public TypeA(int styrke, String navn, double pris, int virkestoffMG) {
        super(navn, pris, virkestoffMG);
        this.styrke = styrke;
    }
}
