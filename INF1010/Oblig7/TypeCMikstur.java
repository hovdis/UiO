/**
 * Created by Rune on 17.02.2016.
 */
public class TypeCMikstur extends TypeC implements Mikstur {
    private int cm3;
    private int mgPrCm3;

    public TypeCMikstur(String navn, int pris, int virkestoffMG, int cm3, String type) {
        super(navn, pris, virkestoffMG, type);
        this.cm3 = cm3;
        mgPrCm3 = virkestoffMG / cm3;
    }


    public int getCm3() {
        return cm3;
    }
    public int virkestoffPrCm3(){
        return mgPrCm3;
    }
}
