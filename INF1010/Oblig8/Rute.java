/**
 * Created by Rune on 02.04.2016.
 */
public class Rute {
    int verdi = 0;

    private Boks boks;
    private Kolonne kolonne;
    private Rad rad;
    private Brett brett;
    Rute neste = null;
    private boolean fast = false;

    Rute() {

    }
    Rute (int verdi) {
        this.verdi = verdi;
    }

    public int[] finnAlleMuligeTall() {
        if ( fast == true){
            return null;
        }
        int[] muligeVerdier = new int[kolonne.getRuteArray().length];

        for(int i = 1; i<muligeVerdier.length+1;i++){
            for(int j = 0;j<muligeVerdier.length; j++ ){
                if(kolonne.getRuteArray()[j].getVerdi()==i){
                    break;
                } else if(rad.getRuteArray()[j].getVerdi()==i){
                    break;
                } else if(boks.getRuteArray()[j].getVerdi()==i){
                    break;
                } else if(j==muligeVerdier.length-1){
                    muligeVerdier[i-1]=i;
                }
            }
        }
        int teller = 0;
        for(int i = 0; i<muligeVerdier.length; i++){
            if(muligeVerdier[i]!=0){
                teller++;
            }
        }
        int[] retur = new int[teller];
        int teller2 = 0;
        for(int i = 0; i<muligeVerdier.length; i++){
            if(muligeVerdier[i]!=0){
                retur[teller2]=muligeVerdier[i];
                teller2++;
            }
        }
        return retur;
    }

    public int getVerdi() {
        return verdi;
    }
    public void settKolonne(Kolonne kolonne) {
        this.kolonne = kolonne;
    }
    public void settRad(Rad rad) {
        this.rad = rad;
    }

    public void settBrett(Brett brett){
        this.brett=brett;
    }

    public void settBoks(Boks boks) {
        this.boks = boks;
    }
}
