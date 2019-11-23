/**
 * Created by Rune on 17.02.2016.
 */
public class Lege implements Lik {
    String navn;

    public Lege (String navn) {
        this.navn = navn;
    }

    public boolean samme(String sNavn) {
        if (sNavn.equals(this.navn)) {
            return true;
        } return false;
    }


}
